package com.itemInquiry;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/itemInquiry/*")
public class ItemInquiryServlet extends MyServlet {
	private static final long serialVersionUID = 1048915766829257364L;

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
		
		if (uri.indexOf("itemInquiry.do") != -1) {
			inquiryList(req, resp);
		} else if (uri.indexOf("write.do") != -1) {
			writeForm(req, resp);
		} else if (uri.indexOf("write_ok.do") != -1) {
			writeSubmit(req, resp);
		} else if (uri.indexOf("article.do") != -1) {
			article(req, resp);
		} else if (uri.indexOf("insertReply.do") != -1) {
			insertReply(req, resp);
		} else if (uri.indexOf("listReply.do") != -1) {
			listReply(req, resp);
		}
	}
	
	// 아이템별 상품문의 개수
	protected void inquiryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ItemInquiryDAO dao =  new ItemInquiryDAO();
		MyUtil util = new MyUtil();
		
		String cp = req.getContextPath();
		
		try {
			String page = req.getParameter("page");
			int current_page = 1;
			if (page != null) {
				current_page = Integer.parseInt(page);
			}
			
			
			long itemNo = Long.parseLong(req.getParameter("itemNo"));
			
			// 전체 데이터 개수			
			int dataCount;
			dataCount = dao.dataCount(itemNo);

			// 전체 페이지 수
			int size = 10;
			int total_page = util.pageCount(dataCount, size);
			if (current_page > total_page) {
				current_page = total_page;
			}
			
			int offset = (current_page - 1) * size;
			if(offset < 0) offset = 0;
			
			List<ItemInquiryDTO> list = null;
			ItemInquiryDTO vo = new ItemInquiryDTO();
			list = dao.inquiryList(itemNo, offset, size);
			vo = dao.readItemName(itemNo);
			// 페이징 처리
			String listUrl = cp + "/itemInquiry/itemInquiry.do";
			String detailUrl = cp + "/itemInquiry/article.do?page=" + current_page;
		
			String paging = util.paging(current_page, total_page, listUrl);
			
			req.setAttribute("itemNo", itemNo);
			req.setAttribute("vo", vo);
			req.setAttribute("list", list);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("size", size);
			req.setAttribute("detailUrl", detailUrl);
			req.setAttribute("paging", paging);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String path = "/WEB-INF/views/itemInquiry/list.jsp";
		forward(req, resp, path);	
	}
	
	protected void writeForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글쓰기 폼		
		ItemInquiryDAO dao =  new ItemInquiryDAO();
		long itemNo = Long.parseLong(req.getParameter("itemNo"));

		try {
			ItemInquiryDTO vo = new ItemInquiryDTO();
			vo = dao.readItemName(itemNo);
			req.setAttribute("vo", vo);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		req.setAttribute("itemNo", itemNo);
		req.setAttribute("mode", "write");
		forward(req, resp, "/WEB-INF/views/itemInquiry/write.jsp");
	}
	
	protected void writeSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 저장
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");

		String cp = req.getContextPath();
		
		if (req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp + "/itemInquiry/list.do");
			return;
		}
		
		ItemInquiryDAO dao = new ItemInquiryDAO();
		
		String itemnum = req.getParameter("itemNo");
		
		long itemNo = Long.parseLong(itemnum);
		try {
			ItemInquiryDTO dto = new ItemInquiryDTO();
			
			dto.setMemberId(info.getMemberId());

			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content"));
			dto.setItemNo(itemNo);

			dao.insertInquiry(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/itemInquiry/itemInquiry.do?&itemNo="+itemNo);
	}
	protected void article(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ItemInquiryDAO dao = new ItemInquiryDAO();
		MyUtil util = new MyUtil();
	
		String cp = req.getContextPath();
		String page = req.getParameter("page");
		
		String query = "page=" + page;
		String itemnum = req.getParameter("itemNo");
		
		try {
			long itemNo = Long.parseLong(itemnum);
			long inquiryNo = Long.parseLong(req.getParameter("inquiryNo"));
			// 조회수 할까말까,,
			
			// 게시물 가져오기
			ItemInquiryDTO dto = dao.readBoard(itemNo,inquiryNo);
			if (dto == null) {
				resp.sendRedirect(cp + "/itemInquiry/list.do?" + query);
				return;
			}
			dto.setContent(util.htmlSymbols(dto.getContent()));
			
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			req.setAttribute("query", query);

			forward(req, resp, "/WEB-INF/views/itemInquiry/article.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void insertReply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ItemInquiryDAO dao = new ItemInquiryDAO();
				
		String state = "false";
		try {
			ReplyDTO dto = new ReplyDTO();
			long inquiryNo = Long.parseLong(req.getParameter("inquiryNo"));
			dto.setInquiryNo(inquiryNo);
			dto.setContent(req.getParameter("content"));
			
			dao.insertReply(dto);
			state = "true";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject job = new JSONObject();
		job.put("state", state);
		
		resp.setContentType("text/html;charset=utf-8"); // 이거없으면 한글 다 깨짐
		PrintWriter out = resp.getWriter();
		out.print(job.toString());

	}
	protected void listReply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ItemInquiryDAO dao = new ItemInquiryDAO();
		
		try {
			long inquiryNo = Long.parseLong(req.getParameter("inquiryNo"));
			ReplyDTO dto = dao.listReply(inquiryNo);
			
			
			req.setAttribute("dto", dto);
			
			forward(req, resp, "/WEB-INF/views/itemInquiry/Reply.jsp");
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendError(400);
	}
	
	
}
