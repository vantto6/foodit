package com.mypage;

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

@WebServlet("/mypage/*")
public class MypageServlet extends MyServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");

		if (info == null) {
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
			return;
		}
		
		
		// uri에 따른 작업 구분
		if(uri.indexOf("login_ok.do")!=-1) {
			loginSubmit(req, resp);
		} else if(uri.indexOf("modify_checkPw.do") != -1) {
			checkPw(req,resp);
		} else if(uri.indexOf("mypage.do") != -1) {
			orderList(req,resp);
		} else if(uri.indexOf("update_ok.do") != -1) {
			updateSubmit(req,resp);
		} else if(uri.indexOf("order.do") != -1) {
			orderList(req,resp);
		} else if(uri.indexOf("addr.do") != -1) {
			addrList(req,resp);
		} else if(uri.indexOf("emailCheck.do") != -1) {
			emailCheck(req,resp);
		} else if(uri.indexOf("addAddr.do") != -1) {
			insertAddr(req,resp);
		} else if(uri.indexOf("deleteAddr.do") != -1) {
			deleteAddr(req,resp);
		} else if(uri.indexOf("review.do") != -1) {
			reviewList(req,resp);
		}
 	}
	
	protected void mypageForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 마이페이지 폼 , 주문내역 창으로 바로 들어감
		String path = "/WEB-INF/views/mypage/order.jsp";
		
		forward(req, resp, path);
	}
	
	// 배송지 추가 등록 메소드
	protected void insertAddr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp = req.getContextPath();
		
		HttpSession session = req.getSession();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute("member");

		String str = sessionInfo.getMemberId();
		MypageDAO dao = new MypageDAO();
		String memberId = dao.bringClientNo(str);
		
		
		
		try {
			
			addrmanageDTO dto = new addrmanageDTO();
			
			
			dto.setAddressCode(req.getParameter("addressCode"));
			
			dto.setAddress(req.getParameter("address"));
			dto.setAddressDetail(req.getParameter("addressDetail"));
			System.out.println(dao.bringClientNo(str));
			dto.setClientNo(dao.bringClientNo(str));
			
			dao.insertAddr(dto);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect(cp+"/mypage/addr.do");
	}
	
	
	protected void checkPw(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 폼
		String path = "/WEB-INF/views/mypage/modify1.jsp";
		
		HttpSession session = req.getSession();
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
		
		MypageDAO dao = new MypageDAO();
		String cp = req.getContextPath();

		if(req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp + "/");
			return;
		}
		
		String memberId = req.getParameter("memberId");
		String pwd = req.getParameter("pwd");
		
		MemberDTO dto = dao.loginMember(memberId, pwd);
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
	
	protected void emailCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MypageDAO dao = new MypageDAO();
		
		String email = req.getParameter("email");
		boolean result = dao.emailCheck(email);
		
		String passed = "";
		if(result == false) {
			passed = "true";
		}
		
		JSONObject job = new JSONObject();
		job.put("passed", passed);
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(job.toString());
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
			
			
			
			dao.updateMember(dto);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect(cp+"/mypage/mypage.do");
				
	}
	
	protected void addrList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시물 리스트
		MypageDAO dao = new MypageDAO();
		MyUtil util = new MyUtil();

		String cp = req.getContextPath();
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		
		String memberId = info.getMemberId();
		
		try {
			String page = req.getParameter("page");
			int current_page = 1;
			if (page != null) {
				current_page = Integer.parseInt(page);
			}
		
			// 전체 데이터 개수
			int dataCount = dao.dataCount(memberId);
			
			// 전체 페이지 수
			int size = 10;
			int total_page = util.pageCount(dataCount, size);
			if (current_page > total_page) {
				current_page = total_page;
			}

			// 게시물 가져오기
			int offset = (current_page - 1) * size;
			if(offset < 0) offset = 0;
			
			List<addrmanageDTO> list = null;
			
			list = dao.listBoard(memberId, offset, size);

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
	protected void orderList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시물 리스트
		MypageDAO dao = new MypageDAO();
		MyUtil util = new MyUtil();
		
		String cp = req.getContextPath();
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		
		if(info==null) {
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
			return;
		}
		
		String memberId = info.getMemberId();
		
		
		try {
			String page = req.getParameter("page");
			int current_page = 1;
			if (page != null) {
				current_page = Integer.parseInt(page);
			}
			
			// 전체 데이터 개수
			int dataCount = dao.orderDataCount(memberId);
			
			// 전체 페이지 수
			int size = 3;
			int total_page = util.pageCount(dataCount, size);
			if (current_page > total_page) {
				current_page = total_page;
			}
			
			// 게시물 가져오기
			int offset = (current_page - 1) * size;
			if(offset < 0) offset = 0;
			
			List<orderDTO> list = null;
			
			list = dao.orderListBoard(memberId, offset, size);
			
			// 페이징 처리
			String listUrl = cp + "/mypage/order.do";
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
		forward(req, resp, "/WEB-INF/views/mypage/order.jsp");
	}
	protected void reviewList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시물 리스트
		MypageDAO dao = new MypageDAO();
		MyUtil util = new MyUtil();
		
		String cp = req.getContextPath();
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		
		String memberId = info.getMemberId();
		
		if(info==null) {
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
			return;
		}
		
		try {
			String page = req.getParameter("page");
			int current_page = 1;
			if (page != null) {
				current_page = Integer.parseInt(page);
			}
			
			// 전체 데이터 개수
			int dataCount = dao.reviewDataCount(memberId);
			
			// 전체 페이지 수
			int size = 3;
			int total_page = util.pageCount(dataCount, size);
			if (current_page > total_page) {
				current_page = total_page;
			}
			
			// 게시물 가져오기
			int offset = (current_page - 1) * size;
			if(offset < 0) offset = 0;
			
			List<reviewDTO> list = null;
			
			list = dao.reviewListBoard(memberId, offset, size);
			
			// 페이징 처리
			String listUrl = cp + "/mypage/review.do";
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
		forward(req, resp, "/WEB-INF/views/mypage/review.jsp");
	}
	
	protected void deleteAddr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp = req.getContextPath();
		String addrNo = req.getParameter("addrNo");
		
		try {
			MypageDAO dao = new MypageDAO();
			
			dao.deleteAddr(Integer.parseInt(addrNo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect(cp+"/mypage/addr.do");
		
	}
}
