package com.basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;


public class BasketDAO {
	Connection conn = DBConn.getConnection();
	public List<BasketDTO> listBasket(String memberId) {
		// 장바구니 목록 출력
		List<BasketDTO> list = new ArrayList<BasketDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" SELECT itemName,basketCnt, price, discount");
			sb.append(" FROM basket b ");
			sb.append(" JOIN items i ON b.itemNo = i.itemNo ");
			sb.append(" WHERE memberId = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BasketDTO dto = new BasketDTO();
				
				dto.setItemName(rs.getString("itemName"));
				dto.setBasketCnt(rs.getInt("basketCnt"));
				dto.setPrice(rs.getInt("price"));
				dto.setDiscount(rs.getInt("discount"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
				
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
		
		return list;
	}
	

}
