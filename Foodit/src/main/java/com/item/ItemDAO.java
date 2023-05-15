package com.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class ItemDAO {
	private Connection conn = DBConn.getConnection();
	
	public List<ItemDTO> listBoard() {
		List<ItemDTO> list = new ArrayList<ItemDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append("SELECT i.itemNo itemNo,brandName,categoryName,itemName,price,discount,saleUnit,description,deadline ");
			sb.append(" FROM items i ");
			sb.append(" JOIN brand b ON i.brandNo = b.brandNo");
			sb.append(" JOIN category c ON i.categoryNo = c.categoryNo");

			pstmt = conn.prepareStatement(sb.toString());
			
//			pstmt.setInt(1, offset);
//			pstmt.setInt(2, size);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ItemDTO dto = new ItemDTO();
					
				dto.setItemNo(rs.getLong("itemNo"));
				dto.setBrandName(rs.getString("brandName"));
				dto.setCategoryName(rs.getString("categoryName"));
				dto.setItemName(rs.getString("itemName"));
				dto.setPrice(rs.getLong("price"));
				dto.setDiscount(rs.getLong("discount"));
				dto.setSaleUnit(rs.getString("saleUnit"));
				dto.setDescription(rs.getString("description"));
				dto.setDeadline(rs.getString("deadline"));
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
	
	
	// 데이터 개수
	public int dataCount() {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT NVL(COUNT(*), 0) FROM items";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}

		return result;
	}
	
	
	
	// 상품보기 (상품눌렀을때)
	public ItemDTO readItem(long itemNo) {
		ItemDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "SELECT brandName,categoryName,itemName,price,discount,saleUnit,description,deadline"
					+ " FROM items i "
					+ " JOIN brandNo b ON i.brandNo = b.brandNo"
					+ " JOIN categoryNo c ON i.categoryNo = c.categoryNo "
					+ " WHERE itemNo = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, itemNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ItemDTO();
				
				dto.setBrandName(rs.getString("brandName"));
				dto.setCategoryName(rs.getString("categoryName"));
				dto.setItemName(rs.getString("itemName"));
				dto.setPrice(rs.getLong("price"));
				dto.setDiscount(rs.getLong("discount"));
				dto.setSaleUnit(rs.getString("saleUnit"));
				dto.setDescription(rs.getString("description"));
				dto.setDeadline(rs.getString("deadline"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}

		}
		
		
		return dto;
	}

}
