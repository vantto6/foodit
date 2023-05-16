package com.item;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.SessionInfo;
import com.util.MyServlet;
import com.util.MyUtil;


@WebServlet("/item/*")
public class ItemServlet extends MyServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();
		
		// 세션 정보
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		if(info == null) {
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
			return;
		}
		
		if(uri.indexOf("item.do")!=-1) {
			itemList(req, resp);
		} else if(uri.indexOf("detail.do")!=-1) {
			detail(req, resp);
		} else if(uri.indexOf("basket_ok.do")!=-1) {
			basketSubmit(req, resp);
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
			if(gubun != null) {
				category = Integer.parseInt(gubun);
			}
			
			String page = req.getParameter("page");
			int current_page = 1;
			if (page != null) {
				current_page = Integer.parseInt(page);
			}
			
			// 전체 데이터 개수
			int dataCount;
			dataCount = dao.dataCount(category);

			// 전체 페이지 수
			int size = 10;
			int total_page = util.pageCount(dataCount, size);
			if (current_page > total_page) {
				current_page = total_page;
			}

			// 게시물 가져오기
			int offset = (current_page - 1) * size;
			if(offset < 0) offset = 0;
			
			List<ItemDTO> list = null;
			
			list = dao.listBoard(category,offset,size);

			// 페이징 처리
			String listUrl = cp + "/item/item.do?category="+category;
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

	protected void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 상품상세
		ItemDAO dao = new ItemDAO();
		MyUtil util = new MyUtil();
		
		String cp = req.getContextPath();
		String page = req.getParameter("page");
		
		String query = "page=" + page;
		
		try {
			long itemNo = Long.parseLong(req.getParameter("itemNo"));
			
			// 게시물 가져오기
			ItemDTO dto = dao.readItem(itemNo);
			if (dto == null) {
				resp.sendRedirect(cp + "/item/item.do?" + query);
				return;
			}
			dto.setContent(util.htmlSymbols(dto.getContent()));
			
			
			// JSP로 전달할 속성
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			req.setAttribute("query", query);

			// 포워딩
			forward(req, resp, "/WEB-INF/views/item/detail.jsp");
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect(cp + "/item/item.do?" + query);
	}
	protected void basketSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	

}
