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
			sb.append(" SELECT b.itemNo,basketNo,itemName,basketCnt, price, discount");
			sb.append(" FROM basket b ");
			sb.append(" JOIN items i ON b.itemNo = i.itemNo ");
			sb.append(" WHERE memberId = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BasketDTO dto = new BasketDTO();
				
				dto.setBasketNo(rs.getInt("basketNo"));
				dto.setItemNo(rs.getInt("itemNo"));
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
	
	public void deleteBasket(String[] itemsToDelete) {
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "DELETE FROM Basket WHERE basketNo = ?";
			pstmt = conn.prepareStatement(sql);
			for(String basketNo : itemsToDelete) {
				pstmt.setString(1, basketNo);
				
				pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
		
	}
	
	public List<AddressDTO> listAddress(long clientNo) {
		List<AddressDTO> list = new ArrayList<AddressDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT addressCode, address, addressdetail FROM addressinfo WHERE clientNo = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, clientNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AddressDTO dto = new AddressDTO();
				
				dto.setAddressCode(rs.getString("addressCode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddressDetail(rs.getString("addressdetail"));
				
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
