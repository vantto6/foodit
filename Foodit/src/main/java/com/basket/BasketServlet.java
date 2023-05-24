package com.basket;

import java.io.IOException;
import java.util.ArrayList;
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
	OrderDTO oDto = new OrderDTO();
	List<OrderDTO> orderDetailList = new ArrayList<>();
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
		} else if(uri.indexOf("order_ok.do") != -1) {
			order_complete(req, resp);
		} else if(uri.indexOf("home.do") != -1) {
			back_home(req, resp);
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
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
			
		try {
			
			String memberId = info.getMemberId();
			List<BasketDTO> list = dao.listBasket(memberId);
			String[] count = req.getParameterValues("count");
			String address = req.getParameter("address");
			String addressDetail = req.getParameter("addressDetail");
			String addressCode = req.getParameter("addressCode");
			oDto.setClientNo(info.getClientNo());
			oDto.setAddressCode(addressCode);
			oDto.setAddress(address);
			oDto.setAddressDetail(addressDetail);
			oDto.setTotPrice(Integer.parseInt(req.getParameter("totalDiscountPrice")));
			Long clientNo = info.getClientNo();
			
			int[] itemNo = new int[list.size()];
			int[] price = new int[list.size()];
			int[] disprice = new int[list.size()];
			int i = 0;
			
			for(BasketDTO dto : list) {
				itemNo[i] = dto.getItemNo();
				price[i] = dto.getPrice();
				disprice[i] = dto.getDiscountPrice();
				i++;
			}
			
			oDto.setCnt(i);
			
			int orderNo = Integer.parseInt(dao.readOrderNo(memberId));
			int[] realCount = new int[i];
			
			for(int j=0;j<=i;j++) {
				OrderDTO dto = new OrderDTO();
				realCount[j] = Integer.parseInt(count[j]);
				dto.setItemNo(itemNo[j]);
				dto.setPrice(price[j]);
				dto.setDisPrice(disprice[j]);
				dto.setOrderNo(orderNo);
				dto.setOrdetailCnt(realCount[j]);
				dto.setClientNo(clientNo);
				
				orderDetailList.add(dto);
			}
			
			MemberDTO dto = dao.readMember(memberId);
			oDto.setTel(dto.getTel());
			oDto.setRecipient(dto.getName());
			req.setAttribute("list", list);
			req.setAttribute("count", count);
			req.setAttribute("address", address);
			req.setAttribute("addressDetail", addressDetail);
			req.setAttribute("addressCode", addressCode);
			req.setAttribute("name", dto.getName());
			req.setAttribute("email", dto.getEmail());
			req.setAttribute("tel", dto.getTel());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		forward(req, resp, "/WEB-INF/views/basket/orderForm.jsp");
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
	
	protected void order_complete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BasketDAO dao = new BasketDAO();
		try {
			oDto.setRequest(req.getParameter("request"));
			dao.insertOrder(oDto);
			dao.insertOrderDetail(orderDetailList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		forward(req, resp, "/WEB-INF/views/basket/orderComplete.jsp");
	}
	
	protected void back_home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forward(req, resp, "/WEB-INF/views/main/main.jsp");
	}
}
