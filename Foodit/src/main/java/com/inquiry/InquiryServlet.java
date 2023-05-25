package com.inquiry;

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

@WebServlet("/inquiry/*")
public class InquiryServlet extends MyServlet{
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
		}else if(uri.indexOf("delete.do")!= -1) {
			delete(req,resp);
		}else if(uri.indexOf("insertReply.do")!= -1) {
			insertReply(req,resp);
		}else if (uri.indexOf("listReply.do") != -1) {
			listReply(req, resp);
		}
	}
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InquiryDAO dao = new InquiryDAO();
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
			List<InquiryDTO> list = dao.listInquiry(offset, size);
			
			String listUrl = cp + "/inquiry/list.do";
			String articleUrl = cp + "/inquiry/article.do?page=" + current_page;
 

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
		// 글 작성폼	
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		String cp = req.getContextPath();
		
		
		if( info.getMemberId() == null ) {
			resp.sendRedirect(cp+"/inquiry/list.do");
			return;
		}
		
		req.setAttribute("mode", "write");

		forward(req, resp, "/WEB-INF/views/inquiry/write.jsp");
	}
	
	protected void writeSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 저장
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		String cp = req.getContextPath();
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp+"/WEB-INF/inquiry/list.do");
			return;
		}
		if( info.getMemberId() == null ) {
			resp.sendRedirect(cp+"/inquiry/list.do");
			return;
		}
		
		InquiryDAO dao = new InquiryDAO();
		
		try {
			
			InquiryDTO dto = new InquiryDTO();
			dto.setClientNo(info.getClientNo());
			if(req.getParameter("isPublic")!= null) {
				dto.setIsPublic(Integer.parseInt(req.getParameter("isPublic")));
			}
			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content"));
			
			dao.insertInquiry(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect(cp + "/inquiry/list.do");
		
	}
	protected void article(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글보기
		
		String cp = req.getContextPath();

		String page = req.getParameter("page");
		String query = "page="+page;
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		
		InquiryDAO dao = new InquiryDAO();
		
		try {
			long inquiryNo = Long.parseLong(req.getParameter("num"));
			
			InquiryDTO dto = dao.readInquiry(inquiryNo);
		
			if(dto == null) {
				resp.sendRedirect(cp+"/inquiry/list.do?"+query);
				return;
			}
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));

			if (dto.getIsPublic() == 1 && (!info.getMemberId().equals(dto.getMemberId()) && !info.getMemberId().equals("admin"))) {
			    String message = "비밀글입니다.";
			    resp.setContentType("text/html;charset=UTF-8");
			    PrintWriter out = resp.getWriter();
			    out.println("<script>alert('" + message + "'); location.href='" + cp + "/inquiry/list.do?" + query + "';</script>");
			    return;
			}


			req.setAttribute("dto", dto);
			req.setAttribute("query", query);
			req.setAttribute("page", page);
			
			forward(req,resp, "/WEB-INF/views/inquiry/article.jsp");
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/inquiry/list.do?" + query);
	}	
	
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 삭제
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		String cp = req.getContextPath();

		
		if(!info.getMemberId().equals("admin")) {
			resp.sendRedirect(cp+"/inquiry/list.do");
			return;
		}
		
		InquiryDAO dao = new InquiryDAO();
		
		String page = req.getParameter("page");
		String query = "page=" + page;
		try {
			long inquiryNo = Long.parseLong(req.getParameter("num"));

			InquiryDTO dto = dao.readInquiry(inquiryNo);
			
			if(dto == null) {
				resp.sendRedirect(cp+"/inquiry/list.do?" + query);
				return;
			}
			
			dao.deleteInquiry(inquiryNo);
		}catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(cp + "/inquiry/list.do?" + query);
	}	
	

	protected void insertReply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시글 댓글/답글 저장 : AJAX-JSON
		InquiryDAO dao = new InquiryDAO();
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		String state = "false";

		try {
			InquiryDTO dto = new InquiryDTO();
			
			long inquiryNo = Long.parseLong(req.getParameter("inquiryNo"));
			dto.setInquiryNo(inquiryNo);
			dto.setMemberId(info.getMemberId());
			String answer = req.getParameter("answer");
			dto.setAnswer(answer);
			
			//System.out.println("업데이트 전 : " + dto);
			
			dao.updateInquiry(dto);
			
			 
			dto = dao.readInquiry(dto.getInquiryNo());
			
			/*
			System.out.println("업데이트 후 : " + dto);
			
			List<InquiryDTO> list = dao.listInquiry(0, 20);
			
			System.out.println("포문 시작 ");

			for (InquiryDTO a : list) {
				System.out.println(a);
			}
			 */
			
			state = "true";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject job = new JSONObject();
		job.put("state", state);
		
		resp.setContentType("text/html;charset=utf-8"); // 안하면 한글 깨짐
		PrintWriter out = resp.getWriter();
		out.print(job.toString());

	}
	
	protected void listReply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시글 댓글 리스트 : AJAX-Text
		InquiryDAO dao = new InquiryDAO();
		MyUtil util = new MyUtil();
		
		try {
			long inquiryNo = Long.parseLong(req.getParameter("inquiryNo"));
			
			/*
			String pageNo = req.getParameter("pageNo");
			int current_page =1;
			if(pageNo != null) {
				current_page = Integer.parseInt(pageNo);
			}
			
			int size = 5;
			int total_page = 0;
			int replyCount = 0;
			
			replyCount =dao.dataCount();
			total_page = util.pageCount(replyCount, size);
			if(current_page > total_page) {
				current_page = total_page;
			}
			
			int offset = (current_page -1) * size;
			if(offset < 0)offset = 0;
			*/
			//List<InquiryDTO> listReply = dao.listInquiry(offset, size);
			InquiryDTO dto = dao.readInquiry(inquiryNo);
			if (dto.getAnswer() != null) {
				dto.setAnswer(dto.getAnswer().replaceAll("\n", "<br>"));
			}
			
			//for(InquiryDTO dto : listReply) {
			//	if (dto.getAnswer() != null) {
			//		dto.setAnswer(dto.getAnswer().replaceAll("\n", "<br>"));
			//	}
			//}
			
			//String paging = util.pagingMethod(current_page, total_page, "listPage");
			
			req.setAttribute("dto", dto);
			
			//req.setAttribute("pageNo", pageNo);
			//req.setAttribute("replyCount", replyCount);
			//req.setAttribute("total_page", total_page);
			//req.setAttribute("paging", paging);
			
			forward(req, resp, "/WEB-INF/views/inquiry/listReply.jsp");
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendError(400);
	}	
	
}
