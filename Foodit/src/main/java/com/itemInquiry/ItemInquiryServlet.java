package com.itemInquiry;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.SessionInfo;
import com.notice.NoticeDAO;
import com.notice.NoticeDTO;
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
			String detailUrl = cp + "/itemInquiry/detail.do?page=" + current_page;
		
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
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		
		String cp = req.getContextPath();
		

		// admin만 글을 등록
		if (!info.getMemberId().equals("admin")) {
			resp.sendRedirect(cp + "/itemInquiry/list.do");
			return;
		}

		req.setAttribute("mode", "write");
		forward(req, resp, "/WEB-INF/views/itemInquiry/write.jsp");
	}
	
	protected void writeSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 저장
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");

		String cp = req.getContextPath();
		
		if (req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp + "/notice/list.do");
			return;
		}
		
		ItemInquiryDAO dao = new ItemInquiryDAO();
		
		String page = req.getParameter("page");
		int current_page = 1;
		if (page != null) {
			current_page = Integer.parseInt(page);
		}
		try {
			
			ItemInquiryDTO dto = new ItemInquiryDTO();
			
			dto.setMemberId(info.getMemberId());

			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content"));
			dto.setContent(req.getParameter("itemNo"));

			dao.insertInquiry(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/itemInquiry/itemInquiry.do?page=" + current_page);
	}

}
