package com.inquiry;

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

@WebServlet("/inquiry/*")
public class inquiryServlet extends MyServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String uri = req.getRequestURI();
		String cp = req.getContextPath();

		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");


		if (uri.indexOf("list.do") == -1 && info == null) {
			resp.sendRedirect(cp + "/member/login.do");
			return;
		}		
		
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
		} 	
		
	}
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		inquiryDAO dao = new inquiryDAO();
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
			
			// 게시글 리스트 
			List<inquiryDTO> list = null;
			list = dao.listInquiry(offset, size);
			
			String listUrl = cp + "/inquiry/list.do?size=" + size;
			String articleUrl = cp + "/inquiry/article.do?page=" + current_page + "&size=" + size;
 

			String paging = util.paging(current_page, total_page, listUrl);
			
			
			// 포워딩 전달할 데이터
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
		forward(req, resp, "/WEB-INF/views/inquiry/list.jsp");
	}
	
	protected void writeForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 작성	
		
	}
	
	protected void writeSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 저장
		
	}
	protected void article(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 보기
		
	}	
	protected void updateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 수정
		
	}	
	protected void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 수정 저장
		
	}
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 삭제
		
	}	
	
	
}
