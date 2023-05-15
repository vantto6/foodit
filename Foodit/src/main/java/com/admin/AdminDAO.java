package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.DBConn;

public class AdminDAO {
	private Connection conn = DBConn.getConnection();
	
	public void insertItems(AdminDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		
		try {		
			sql = "INSERT INTO items (itemNo, itemName, price, discount, cnt, saleUnit, hitCount, description, " +
				      "createDate, updateDate, deadline, categoryNo, brandNo) " +
				      "VALUES (items_seq.NEXTVAL, ?, ?, ?, ?, 0, ?, SYSDATE, SYSDATE, ?, ?, brand_seq.NEXTVAL)";

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
					
			
			//sql = "INSERT INTO brand(brandNo, brandName ) VALUES (brand_seq.NEXTVAL,?)";
			//pstmt=conn.prepareStatement(sql);
			
			//pstmt.setString(1, dto.getBrandName());
			
			//pstmt.executeUpdate();
			
			/*
			sql = "INSERT INTO category(categoryNo, categoryName) VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getCategoryNo());
			pstmt.setString(2, dto.getCategoryName());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = null;
			*/

			

			/*
            sql = "INSERT INTO itemsImg(imgNo, saveFilename, thumbnail, fileSize, imgcreateDate, imgupdateDate, itemNo) VALUES (itemsImg_seq.NEXTVAL, ?, ?, ?, SYSDATE, SYSDATE)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, dto.getSaveFilename());
            pstmt.setInt(2, dto.getThumbnail());
            pstmt.setLong(3, dto.getFileSize());
          
			
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = null;
            

			*/
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

	
}
