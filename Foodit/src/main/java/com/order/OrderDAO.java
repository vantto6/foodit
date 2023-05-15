package com.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class OrderDAO {
	private Connection conn = DBConn.getConnection();
	
	public void insertOrder(OrderDTO dto) {
		// 주문정보 입력하기
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		String sql, sql2;
		try {
			sql = "INSERT INTO Ordering(orderNo, clientNo, addressCode, address, addressDetail, totPrice, confirm, field)"
					+ " VALUES(Ordering_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, dto.getClientNo());
			pstmt.setString(2, dto.getAddressCode());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getAddressDetail());
			pstmt.setInt(5, dto.getTotPrice());
			pstmt.setInt(6, dto.getConfirm());
			pstmt.setInt(7, dto.getField());
			
			pstmt.executeUpdate();
			
			sql2 = "INSERT INTO OrderDetail(ordetailNo,orderNo,itemNo,ordetailCnt,price,payOption,payDate,disPrice)"
					+ " VALUES(OrderDetail_seq.NEXTVAL, ?,?,?,?,?,?,?)";
			
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setLong(1, dto.getOrderNo());
			pstmt2.setLong(2, dto.getItemNo());
			pstmt2.setLong(3, dto.getOrdetailCnt());
			pstmt2.setInt(4, dto.getPrice());
			pstmt2.setString(5, dto.getPayOption());
			pstmt2.setString(6, dto.getPayDate());
			pstmt2.setInt(7, dto.getDisPrice());
			
			pstmt2.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (pstmt2 != null)
				try {
					pstmt2.close();
				} catch (SQLException e) {
				}
		}
		
		
	}
	
	public List<OrderDTO> listOrder(int clientNo){
		List<OrderDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT address, addressDetail, itemName, totprice, field, price, disprice"
					+ " FROM orderDetail od"
					+ " JOIN ordering o"
					+ " ON od.orderNo = o.orderNo"
					+ " JOIN Items i"
					+ " ON od.itemNo = i.itemNo"
					+ " WHERE clientNo = ?";
			
			pstmt.setInt(1, clientNo);
		} catch (Exception e) {
			
		}
		
		
		return list;
		
	}

	
	
	

}
