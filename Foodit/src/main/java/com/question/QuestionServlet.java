package com.question;

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

@WebServlet("/question/*")
public class QuestionServlet extends MyServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();
		
		if(uri.indexOf("list.do")!= -1) {
			list(req, resp);
		}else if(uri.indexOf("write.do")!= -1) {
			writeForm(req,resp);
		}else if(uri.indexOf("write_ok.do")!= -1) {
			writeSubmit(req,resp);
		}else if(uri.indexOf("update.do")!= -1) {
			updateForm(req,resp);
		}else if(uri. indexOf("update_ok.do")!= -1) {
			updateSubmit(req,resp);
		}else if (uri.indexOf("deleteList.do") != -1) {
			deleteList(req, resp);
		}
	}
	
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    QuestionDAO dao = new QuestionDAO();
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
			int size = 5;
			int total_page = util.pageCount(dataCount, size);
			if(current_page > total_page) {
				current_page = total_page;
			}
			
			// 게시글 가져오기
			int offset = (current_page - 1) * size;
			if(offset < 0) offset = 0;
	    	
		    List<QuestionDTO> list = dao.listQuestion(offset, size);
			
			
	        for (QuestionDTO question : list) {
	            String content = question.getContent();
	            content = content.replaceAll("\n", "<br>");
	            question.setContent(content);
	        }

			String listUrl = cp + "/question/list.do";
 

			String paging = util.paging(current_page, total_page, listUrl);
	        
			req.setAttribute("list", list);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("page", current_page);
			req.setAttribute("size", size);
			req.setAttribute("total_page", total_page);
			req.setAttribute("paging", paging);
	        
	        forward(req, resp, "/WEB-INF/views/question/list.jsp");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	    
	}

	protected void writeForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mode", "write");
		forward(req, resp, "/WEB-INF/views/question/write.jsp");
	}


	protected void writeSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp = req.getContextPath();
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp + "/WEB-INF/question/list.do");
			return;
		}
		
		QuestionDAO dao = new QuestionDAO();
		try {
			QuestionDTO dto = new QuestionDTO();
			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content"));
			dto.setCategory(req.getParameter("category"));
			
			dao.insertQuestion(dto);
			
			req.setAttribute("dto", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect(cp + "/question/list.do?");
	}
	protected void updateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		String cp = req.getContextPath();
		
		if(!info.getMemberId().equals("admin")) {
			resp.sendRedirect(cp+"/question/list.do");
			return;
		}		
		
		
		QuestionDAO dao = new QuestionDAO();
		String page = req.getParameter("page");

		try {
			
			long questionNo = Long.parseLong(req.getParameter("nums"));
			
			QuestionDTO dto = dao.readQuestion(questionNo);
			if(dto == null) {
				resp.sendRedirect(cp+"/question/list.do?page="+page);
				return;
			}
			
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);

			req.setAttribute("mode", "update");

			forward(req, resp, "/WEB-INF/views/question/write.jsp");
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		resp.sendRedirect(cp+"/question/list.do");
	}
	
	protected void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		String cp = req.getContextPath();

		if(req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp+"/question/list.do");
			return;
		}
		
		if(!info.getMemberId().equals("admin")) {
			resp.sendRedirect(cp + "/question/list.do");
			return;
		}
		
		QuestionDAO dao = new QuestionDAO();
		String page = req.getParameter("page");

		try {
			
			QuestionDTO dto = new QuestionDTO();
			dto.setQuestionNo(Long.parseLong(req.getParameter("num")));
			dto.setCategory(req.getParameter("category"));
			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content"));
			
			dao.updateQuestion(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect(cp+"/question/list.do?page="+page);

	}
	
	
	protected void deleteList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		String cp = req.getContextPath();

		if (!info.getMemberId().equals("admin")) {
			resp.sendRedirect(cp + "/question/list.do");
			return;
		}
		

		String page = req.getParameter("page");
		String query ="&page=" + page;

		try {
			 
			String[] nn = req.getParameterValues("nums");
			long nums[] = null;
			nums = new long[nn.length];
			for (int i = 0; i < nn.length; i++) {
				nums[i] = Long.parseLong(nn[i]);
			}

			QuestionDAO dao = new QuestionDAO();

			dao.deleteQuestionList(nums);

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/question/list.do?" + query);
	}

		
	
	
}
