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
			sb.append(" SELECT b.itemNo,basketNo,itemName,basketCnt, price, discount, cnt");
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
				dto.setDiscountPrice();
				dto.setCnt(rs.getInt("cnt"));
				
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
			
			if(itemsToDelete == null) {
				return;
			}
			
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
	
	public boolean inventoryCheck(String[] itemNo, int count) {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql;

	    try {
	        sql = "SELECT cnt FROM items WHERE itemNO = ?";
	        pstmt = conn.prepareStatement(sql);

	        for (String check : itemNo) {
	            pstmt.setString(1, check);
	            rs = pstmt.executeQuery();
	            if (rs.next()) {
	                int stock = rs.getInt("cnt");
	                if (stock < count) {
	                    return false; // 재고 부족
	                }
	            } else {
	                return false; // 아이템 번호에 해당하는 데이터가 없음
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return true; // 재고 확인 완료
	}


}
