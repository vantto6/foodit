package com.item;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.member.SessionInfo;
import com.util.MyServlet;
import com.util.MyUtil;

@WebServlet("/item/*")
public class ItemServlet extends MyServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String uri = req.getRequestURI();

		// 세션 정보
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");

		if (info == null) {
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
			return;
		}

		if (uri.indexOf("item.do") != -1) {
			itemList(req, resp);
		} else if (uri.indexOf("detail.do") != -1) {
			categoryDetail(req, resp);
		} else if (uri.indexOf("basketCheck.do") != -1) {

			basketCheck(req, resp);
		} else if (uri.indexOf("basket_ok.do") != -1) {
			basketSubmit(req, resp);
		} else if (uri.indexOf("insertItemLike.do") != -1) {
			// 게시글 공감
			insertItemLike(req, resp);
		} else if (uri.indexOf("newItem.do") != -1) {
			newitemList(req, resp);
		} else if (uri.indexOf("detail2.do") != -1) {
			detail(req, resp);
		}
	}

	protected void itemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 상품이미지 리스트(카테고리 있는거)

		ItemDAO dao = new ItemDAO();
		MyUtil util = new MyUtil();

		String cp = req.getContextPath();
		try {
			String gubun = req.getParameter("category");
			int category = 1;
			if (gubun != null) {
				category = Integer.parseInt(gubun);
			}

			String page = req.getParameter("page");
			int current_page = 1;
			if (page != null && !page.equals("")) {
				current_page = Integer.parseInt(page);
			}

			// 전체 데이터 개수
			int dataCount;
			dataCount = dao.dataCount(category);

			// 전체 페이지 수
			int size = 12;
			int total_page = util.pageCount(dataCount, size);
			if (current_page > total_page) {
				current_page = total_page;
			}

			// 게시물 가져오기
			int offset = (current_page - 1) * size;
			if (offset < 0)
				offset = 0;

			List<ItemDTO> list = null;

			list = dao.listBoard(category, offset, size);

			// 페이징 처리
			String listUrl = cp + "/item/item.do?category=" + category;
			String detailUrl = cp + "/item/detail.do?category=" + category + "&page=" + current_page;

			String paging = util.paging(current_page, total_page, listUrl);

			req.setAttribute("list", list);
			req.setAttribute("category", category);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("size", size);
			req.setAttribute("detailUrl", detailUrl);
			req.setAttribute("paging", paging);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String path = "/WEB-INF/views/item/item.jsp";
		forward(req, resp, path);
	}

	protected void newitemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 상품이미지 리스트(신상품 그리고 베스트)

		ItemDAO dao = new ItemDAO();
		MyUtil util = new MyUtil();

		String cp = req.getContextPath();
		try {
			int num = Integer.parseInt(req.getParameter("num"));
			String page = req.getParameter("page");
			int current_page = 1;
			if (page != null) {
				current_page = Integer.parseInt(page);
			}

			// 전체 데이터 개수
			int dataCount = 0;

			if (num == 1) {
				dataCount = dao.newdataCount();
			} else if (num == 2) {
				dataCount = dao.bestItemCount();
			}
			// 전체 페이지 수
			int size = 12;
			int total_page = util.pageCount(dataCount, size);
			if (current_page > total_page) {
				current_page = total_page;
			}

			// 게시물 가져오기
			int offset = (current_page - 1) * size;
			if (offset < 0)
				offset = 0;

			List<ItemDTO> list = null;
			if (num == 1) {
				list = dao.listBoard2(num, offset, size);
			} else {
				list = dao.listBoard3(num, offset, size);
			}

			// 페이징 처리
			String listUrl = cp + "/item/newItem.do?num=" + num;
			String detailUrl = cp + "/item/detail2.do?num=" + num + "&page=" + current_page;

			String paging = util.paging(current_page, total_page, listUrl);

			req.setAttribute("list", list);
			req.setAttribute("num", num);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("size", size);
			req.setAttribute("detailUrl", detailUrl);
			req.setAttribute("paging", paging);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String path = "/WEB-INF/views/item/newItem.jsp";
		forward(req, resp, path);
	}

	protected void categoryDetail(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 상품상세(카테고리)
		ItemDAO dao = new ItemDAO();
		MyUtil util = new MyUtil();

		String cp = req.getContextPath();
		String gubun = req.getParameter("category");
		int category = 1;
		if (gubun != null) {
			category = Integer.parseInt(gubun);
		}
		String page = req.getParameter("page");
		
		String query = "";
		if (page != null && page.equals("")) {
			query = "page=" + page;
		} else {
			page = "";
		}
		try {
			long itemNo = Long.parseLong(req.getParameter("itemNo"));

			// 게시물 가져오기
			ItemDTO dto = dao.readItem(category, itemNo);
			if (dto == null) {
				resp.sendRedirect(cp + "/item/item.do?"+query);
				return;
			}
			dto.setContent(util.htmlSymbols(dto.getContent()));

			// 로그인 유저의 게시글 공감 여부
			HttpSession session = req.getSession();
			SessionInfo info = (SessionInfo) session.getAttribute("member");
			boolean isMemberLike = dao.isMemberitemLike(itemNo, info.getMemberId());

			//개수
			int maxBasketCnt = dao.maxBasketCnt(itemNo);
			
			
			// JSP로 전달할 속성
			req.setAttribute("maxBasketCnt", maxBasketCnt);
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			req.setAttribute("query", query);
			req.setAttribute("category", category);
			req.setAttribute("isMemberLike", isMemberLike);

			// 포워딩
			forward(req, resp, "/WEB-INF/views/item/detail.jsp");
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/item/item.do?category=" + category );
	}

	protected void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 상품상세(카테고리 아닌거)
		ItemDAO dao = new ItemDAO();
		MyUtil util = new MyUtil();
		ItemDTO dto = null;
		String cp = req.getContextPath();

		String page = req.getParameter("page");
		String query = "";

		if (page != null && page.equals("")) {
			query = "page=" + page;
		} else {
			page = "";
		}

		try {
			long itemNo = Long.parseLong(req.getParameter("itemNo"));

			long num = 0;

			String number = req.getParameter("num");
			if (number != null) {
				num = Long.parseLong(number);
			}

			// 게시물 가져오기
			// 신상품
			if (num == 1) {
				dto = dao.newReadItem(itemNo);
			} else if (num == 2) {
				dto = dao.bestReadItem(itemNo);
			} else {

			}

			if (dto == null) {
				resp.sendRedirect(cp + "/item/item.do?" + query);
				return;
			}
			dto.setContent(util.htmlSymbols(dto.getContent()));

			// 로그인 유저의 게시글 공감 여부
			HttpSession session = req.getSession();
			SessionInfo info = (SessionInfo) session.getAttribute("member");
			boolean isMemberLike = dao.isMemberitemLike(itemNo, info.getMemberId());
			
			//개수
			int maxBasketCnt = dao.maxBasketCnt(itemNo);
			
			
			// JSP로 전달할 속성
			req.setAttribute("maxBasketCnt", maxBasketCnt);
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			req.setAttribute("num", num);
			req.setAttribute("query", query);
			req.setAttribute("isMemberLike", isMemberLike);

			// 포워딩
			forward(req, resp, "/WEB-INF/views/item/detail.jsp");
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/item/item.do?" + query);
	}

	protected void basketCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ItemDAO dao = new ItemDAO();

		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		String state = "false";

		try {
			long itemNo = Long.parseLong(req.getParameter("itemNo"));

			int checkbasket = dao.checkbasket(itemNo, info.getMemberId());
			if(checkbasket!=0) {
				state = "true";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		JSONObject job = new JSONObject();
		job.put("state", state);

		PrintWriter out = resp.getWriter();
		out.print(job.toString());
	}

	// 장바구니 저장
	protected void basketSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ItemDAO dao = new ItemDAO();
		String query = "";
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		String gubun = req.getParameter("category");
		int category = 1;
		
		String cp = req.getContextPath();
		if (req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp + "/bbs/list.do");
			return;
		}
		
		String page = req.getParameter("page");
		int basketCnt = Integer.parseInt(req.getParameter("basketCnt"));
		long itemNo = Integer.parseInt(req.getParameter("itemNo"));
		String number = req.getParameter("num");
		if (gubun != null && !gubun.equals("")) { // 카테고리일때
			category = Integer.parseInt(gubun);
			query += "category=" + category + "&page=" + page + "&itemNo=" + itemNo;

		} else if(number != null && !number.equals("")){ // 아닐때
			
			long num = Integer.parseInt(number);
			query += "num=" + num + "&itemNo=" + itemNo;
		}
		String memberId = info.getMemberId();

		try {
			ItemDTO dto = new ItemDTO();

			dto.setBasketCnt(basketCnt);
			dto.setMemberId(memberId);
			dto.setItemNo(itemNo);

			dao.insertBasket(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (gubun != null && !gubun.equals("")) {

			resp.sendRedirect(cp + "/item/detail.do?" + query);
		} else  if(number != null && !number.equals("")){

			resp.sendRedirect(cp + "/item/detail2.do?" + query);
		}
	}

	protected void insertItemLike(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 상품 찜 저장 : AJAX-JSON

		ItemDAO dao = new ItemDAO();

		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");

		String state = "false";
		int itemLikeCount = 0;

		try {
			long itemNo = Long.parseLong(req.getParameter("itemNo"));
			String isNoLike = req.getParameter("isNoLike");

			if (isNoLike.equals("true")) {
				dao.insertItemLike(itemNo, info.getMemberId()); // 공감
			} else {
				dao.deleteItemLike(itemNo, info.getMemberId()); // 공감 취소
			}
			itemLikeCount = dao.countItemLike(itemNo);

			state = "true"; // 추가됬으면 true

		} catch (SQLException e) {
			state = "liked";
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONObject job = new JSONObject();
		job.put("state", state);
		job.put("itemLikeCount", itemLikeCount);

		PrintWriter out = resp.getWriter();
		out.print(job.toString());
	}

}
