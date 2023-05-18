package com.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.util.DBConn;

public class mainItemDAO {
	private Connection conn = DBConn.getConnection();
	
	public List<mainItemDTO> listNewItem() {
		List<mainItemDTO> list = new ArrayList<mainItemDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append("SELECT i.itemNo itemNo,brandName,categoryName,itemName,price,discount,saleUnit,description,deadline ");
			sb.append(" FROM items i ");
			sb.append(" JOIN brand b ON i.brandNo = b.brandNo");
			sb.append(" JOIN category c ON i.categoryNo = c.categoryNo");
			sb.append( " WHERE TRUNC(SYSDATE - createDate) < 5 ");

			pstmt = conn.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				mainItemDTO dto = new mainItemDTO();
					
				dto.setItemNo(rs.getLong("itemNo"));
				dto.setBrandName(rs.getString("brandName"));
				dto.setItemName(rs.getString("itemName"));
				dto.setPrice(rs.getLong("price"));
				dto.setDiscount(rs.getLong("discount"));
				dto.setDiscountPrice();
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
				}
			}
		}

		return list;
	}
	
	public List<mainItemDTO> listDiscountItem() {
		List<mainItemDTO> list = new ArrayList<mainItemDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			
			sql = "SELECT i.itemNo itemNo,brandName,categoryName,itemName,price,discount,saleUnit,description,deadline "
					+ "FROM items i "
					+ "JOIN brand b ON i.brandNo = b.brandNo "
					+ "JOIN category c ON i.categoryNo = c.categoryNo "
					+ "WHERE i.itemNo IN ( select * from "
					+ "                    (select itemNo  "
					+ "                      from items "
					+ "                      order by discount desc) where rownum <= 10)";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				mainItemDTO dto = new mainItemDTO();
					
				dto.setItemNo(rs.getLong("itemNo"));
				dto.setBrandName(rs.getString("brandName"));
				dto.setItemName(rs.getString("itemName"));
				dto.setPrice(rs.getLong("price"));
				dto.setDiscount(rs.getLong("discount"));
				dto.setDiscountPrice();
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
				}
			}
		}

		return list;
	}
}
