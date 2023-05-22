package com.basket;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.SessionInfo;
import com.util.MyServlet;

@WebServlet("/basket/*")
public class BasketServlet extends MyServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); 
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		if(info==null) {
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
			return;
		}
		
		String uri = req.getRequestURI();
		
		if(uri.indexOf("cart.do") != -1) {
			cart(req, resp);
		} else if(uri.indexOf("order.do") != -1) {
			order(req, resp);
		} else if(uri.indexOf("cart_delete.do") != -1) {
			cart_delete(req, resp);
		}

	}
	
	protected void cart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BasketDAO dao = new BasketDAO();
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");

		try {
			String memberId = info.getMemberId();
			Long clientNo = info.getClientNo();
			List<BasketDTO> list = dao.listBasket(memberId);
			List<AddressDTO> addressList = dao.listAddress(clientNo);
			req.setAttribute("list", list);
			req.setAttribute("addressList", addressList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		forward(req, resp, "/WEB-INF/views/basket/cart.jsp");		
	}
	
	protected void order(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BasketDAO dao = new BasketDAO();
		String cp = req.getContextPath();
		/*
		try {
			int count = Integer.parseInt(req.getParameter("count"));
			String[] itemNo = req.getParameterValues("itemNo");
			if(! dao.inventoryCheck(itemNo, count)) {
				req.setAttribute("inven", "0");
				forward(req, resp, "/WEB-INF/views/basket/cart.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		forward(req, resp, "/WEB-INF/views/basket/order.jsp");
	}
	 
	protected void cart_delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BasketDAO dao = new BasketDAO();
		String cp = req.getContextPath();
		try {
			String[] itemsToDelete = req.getParameterValues("itemsToDelete");
			dao.deleteBasket(itemsToDelete);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect(cp + "/basket/cart.do");
	}
}
