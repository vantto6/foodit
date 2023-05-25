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
	
	public String readOrderNo(long clientNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String orderNo = null;
		String sql;
		
		try {
			sql = "SELECT orderNo FROM Ordering WHERE clientNo = ? AND confirm = 0";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, clientNo);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderNo = rs.getString("orderNo");
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

		return orderNo;
	}
	
	
	public MemberDTO readMember(String memberId) {
		MemberDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			sb.append("SELECT name,email,tel");
			sb.append(" FROM member");
			sb.append(" WHERE memberId = ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				dto = new MemberDTO();
				
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
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
		
		return dto;
	}
	
	
	public void insertOrder(OrderDTO dto) {
		PreparedStatement pstmt = null;
		
		String sql;
		try {
			sql = "INSERT INTO Ordering(orderNo, clientNo, addressCode, address, addressDetail, totPrice, cnt, recipient, tel, request, confirm)"
					+ " VALUES(Ordering_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, dto.getClientNo());
			pstmt.setString(2, dto.getAddressCode());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getAddressDetail());
			pstmt.setInt(5, dto.getTotPrice());
			pstmt.setInt(6, dto.getCnt());
			pstmt.setString(7, dto.getRecipient());
			pstmt.setString(8, dto.getTel());
			pstmt.setString(9, dto.getRequest());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
		}
		
		
	}
	
	public void insertOrderDetail(List<OrderDTO> orderDetailList) {
	    PreparedStatement pstmt = null;
	    String sql = "INSERT INTO OrderDetail(ordetailNo,orderNo,itemNo,ordetailCnt,price,payOption,payDate,disPrice)"
	                 + " VALUES(OrderDetail_seq.NEXTVAL, Order_seq.CURRVAL,?,?,?,?,?,?)";
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        
	        for (OrderDTO dto : orderDetailList) {
	            pstmt.setLong(1, dto.getOrderNo());
	            pstmt.setLong(2, dto.getItemNo());
	            pstmt.setLong(3, dto.getOrdetailCnt());
	            pstmt.setInt(4, dto.getPrice());
	            pstmt.setString(5, dto.getPayDate());
	            pstmt.setInt(6, dto.getDisPrice());
	            pstmt.setLong(7, dto.getClientNo());
	            
	            pstmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	
	public void updateConfirm() {
		PreparedStatement pstmt = null;
		
		String sql;
		try {
			sql = "UPDATE ordering SET confirm = 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
		}
		
		
	}


}
