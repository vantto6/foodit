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
	
	// 카테고리가 있는 상품 테이터 리스트
	public List<ItemDTO> listBoard(int category, int offset, int size) {
		List<ItemDTO> list = new ArrayList<ItemDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append("SELECT i.itemNo itemNo,brandName,categoryName,itemName,price,discount,saleUnit,description,deadline ");
			sb.append(" FROM items i ");
			sb.append(" JOIN brand b ON i.brandNo = b.brandNo");
			sb.append(" JOIN category c ON i.categoryNo = c.categoryNo");
			sb.append( " WHERE i.categoryNo = ? ");
			sb.append(" OFFSET ? ROWS FETCH FIRST ? ROWS ONLY ");

			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, category);
			pstmt.setInt(2, offset);
			pstmt.setInt(3, size);

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
	
	// 신상품 상품 리스트
	public List<ItemDTO> listBoard2(int num, int offset, int size) {
		List<ItemDTO> list = new ArrayList<ItemDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append("SELECT i.itemNo itemNo,brandName,categoryName,itemName,price,discount,saleUnit,description,deadline ");
			sb.append(" FROM items i ");
			sb.append(" JOIN brand b ON i.brandNo = b.brandNo");
			sb.append(" JOIN category c ON i.categoryNo = c.categoryNo");
			sb.append( " WHERE TRUNC(SYSDATE - createDate) < 5 ");
			sb.append(" OFFSET ? ROWS FETCH FIRST ? ROWS ONLY ");

			pstmt = conn.prepareStatement(sb.toString());
			

			pstmt.setInt(1, offset);
			pstmt.setInt(2, size);

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
	
	// 베스트 상품 리스트
	public List<ItemDTO> listBoard3(int num, int offset, int size) {
		List<ItemDTO> list = new ArrayList<ItemDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT i.itemNo itemNo,brandName,categoryName,itemName,price,discount,saleUnit,description,deadline "
					+ "FROM items i "
					+ "JOIN brand b ON i.brandNo = b.brandNo "
					+ "JOIN category c ON i.categoryNo = c.categoryNo "
					+ "WHERE i.itemNo IN (select itemNo "
					+ "                from (select itemNo, COUNT(*) cnt "
					+ "                      from zzim "
					+ "                      group by itemNo "
					+ "                      order by cnt desc) where rownum <= 10)"
					+ " OFFSET ? ROWS FETCH FIRST ? ROWS ONLY ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, size);
			
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
	
	// 신상품 데이터 개수
	public int newdataCount() {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select COUNT(*) from items  "
					+ "WHERE TRUNC(SYSDATE - createDate) < 5 ";
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

	
	// 베스트 데이터 개수
	public int bestItemCount() {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select COUNT(*) "
					+ "from (select itemNo, COUNT(*) cnt "
					+ "      from zzim "
					+ "      group by itemNo "
					+ "      order by cnt desc)  "
					+ "where rownum <= 10";
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
	
	// 카테고리별 데이터 개수
	public int dataCount(int category) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT NVL(COUNT(*), 0) FROM items WHERE categoryNo = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, category);

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
	// 그냥 보기
	public ItemDTO readItem(long itemNo) {
		ItemDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "SELECT i.itemNo itemNo,brandName,categoryName,itemName,price,discount,saleUnit,description,deadline"
					+ " FROM items i "
					+ " JOIN brand b ON i.brandNo = b.brandNo"
					+ " JOIN category c ON i.categoryNo = c.categoryNo "
					+ " WHERE i.itemNo = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, itemNo);

			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ItemDTO();
				

				dto.setItemNo(rs.getLong("itemNo"));
				dto.setBrandName(rs.getString("brandName"));
				dto.setCategoryName(rs.getString("categoryName"));
				dto.setItemName(rs.getString("itemName"));
				dto.setPrice(rs.getLong("price"));
				dto.setDiscount(rs.getLong("discount"));
				dto.setSaleUnit(rs.getString("saleUnit"));
				dto.setDescription(rs.getString("description"));
				dto.setDeadline(rs.getString("deadline"));
				dto.setDiscountPrice();
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
	
	// 상품보기 (카테고리 상품눌렀을때)
	public ItemDTO readItem(int category,long itemNo) {
		ItemDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "SELECT i.itemNo itemNo,brandName,categoryName,itemName,price,discount,saleUnit,description,deadline"
					+ " FROM items i "
					+ " JOIN brand b ON i.brandNo = b.brandNo"
					+ " JOIN category c ON i.categoryNo = c.categoryNo "
					+ " WHERE i.itemNo = ? AND i.categoryNo = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, itemNo);
			pstmt.setInt(2, category);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ItemDTO();
				
				dto.setCategoryNo(category);
				dto.setItemNo(rs.getLong("itemNo"));
				dto.setBrandName(rs.getString("brandName"));
				dto.setCategoryName(rs.getString("categoryName"));
				dto.setItemName(rs.getString("itemName"));
				dto.setPrice(rs.getLong("price"));
				dto.setDiscount(rs.getLong("discount"));
				dto.setSaleUnit(rs.getString("saleUnit"));
				dto.setDescription(rs.getString("description"));
				dto.setDeadline(rs.getString("deadline"));
				dto.setDiscountPrice();
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
	

	// 상품보기 (베스트)
	public ItemDTO bestReadItem(long itemNo) {
		ItemDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "SELECT i.itemNo itemNo,brandName,categoryName,itemName,price,discount,saleUnit,description,deadline"
					+ " FROM items i "
					+ " JOIN brand b ON i.brandNo = b.brandNo"
					+ " JOIN category c ON i.categoryNo = c.categoryNo "
					+ " WHERE i.itemNo = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, itemNo);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ItemDTO();
				
				dto.setItemNo(rs.getLong("itemNo"));
				dto.setBrandName(rs.getString("brandName"));
				dto.setCategoryName(rs.getString("categoryName"));
				dto.setItemName(rs.getString("itemName"));
				dto.setPrice(rs.getLong("price"));
				dto.setDiscount(rs.getLong("discount"));
				dto.setSaleUnit(rs.getString("saleUnit"));
				dto.setDescription(rs.getString("description"));
				dto.setDeadline(rs.getString("deadline"));
				dto.setDiscountPrice();
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

	// 상품보기 (신제품)
	public ItemDTO newReadItem(long itemNo) {
		ItemDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "SELECT i.itemNo itemNo,brandName,categoryName,itemName,price,discount,saleUnit,description,deadline"
					+ " FROM items i "
					+ " JOIN brand b ON i.brandNo = b.brandNo"
					+ " JOIN category c ON i.categoryNo = c.categoryNo "
					+ " WHERE i.itemNo = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, itemNo);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ItemDTO();
				
				dto.setItemNo(rs.getLong("itemNo"));
				dto.setBrandName(rs.getString("brandName"));
				dto.setCategoryName(rs.getString("categoryName"));
				dto.setItemName(rs.getString("itemName"));
				dto.setPrice(rs.getLong("price"));
				dto.setDiscount(rs.getLong("discount"));
				dto.setSaleUnit(rs.getString("saleUnit"));
				dto.setDescription(rs.getString("description"));
				dto.setDeadline(rs.getString("deadline"));
				dto.setDiscountPrice();
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
	
	
	public int checkbasket(long itemNo, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT NVL(COUNT(*), 0) FROM basket WHERE itemNo = ? AND memberId = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, itemNo);
			pstmt.setString(2, memberId);
			
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
	
	public void insertBasket(ItemDTO dto) {
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			conn.setAutoCommit(false);
			
			sql = "INSERT INTO basket(basketNo,basketCnt,memberId,itemNo) VALUES(basket_seq.NEXTVAL,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, dto.getBasketCnt());
			pstmt.setString(2, dto.getMemberId());
			pstmt.setLong(3, dto.getItemNo());
			
			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e2) {
			}
			e.printStackTrace();
		}finally {
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
	
	// 상품의 공감 추가
	public void insertItemLike(long itemNo, String memberId) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "INSERT INTO zzim(itemNo, memberId) VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, itemNo);
			pstmt.setString(2, memberId);
			
			pstmt.executeUpdate();
			
			
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

		}
		
	}
	
	// 상품 공감 삭제
	public void deleteItemLike(long itemNo, String memberId) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "DELETE FROM zzim WHERE itemNo = ? AND memberId = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, itemNo);
			pstmt.setString(2, memberId);
			
			pstmt.executeUpdate();
			


		} catch (SQLException e) {

			e.printStackTrace();
			throw e;
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}

		}
		
	}
	
	
	// 상품의 공감 개수
	public int countItemLike(long itemNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT NVL(COUNT(*), 0) FROM zzim WHERE itemNo=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, itemNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
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
		
		return result;
	}
	
	// 로그인 유저의 게시글 공감여부
	public boolean isMemberitemLike(long itemNo, String memberId) {
		boolean result = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT itemNo, memberId FROM zzim WHERE itemNo = ? AND memberId = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, itemNo);
			pstmt.setString(2, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return result;
	}
	

}
