package com.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.MemberDAO;
import com.member.MemberDTO;
import com.member.SessionInfo;
import com.util.MyServlet;
import com.util.MyUtil;

@WebServlet("/mypage/*")
public class MypageServlet extends MyServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();
		
		// uri에 따른 작업 구분
		if(uri.indexOf("login_ok.do")!=-1) {
			loginSubmit(req, resp);
		} else if(uri.indexOf("modify_checkPw.do") != -1) {
			checkPw(req,resp);
		} else if(uri.indexOf("mypage.do") != -1) {
			mypageForm(req,resp);
		} else if(uri.indexOf("update_ok.do") != -1) {
			updateSubmit(req,resp);
		} else if(uri.indexOf("order.do") != -1) {
			mypageForm(req,resp);
		} else if(uri.indexOf("addr.do") != -1) {
			list(req,resp);
		}
		
 	}
	
	protected void mypageForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 마이페이지 폼 , 주문내역 창으로 바로 들어감
		String path = "/WEB-INF/views/mypage/order.jsp";
		
		forward(req, resp, path);
	}
	/*
	 * protected void addrManageFORM(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException { // 마이페이지 폼 , 주문내역 창으로 바로 들어감
	 * String path = "/WEB-INF/views/mypage/addrManage.jsp";
	 * 
	 * forward(req, resp, path); }
	 */
	
	protected void checkPw(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 폼
		String path = "/WEB-INF/views/mypage/modify1.jsp";
		
		HttpSession session = req.getSession();
		System.out.println(session);
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute("member");

		String str = sessionInfo.getMemberId();
		
	    // 읽어온 속성 값 적용
	    req.setAttribute("username", str);
	    System.out.println(str);
		
		forward(req, resp, path);
	}

	protected void loginSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 처리
		String path = "/WEB-INF/views/mypage/modify2.jsp";
		
		MemberDAO dao = new MemberDAO();
		String cp = req.getContextPath();

		if(req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp + "/");
			return;
		}
		
		String memberId = req.getParameter("memberId");
		String pwd = req.getParameter("pwd");
		
		MemberDTO dto = dao.loginMember(memberId, pwd);
		System.out.println(memberId + pwd);
		if(dto != null) {
			
			HttpSession session = req.getSession();
			System.out.println(session);
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute("member");

			String str = sessionInfo.getMemberId();
			
		    // 읽어온 속성 값 적용
		    req.setAttribute("username", str);
		    System.out.println(str);
		    
			forward(req, resp, path);
			
			return;
		}
		
		// 로그인 실패인 경우(다시 로그인 폼으로 돌아감)
		
		checkPw(req,resp);
		
	}

	protected void memberSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	protected void pwdForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	protected void pwdSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	protected void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp = req.getContextPath();
		
		try {
			MemberDTO dto = new MemberDTO();
			MypageDAO dao = new MypageDAO();
			
			dto.setMemberId(req.getParameter("memberId"));
			dto.setPwd(req.getParameter("after_pwd"));
			dto.setName(req.getParameter("name"));
			dto.setEmail(req.getParameter("email"));
			dto.setGender(req.getParameter("gender"));
			dto.setTel(req.getParameter("tel1")+ req.getParameter("tel2")+ req.getParameter("tel3"));
			
			System.out.println(dto.getPwd() + dto.getTel());
			
			
			dao.updateMember(dto);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect(cp+"/");
				
	}

	protected void userIdCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}	
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시물 리스트
		MypageDAO dao = new MypageDAO();
		MyUtil util = new MyUtil();

		String cp = req.getContextPath();
		
		try {
			String page = req.getParameter("page");
			int current_page = 1;
			if (page != null) {
				current_page = Integer.parseInt(page);
			}
		
			// 전체 데이터 개수
			int dataCount = dao.dataCount();
			
			// 전체 페이지 수
			int size = 2;
			int total_page = util.pageCount(dataCount, size);
			if (current_page > total_page) {
				current_page = total_page;
			}

			// 게시물 가져오기
			int offset = (current_page - 1) * size;
			if(offset < 0) offset = 0;
			
			List<addrmanageDTO> list = null;
			
			list = dao.listBoard(offset, size);

			// 페이징 처리
			String listUrl = cp + "/mypage/addr.do";
			/* String articleUrl = cp + "/page/article.do?page=" + current_page; */

			String paging = util.paging(current_page, total_page, listUrl);

			// 포워딩할 JSP에 전달할 속성
			req.setAttribute("list", list);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("size", size);
			// req.setAttribute("articleUrl", articleUrl);
			req.setAttribute("paging", paging);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// JSP로 포워딩
		forward(req, resp, "/WEB-INF/views/mypage/addr.jsp");
	}
}
