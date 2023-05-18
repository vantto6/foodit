package com.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.item.ItemDAO;
import com.util.MyServlet;

@WebServlet("/main.do")
public class MainServlet extends MyServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uri=req.getRequestURI();
		
		if(uri.indexOf("main.do") != -1) {
			mainList(req, resp);
		}
	}
	
	protected void mainList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		mainItemDAO dao = new mainItemDAO();
		ItemDAO itemdao = new ItemDAO();
		String cp = req.getContextPath();
		
		
		try {
			List<mainItemDTO> newItemList = dao.listNewItem();
			int dataCount = itemdao.newdataCount();
			
			
			List<mainItemDTO> dicountItemList = dao.listDiscountItem();
			req.setAttribute("cp", cp);
			req.setAttribute("newItemList", newItemList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("dicountItemList", dicountItemList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String path = "/WEB-INF/views/main/main.jsp";
		forward(req, resp, path);
		
	}
}
