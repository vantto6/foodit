package com.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.MyServlet;
@WebServlet("/order/*")
public class OrderServlet extends MyServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
	      
		String uri = req.getRequestURI();
  
		// uri에 따른 작업 구분
	  if(uri.indexOf("main.do") != -1) {
		  list(req, resp);
	  } else if(uri.indexOf("list.do") != -1) {
	     list(req, resp);
	  } else if(uri.indexOf("delete.do") != -1) {
	     delete(req, resp);
	  }
		
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	private void list(HttpServletRequest req, HttpServletResponse resp) {
		
		
	}

	private void order(HttpServletRequest req, HttpServletResponse resp) {
		
		
	}

}
