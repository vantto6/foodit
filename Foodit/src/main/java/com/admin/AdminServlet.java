package com.admin;

	import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.member.SessionInfo;
import com.util.MyUploadServlet;

	@MultipartConfig
	@WebServlet("/admin/*")
	public class AdminServlet extends MyUploadServlet {
		private static final long serialVersionUID = 1L;
		private String pathname;
		

		@Override
		protected void execute(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			
			String cp = req.getContextPath();
			String uri = req.getRequestURI();
			
			HttpSession session = req.getSession();
			SessionInfo info = (SessionInfo)session.getAttribute("member");
			
			if(info == null) {
				forward(req, resp, "/WEB-INF/views/member/login.jsp");
				return;
			}

			// 이미지를 저장할 경로(pathname)
			String root = session.getServletContext().getRealPath("/");
			pathname = root + "uploads" + File.separator + "admin";
			
			// uri에 따른 작업 구분
			if(uri.indexOf("admin.do")!=-1) {
				admin(req, resp);
			}else if (uri.indexOf("discount.do")!=-1){
				discount(req, resp);
			}else if(uri.indexOf("addProduct.do")!=-1){
				addProductForm(req, resp);
			}else if(uri.indexOf("admin_ok.do")!=-1){
				addProductSubmit(req, resp); 
			}
		}
	
		protected void admin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setAttribute("title", "상품 할인");
			req.setAttribute("mode", "admin");
			
			String path = "/WEB-INF/views/admin/admin.jsp";
			forward(req, resp, path);
		}
		protected void discount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setAttribute("title", "상품 할인");
			req.setAttribute("mode", "admin");
			
			String path = "/WEB-INF/views/admin/discount.jsp";
			forward(req, resp, path);
		}
		protected void addProductForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setAttribute("title", "품목 추가");
			req.setAttribute("mode", "admin");
			
			
			forward(req, resp, "/WEB-INF/views/admin/addProduct.jsp");
		}
		protected void addProductSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			AdminDAO dao = new AdminDAO();
			//상품 추가
			
			//HttpSession session= req.getSession();
			//SessionInfo info = (SessionInfo)session.getAttribute("member");

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
				
				resp.sendRedirect(cp + "/admin/admin.do");
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
		
		}
		
		protected void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		}
