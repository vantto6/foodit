package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class AdminDAO {
	private Connection conn = DBConn.getConnection();
	
	public void insertItems(AdminDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		
		try {		 
			conn.setAutoCommit(false);
			
			sql = "INSERT INTO items (itemNo, itemName, price, discount, cnt, saleUnit, hitCount, description, " +
				      "createDate, updateDate, deadline, categoryNo, brandNo) " +
				      "VALUES (items_seq.NEXTVAL, ?, ?, ?, ?,?, 0, ?, SYSDATE, SYSDATE, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			// PreparedStatement에 값을 설정합니다.
			pstmt.setString(1, dto.getItemName());
			pstmt.setLong(2, dto.getPrice());
			pstmt.setLong(3, dto.getDiscount());
			pstmt.setInt(4, dto.getCnt());
			pstmt.setString(5, dto.getSaleUnit());
			pstmt.setString(6, dto.getDescription());
			pstmt.setString(7, dto.getDeadline());
			pstmt.setInt(8, dto.getCategoryNo());
			pstmt.setInt(9, dto.getBrandNo());

			pstmt.executeUpdate();	

			pstmt.close();
			pstmt = null;
			
			if (dto.getSaveFiles() != null) {
				sql = "INSERT INTO itemsImg (imgNo, itemNo,saveFilename, createDate, updateDate) " +
				           "VALUES (itemsImg_seq.NEXTVAL, items_seq.CURRVAL, ?, SYSDATE, SYSDATE)";
				pstmt = conn.prepareStatement(sql);
				
				for (int i = 0; i < dto.getSaveFiles().length; i++) {
					pstmt.setString(1, dto.getSaveFiles()[i]);
					
					pstmt.executeUpdate();
				}
			}
		
			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e2) {
			}
			e.printStackTrace();
			throw e;
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e2) {
			}
		}
		
	}
	public List<AdminDTO> listProduct(long itemNo) {
		List<AdminDTO> list = new ArrayList<AdminDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			
			sql="SELECT i.itemNo,itemName, price, createDate, upadateDate"
					+ "  ,categoryNo ,brandNo"
					+ "  FROM items i"
					+ "  JOIN category c ON i.categoryNo = c.categoryNo "
					+ "  JOIN brand b ON i.brandNo = b.brandNo"
					+ "  WHERE i.itemNo = ? ";

			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminDTO dto = new AdminDTO();
				

				
				list.add(dto);
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

		return list;
	}	
		
	
}
