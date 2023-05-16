package com.mypage;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.MemberDAO;
import com.member.MemberDTO;
import com.member.SessionInfo;
import com.util.MyServlet;

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
			
		} else if(uri.indexOf("addrManage.do") != -1) {
			
		}
		
 	}
	
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
	protected void mypageForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 마이페이지 폼 , 주문내역 창으로 바로 들어감
 		String path = "/WEB-INF/views/mypage/order.jsp";
		
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
			MemberDAO dao = new MemberDAO();
			
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
}
