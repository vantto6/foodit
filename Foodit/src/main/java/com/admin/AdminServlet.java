package com.admin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.SessionInfo;

import com.util.FileManager;
import com.util.MyUploadServlet;
import com.util.MyUtil;

@MultipartConfig
@WebServlet("/admin/*")
public class AdminServlet extends MyUploadServlet {
	private static final long serialVersionUID = 1L;
	private String pathname;

	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		// String cp = req.getContextPath();
		String uri = req.getRequestURI();

		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");

		if (info == null) {
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
			return;
		}

		// 이미지를 저장할 경로(pathname)
		String root = session.getServletContext().getRealPath("/");
		pathname = root + "uploads" + File.separator + "admin";

		// uri에 따른 작업 구분
		if (uri.indexOf("admin.do") != -1) {
			admin(req, resp);
		} else if (uri.indexOf("addProduct.do") != -1) {
			addProductForm(req, resp);
		} else if (uri.indexOf("admin_ok.do") != -1) {
			addProductSubmit(req, resp);
		} else if (uri.indexOf("update.do") != -1) {
			updateForm(req, resp);
		} else if (uri.indexOf("update_ok.do") != -1) {
			updateSubmit(req, resp);
		} else if (uri.indexOf("list.do") != -1) {
			list(req, resp);
		} else if (uri.indexOf("deleteFile") != -1) {
			deleteFile(req, resp);
		} else if (uri.indexOf("delete.do") != -1) {
			delete(req, resp);
		} else if (uri.indexOf("stock.do") != -1) {
			seeStock(req, resp);
		} else if (uri.indexOf("brand.do") != -1) {
			seeBrand(req, resp);
		} else if (uri.indexOf("addBrand.do") != -1) {
			addBrand(req, resp);
		} else if (uri.indexOf("seeMembers.do") != -1) {
			seeMembers(req, resp);
		} else if (uri.indexOf("deleteMembers.do") != -1) {
			deleteMembers(req, resp);
		} else if (uri.indexOf("stats.do") != -1) {
			stats(req, resp);
		} else if (uri.indexOf("deleteBrand.do") != -1) {
			deleteBrand(req, resp);
		} else if (uri.indexOf("updateStat.do") != -1) {
		}
	}

	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시물 리스트
		AdminDAO dao = new AdminDAO();
		MyUtil util = new MyUtil();

		// HttpSession session = req.getSession();
		// SessionInfo info = (SessionInfo) session.getAttribute("member");

		String cp = req.getContextPath();

		try {
			String page = req.getParameter("page");
			int current_page = 1;
			if (page != null) {
				current_page = Integer.parseInt(page);
			}

			// 전체데이터 개수
			int dataCount = dao.dataCount();

			// 전체페이지수
			int size = 6;
			int total_page = util.pageCount(dataCount, size);
			if (current_page > total_page) {
				current_page = total_page;
			}

			// 게시물 가져오기
			int offset = (current_page - 1) * size;
			if (offset < 0)
				offset = 0;

			List<AdminDTO> list = dao.listProduct(offset, size);
			// AdminDTO file = dao.readPhotoFile(imgNo);
			// 페이징 처리
			String listUrl = cp + "/admin/list.do";
			String articleUrl = cp + "" + current_page;
			String paging = util.paging(current_page, total_page, listUrl);

			// 포워딩할 list.jsp에 넘길 값
			req.setAttribute("list", list);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("articleUrl", articleUrl);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("paging", paging);

		} catch (Exception e) {
			e.printStackTrace();
		}

		forward(req, resp, "/WEB-INF/views/admin/list.jsp");
	}

	protected void admin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", "상품 할인");
		req.setAttribute("mode", "admin");

		String path = "/WEB-INF/views/admin/admin.jsp";
		forward(req, resp, path);
	}

	protected void addProductForm(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("title", "품목 추가");
		req.setAttribute("mode", "admin");

		forward(req, resp, "/WEB-INF/views/admin/addProduct.jsp");
	}

	protected void addProductSubmit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		// 상품 추가

		// HttpSession session= req.getSession();
		// SessionInfo info = (SessionInfo)session.getAttribute("member");

		String cp = req.getContextPath();
		if (req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp + "/admin/addProduct.do");
			return;
		}

		String message = "";
		try {
			AdminDTO dto = new AdminDTO();

			dto.setItemName(req.getParameter("itemName"));
			dto.setPrice(Long.parseLong(req.getParameter("price")));
			dto.setDiscount(Long.parseLong(req.getParameter("discount")));
			dto.setCnt(Integer.parseInt(req.getParameter("cnt")));
			dto.setSaleUnit(req.getParameter("saleUnit"));
			dto.setDescription(req.getParameter("description"));
			dto.setDeadline(req.getParameter("deadline"));

			dto.setCategoryNo(Integer.parseInt(req.getParameter("categoryNo")));
			dto.setBrandNo(Integer.parseInt(req.getParameter("brandNo")));

			Map<String, String[]> map = doFileUpload(req.getParts(), pathname);
			if (map != null) {
				String[] saveFiles = map.get("saveFilenames");
				dto.setSaveFiles(saveFiles);
			}

			dao.insertItems(dto);
			resp.sendRedirect(cp + "/admin/list.do");
			return;
		} catch (SQLException e) {
			message = "상품 등록에 실패했습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			message = "상품 등록에 실패했습니다.";
		}

		req.setAttribute("message", message);
		req.setAttribute("title", "품목 추가");
		req.setAttribute("mode", "admin");

		forward(req, resp, "/WEB-INF/views/admin/addProduct.jsp");

	}

	protected void updateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정폼
		AdminDAO dao = new AdminDAO();

		String cp = req.getContextPath();

		String page = req.getParameter("page");

		try {
			long itemNo = Long.parseLong(req.getParameter("itemNo"));
			AdminDTO dto = dao.readProduct(itemNo);

			if (dto == null) {
				resp.sendRedirect(cp + "/admin/list.do?page=" + page);
				return;
			}

			List<AdminDTO> listFile = dao.listProductFile(itemNo);

			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			req.setAttribute("listFile", listFile);
			
			req.setAttribute("mode", "update");

			forward(req, resp, "/WEB-INF/views/admin/modifyProduct.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/admin/list.do?page=" + page);
	}

	protected void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정 완료
		AdminDAO dao = new AdminDAO();

		String cp = req.getContextPath();
		if (req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp + "/admin/admin.do");
			return;
		}

		String page = req.getParameter("page");

		try {
			AdminDTO dto = new AdminDTO();
			dto.setItemNo(Long.parseLong(req.getParameter("itemNo")));

			dto.setItemName(req.getParameter("itemName"));
			dto.setPrice(Long.parseLong(req.getParameter("price")));
			dto.setDiscount(Long.parseLong(req.getParameter("discount")));
			dto.setCnt(Integer.parseInt(req.getParameter("cnt")));
			dto.setSaleUnit(req.getParameter("saleUnit"));
			dto.setDescription(req.getParameter("description"));
			dto.setDeadline(req.getParameter("deadline"));

			dto.setCategoryNo(Integer.parseInt(req.getParameter("categoryNo")));
			dto.setBrandNo(Integer.parseInt(req.getParameter("brandNo")));

			Map<String, String[]> map = doFileUpload(req.getParts(), pathname);
			if (map != null) {
				String[] saveFiles = map.get("saveFilenames");
				dto.setSaveFiles(saveFiles);
			}

			dao.updateProduct(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/admin/list.do?page=" + page);
	}

	protected void deleteFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정에서 파일만 삭제
		AdminDAO dao = new AdminDAO();

		String cp = req.getContextPath();

		String page = req.getParameter("page");

		try {
			long itemNo = Long.parseLong(req.getParameter("itemNo"));
			long imgNo = Long.parseLong(req.getParameter("imgNo"));

			AdminDTO dto = dao.readProduct(itemNo);

			if (dto == null) {
				resp.sendRedirect(cp + "/admin/list.do?page=" + page);
				return;
			}

			AdminDTO vo = dao.readImageFile(imgNo);
			if (vo != null) {
				FileManager.doFiledelete(pathname, vo.getSaveFilename());

				dao.deleteImageFile("one", imgNo);
			}

			resp.sendRedirect(cp + "/admin/update.do?itemNo=" + itemNo + "&page=" + page);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/admin/list.do?page=" + page);
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();

		String cp = req.getContextPath();
		String page = req.getParameter("page");

		try {
			long itemNo = Long.parseLong(req.getParameter("itemNo"));
			AdminDTO dto = dao.readProduct(itemNo);

			if (dto == null) {
				resp.sendRedirect(cp + "/admin/list.do?page=" + page);
				return;
			}

			// Delete entries in reply table
			dao.deleteReply(itemNo);

			// Delete image files
			List<AdminDTO> listFile = dao.listProductFile(itemNo);
			for (AdminDTO vo : listFile) {
				FileManager.doFiledelete(pathname, vo.getSaveFilename());
			}
			dao.deleteImageFile("all", itemNo);

			// Delete table data
			dao.delete(itemNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/admin/list.do?page=" + page);
	}

	protected void seeStock(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		MyUtil util = new MyUtil();

		// HttpSession session = req.getSession();
		// SessionInfo info = (SessionInfo) session.getAttribute("member");

		String cp = req.getContextPath();

		try {
			String page = req.getParameter("page");
			int current_page = 1;
			if (page != null) {
				current_page = Integer.parseInt(page);
			}

			// 전체데이터 개수
			int dataCount = dao.dataCount();

			// 전체페이지수
			int size = 6;
			int total_page = util.pageCount(dataCount, size);
			if (current_page > total_page) {
				current_page = total_page;
			}

			// 게시물 가져오기
			int offset = (current_page - 1) * size;
			if (offset < 0)
				offset = 0;

			List<AdminDTO> list = dao.listStock(offset, size);
			// AdminDTO file = dao.readPhotoFile(imgNo);
			// 페이징 처리
			String listUrl = cp + "/admin/stock.do";
			String articleUrl = cp + "" + current_page;
			String paging = util.paging(current_page, total_page, listUrl);

			// 포워딩할 list.jsp에 넘길 값
			req.setAttribute("list", list);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("articleUrl", articleUrl);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("paging", paging);

		} catch (Exception e) {
			e.printStackTrace();
		}

		forward(req, resp, "/WEB-INF/views/admin/stockStatus.jsp");
	}

	protected void seeBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		MyUtil util = new MyUtil();

		// HttpSession session = req.getSession();
		// SessionInfo info = (SessionInfo) session.getAttribute("member");

		String cp = req.getContextPath();

		try {
			String page = req.getParameter("page");
			int current_page = 1;
			if (page != null) {
				current_page = Integer.parseInt(page);
			}

			// 전체데이터 개수
			int dataCount = dao.brandCount();

			// 전체페이지수
			int size = 5;
			int total_page = util.pageCount(dataCount, size);
			if (current_page > total_page) {
				current_page = total_page;
			}

			// 게시물 가져오기
			int offset = (current_page - 1) * size;
			if (offset < 0)
				offset = 0;

			List<AdminDTO> list = dao.listBrand(offset, size);
			// AdminDTO file = dao.readPhotoFile(imgNo);
			// 페이징 처리
			String listUrl = cp + "/admin/brand.do";
			String articleUrl = cp + "" + current_page;
			String paging = util.paging(current_page, total_page, listUrl);

			// 포워딩할 list.jsp에 넘길 값
			req.setAttribute("list", list);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("articleUrl", articleUrl);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("paging", paging);

		} catch (Exception e) {
			e.printStackTrace();
		}

		forward(req, resp, "/WEB-INF/views/admin/brandList.jsp");
	}

	protected void addBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();

		String cp = req.getContextPath();
		if (req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp + "/admin/addBrand.do");
			return;
		}

		String message = "";
		try {
			AdminDTO dto = new AdminDTO();

			dto.setBrandNo(Integer.parseInt(req.getParameter("brandNo")));
			dto.setBrandName(req.getParameter("brandName"));

			dao.addBrands(dto);

			resp.sendRedirect(cp + "/admin/brand.do");
			return;
		} catch (SQLException e) {
			message = "브랜드 등록에 실패했습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			message = "브랜드 등록에 실패했습니다.";
		}

		req.setAttribute("message", message);
		req.setAttribute("title", "브랜드 리스트");
		req.setAttribute("mode", "admin");

		forward(req, resp, "/WEB-INF/views/admin/addBrand.jsp");

	}

	protected void seeMembers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		MyUtil util = new MyUtil();

		// HttpSession session = req.getSession();
		// SessionInfo info = (SessionInfo) session.getAttribute("member");

		String cp = req.getContextPath();

		try {
			String page = req.getParameter("page");
			int current_page = 1;
			if (page != null) {
				current_page = Integer.parseInt(page);
			}

			// 전체데이터 개수
			int dataCount = dao.memberCounts();

			// 전체페이지수
			int size = 10;
			int total_page = util.pageCount(dataCount, size);
			if (current_page > total_page) {
				current_page = total_page;
			}

			// 게시물 가져오기
			int offset = (current_page - 1) * size;
			if (offset < 0)
				offset = 0;

			List<AdminDTO> list = dao.listMembers(offset, size);
			// 페이징 처리
			String listUrl = cp + "/admin/seeMembers.do";
			String articleUrl = cp + "" + current_page;
			String paging = util.paging(current_page, total_page, listUrl);

			// 포워딩할 list.jsp에 넘길 값
			req.setAttribute("list", list);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("articleUrl", articleUrl);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("paging", paging);

		} catch (Exception e) {
			e.printStackTrace();
		}

		forward(req, resp, "/WEB-INF/views/admin/memberList.jsp");
	}

	protected void deleteMembers(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 삭제 완료
		AdminDAO dao = new AdminDAO();

		String cp = req.getContextPath();

		String page = req.getParameter("page");

		try {
			long clientNo = Long.parseLong(req.getParameter("clientNo"));

			AdminDTO dto = dao.readMembers(clientNo);
			if (dto == null) {
				resp.sendRedirect(cp + "/admin/seeMembers.do?page=" + page);
				return;
			}

			// 테이블 데이터 삭제
			dao.deleteMembers(clientNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/admin/seeMembers.do?page=" + page);
	}

	protected void deleteBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();

		String cp = req.getContextPath();

		String page = req.getParameter("page");

		try {
			int brandNo = Integer.parseInt(req.getParameter("brandNo"));

			AdminDTO dto = dao.readBrand(brandNo);
			if (dto == null) {
				resp.sendRedirect(cp + "/admin/brand.do?page=" + page);
				return;
			}
			dao.deleteBrand(brandNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect(cp + "/admin/brand.do?page=" + page);
	}

	protected void stats(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		req.setAttribute("title", "통계");
		req.setAttribute("mode", "admin");

		int memberCount = dao.memberCounts();
		int orderCount = dao.orderCount();

		req.setAttribute("memberCount", memberCount);
		req.setAttribute("orderCount", orderCount);

		String path = "/WEB-INF/views/admin/stats.jsp";
		forward(req, resp, path);
	}

}
