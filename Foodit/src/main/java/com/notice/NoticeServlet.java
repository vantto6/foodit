package com.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.SessionInfo;
import com.util.MyServlet;
import com.util.MyUtil;



@WebServlet("/notice/*")
public class NoticeServlet extends MyServlet  {
 
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();
		/*
		String cp = req.getContextPath();
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		if(uri.indexOf("list.do")== -1 && info == null) {
			resp.sendRedirect(cp + "/member/login.do");
			return;
		}
		*/
		if(uri.indexOf("list.do")!= -1) {
			list(req, resp);
		}else if(uri.indexOf("write.do") != -1) {
			writeForm(req,resp);
		}else if(uri.indexOf("write_ok.do")!= -1) {
			writeSubmit(req,resp);
		}else if(uri.indexOf("article.do")!= -1) {
			article(req,resp);
		}else if(uri.indexOf("update.do")!= -1) {
			updateForm(req,resp);
		}else if(uri. indexOf("update_ok.do")!= -1) {
			updateSubmit(req,resp);
		}else if(uri.indexOf("delete.do")!= -1) {
			delete(req,resp);
		} else if (uri.indexOf("deleteList.do") != -1) {
			deleteList(req, resp);
		}
	}
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeDAO dao = new NoticeDAO();
		MyUtil util = new MyUtil();
		
		String cp = req.getContextPath();
		
		try {
			String page = req.getParameter("page");
			int current_page = 1;
			if(page != null) {
				current_page = Integer.parseInt(page);
			}
			
			// 전체 데이터 개수
			int dataCount = dao.dataCount();
			
			// 전체 페이지수
			int size = 4;
			int total_page = util.pageCount(dataCount, size);
			if(current_page > total_page) {
				current_page = total_page;
			}
			
			// 게시글 가져오기
			int offset = (current_page - 1) * size;
			if(offset < 0) offset = 0;
			
			List<NoticeDTO> list = dao.listNotice(offset, size);
			
			String listUrl = cp + "/notice/list.do";
			String articleUrl = cp + "/notice/article.do?page=" + current_page ;
 

			String paging = util.paging(current_page, total_page, listUrl);
			
			// 포워딩 jsp에 전달할 데이터
			req.setAttribute("list", list);
			req.setAttribute("articleUrl", articleUrl);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("size", size);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("paging", paging);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		forward(req, resp, "/WEB-INF/views/notice/list.jsp");
	}
	
	protected void writeForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글쓰기 폼
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		
		String cp = req.getContextPath();
		

		// admin만 글을 등록
		if (!info.getMemberId().equals("admin")) {
			resp.sendRedirect(cp + "/notice/list.do");
			return;
		}

		req.setAttribute("mode", "write");
		forward(req, resp, "/WEB-INF/views/notice/write.jsp");
	}
	
	protected void writeSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 저장
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		String cp = req.getContextPath();
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp + "/notice/list.do");
			return;
		}
		
		if(!info.getMemberId().equals("admin")) {
			resp.sendRedirect(cp+"/notice/list.do");
			return;
		}
		
		NoticeDAO dao = new NoticeDAO();
		try {
			NoticeDTO dto = new NoticeDTO();
			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content"));
			
			dao.insertNotice(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect(cp + "/notice/list.do");
	}
	
	
	protected void article(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp = req.getContextPath();
		
		String page = req.getParameter("page");
		String query = "page=" + page;

		NoticeDAO dao = new NoticeDAO();
		
		try {
			long num = Long.parseLong(req.getParameter("num"));

			NoticeDTO dto = dao.readNotice(num);
			if(dto == null) {
				resp.sendRedirect(cp + "/notice/list.do?"+query);
				return;
			}
			
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			
			
			req.setAttribute("dto", dto);
			req.setAttribute("query", query);
			req.setAttribute("page", page);

			forward(req, resp, "/WEB-INF/views/notice/article.jsp");			
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect(cp + "/notice/list.do?" + query);
	}
	
	protected void updateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		String cp = req.getContextPath();
		
		if(!info.getMemberId().equals("admin")) {
			resp.sendRedirect(cp+"/notice/list.do");
			return;
		}
		
		NoticeDAO dao = new NoticeDAO();
		String page = req.getParameter("page");
		
		try {
			long num = Long.parseLong(req.getParameter("num"));
			
			NoticeDTO dto = dao.readNotice(num);
			if(dto==null) {
				resp.sendRedirect(cp+"/notice/list.do?page="+page);
				return;
			}
		
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);

			req.setAttribute("mode", "update");

			forward(req, resp, "/WEB-INF/views/notice/write.jsp");
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect(cp + "/notice/list.do?page=" + page );
	}
	
	protected void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		String cp = req.getContextPath();

		if(req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp+"/notice/list.do");
			return;
		}
		
		if(!info.getMemberId().equals("admin")) {
			resp.sendRedirect(cp + "/notice/list.do");
			return;
		}

		NoticeDAO dao = new NoticeDAO();
		
		String page = req.getParameter("page");
		
		try {
			NoticeDTO dto = new NoticeDTO();
			dto.setNoticeNo(Long.parseLong(req.getParameter("num")));

			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content"));
			
			dao.updateNotice(dto);

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/notice/list.do?page=" + page );
	}
	
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		String cp = req.getContextPath();
		
		if(!info.getMemberId().equals("admin")) {
			resp.sendRedirect(cp + "/notice/list.do");
			return;
		}
		
		NoticeDAO dao = new NoticeDAO();
		
		String page = req.getParameter("page");
		String query = "page="+ page;
		
		
		try {
			
			long num = Long.parseLong(req.getParameter("num"));
			
			NoticeDTO dto = dao.readNotice(num);
			if(dto == null) {
				resp.sendRedirect(cp + "/notice/list.do?" + query);
				return;
			}
			
			dao.deleteNotice(num);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect(cp + "/notice/list.do?" + query);
	}
	
	protected void deleteList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		String cp = req.getContextPath();

		if (!info.getMemberId().equals("admin")) {
			resp.sendRedirect(cp + "/notice/list.do");
			return;
		}
		

		String page = req.getParameter("page");
		String query =  "&page=" + page;

		try {
			 
			String[] nn = req.getParameterValues("nums");
			long nums[] = null;
			nums = new long[nn.length];
			for (int i = 0; i < nn.length; i++) {
				nums[i] = Long.parseLong(nn[i]);
			}

			NoticeDAO dao = new NoticeDAO();


			// 게시글 삭제
			dao.deleteNoticeList(nums);

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/notice/list.do?" + query);
	}

	
}
