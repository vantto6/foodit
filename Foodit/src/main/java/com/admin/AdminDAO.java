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
	public List<AdminDTO> listProduct( int offset, int size) {
		List<AdminDTO> list = new ArrayList<AdminDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			
			sb.append(" SELECT i.itemNo, saveFilename, i.itemName, i.price, i.createDate, i.updateDate, categoryName, brandName");
			sb.append("  FROM items i ");								
			sb.append("  LEFT OUTER JOIN ( ");
			sb.append("     SELECT itemNo, saveFilename FROM ( ");
			sb.append("        SELECT imgNo, itemNo, saveFilename, ");
			sb.append("            ROW_NUMBER() OVER(PARTITION BY itemNo ORDER BY imgNo ASC) rank ");
			sb.append("        FROM itemsImg");
			sb.append("     ) WHERE rank = 1 ");
			sb.append(" ) img ON i.itemNo = img.itemNo ");			
			sb.append("  JOIN category c ON i.categoryNo = c.categoryNo");
			sb.append("  JOIN brand b ON i.brandNo = b.brandNo");
			sb.append("  ORDER BY itemNo DESC ");
			sb.append("  OFFSET ? ROWS FETCH FIRST ? ROWS ONLY ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, offset);
			pstmt.setInt(2, size);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminDTO dto = new AdminDTO();
				
				dto.setItemNo(rs.getLong("itemNo"));
				dto.setSaveFilename(rs.getString("saveFilename"));
				dto.setItemName(rs.getString("itemName"));
				dto.setPrice(rs.getLong("price"));
				dto.setCreateDate(rs.getString("createDate"));
				dto.setUpdateDate(rs.getString("updateDate"));
				dto.setCategoryName(rs.getString("categoryName"));
				dto.setBrandName(rs.getString("brandName"));
				
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
		
	public AdminDTO readProduct(long itemNo) {
		AdminDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			
			sql = "SELECT itemNo, itemName, price, discount, cnt, saleunit, hitCount, description, createDate, updateDate, deadline, categoryNo, brandNo "
		            + " FROM items "
		            + " WHERE itemNo = ?";
			

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, itemNo);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new AdminDTO();
				
				dto.setItemNo(rs.getLong("itemNo"));
				dto.setItemName(rs.getString("itemName"));
				dto.setPrice(rs.getLong("price"));
				dto.setDiscount(rs.getLong("discount"));
				dto.setCnt(rs.getInt("cnt"));
				dto.setSaleUnit(rs.getString("saleUnit"));
				dto.setHitCount(rs.getInt("HitCount"));
				dto.setDescription(rs.getString("description"));
				dto.setCreateDate(rs.getString("createDate"));
				dto.setUpdateDate(rs.getString("updateDate"));
				dto.setDeadline(rs.getString("deadline"));
				dto.setCategoryNo(rs.getInt("categoryNo"));
				dto.setBrandNo(rs.getInt("brandNo"));
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

		return dto;
	}
	public AdminDTO readImageFile(long imgNo) {
		AdminDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT imgNo,saveFilename, itemNo FROM itemsimg WHERE imgNo = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, imgNo);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new AdminDTO();

				dto.setImgNo(rs.getLong("imgNo"));
				dto.setSaveFilename(rs.getString("saveFilename"));
				dto.setItemNo(rs.getLong("itemNo"));
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

		return dto;
	}
	public List<AdminDTO> listProductFile(long itemNo) {
		List<AdminDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT itemNo, imgNo, saveFilename FROM itemsimg WHERE itemNo = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, itemNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminDTO dto = new AdminDTO();

				
				dto.setItemNo(rs.getLong("itemNo"));
				dto.setImgNo(rs.getLong("imgNo"));
				dto.setSaveFilename(rs.getString("saveFilename"));
				
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
	public void updateProduct(AdminDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE items SET itemName=?, price=? ,discount=? ,cnt=? ,saleUnit=?, description=?, deadline=?, categoryNo=?, brandNo=? WHERE itemNo=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getItemName());
			pstmt.setLong(2, dto.getPrice());
			pstmt.setLong(3, dto.getDiscount());
			pstmt.setInt(4, dto.getCnt());
			pstmt.setString(5, dto.getSaleUnit());
			pstmt.setString(6, dto.getDescription());
			pstmt.setString(7, dto.getDeadline());
			pstmt.setInt(8, dto.getCategoryNo());
			pstmt.setInt(9, dto.getBrandNo());
			pstmt.setLong(10, dto.getItemNo());

			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = null;

			if (dto.getSaveFiles() != null) {
				sql = "INSERT INTO itemsImg (imgNo, itemNo,saveFilename, createDate, updateDate) " +
				           "VALUES (itemsImg_seq.NEXTVAL, ?, ?, SYSDATE, SYSDATE)";
				pstmt = conn.prepareStatement(sql);
				
				for (int i = 0; i < dto.getSaveFiles().length; i++) {
					pstmt.setLong(1, dto.getItemNo());
					pstmt.setString(2, dto.getSaveFiles()[i]);
					
					pstmt.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	public void delete(long itemNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "DELETE FROM items WHERE itemNo=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, itemNo);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	public void deleteImageFile(String mode, long itemNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;

	    try {
	        if (mode.equals("all")) {
	            sql = "DELETE FROM zzim WHERE itemNo = ?";
	            pstmt = conn.prepareStatement(sql);
	           pstmt.setLong(1, itemNo);
	            pstmt.executeUpdate();
	            pstmt.close();

	            sql = "DELETE FROM basket WHERE itemNo = ?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setLong(1, itemNo);
	            pstmt.executeUpdate();
	            pstmt.close();

	            sql = "DELETE FROM requires WHERE itemNo = ?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setLong(1, itemNo);
	            pstmt.executeUpdate();
	            pstmt.close();

	            sql = "DELETE FROM orderdetail WHERE itemNo = ?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setLong(1, itemNo);
	            pstmt.executeUpdate();
	            pstmt.close();

	            sql = "DELETE FROM itemsimg WHERE itemNo = ?";
	        } else {
	            sql = "DELETE FROM itemsimg WHERE imgNo = ?";
	        }
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setLong(1, itemNo);
	        pstmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e2) {
	                e2.printStackTrace();
	            }
	        }
	    }
	}
}
