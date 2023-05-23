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
		// 상품 추가
		PreparedStatement pstmt = null;
		String sql;

		try {
			conn.setAutoCommit(false);

			sql = "INSERT INTO items (itemNo, itemName, price, discount, cnt, saleUnit, hitCount, description, "
					+ "createDate, updateDate, deadline, categoryNo, brandNo) "
					+ "VALUES (items_seq.NEXTVAL, ?, ?, ?, ?,?, 0, ?, SYSDATE, SYSDATE, ?, ?, ?)";

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

			pstmt.executeUpdate();

			pstmt.close();
			pstmt = null;

			if (dto.getSaveFiles() != null) {
				sql = "INSERT INTO itemsImg (imgNo, itemNo,saveFilename, createDate, updateDate) "
						+ "VALUES (itemsImg_seq.NEXTVAL, items_seq.CURRVAL, ?, SYSDATE, SYSDATE)";
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
			if (pstmt != null) {
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
		// 상품 갯수
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

	public List<AdminDTO> listProduct(int offset, int size) {
		List<AdminDTO> list = new ArrayList<AdminDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {

			sb.append(
					" SELECT i.itemNo, saveFilename, i.itemName, i.price, i.createDate, i.updateDate, categoryName, brandName");
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
					+ " FROM items " + " WHERE itemNo = ?";

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
				sql = "INSERT INTO itemsImg (imgNo, itemNo,saveFilename, createDate, updateDate) "
						+ "VALUES (itemsImg_seq.NEXTVAL, ?, ?, SYSDATE, SYSDATE)";
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

	public List<AdminDTO> listStock(int offset, int size) {
		List<AdminDTO> list = new ArrayList<AdminDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {

			sb.append(" SELECT i.itemNo, saveFilename, i.itemName, i.price,cnt, categoryName, brandName");
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
			sb.append("  ORDER BY cnt ASC ");
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
				dto.setCnt(rs.getInt("cnt"));
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

	public List<AdminDTO> listBrand(int offset, int size) {
		List<AdminDTO> list = new ArrayList<AdminDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {

			sb.append("SELECT brandNo, brandName ");
			sb.append("FROM brand ");
			sb.append("ORDER BY brandNo ASC ");
			sb.append("OFFSET ? ROWS FETCH FIRST ? ROWS ONLY ");

			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setInt(1, offset);
			pstmt.setInt(2, size);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminDTO dto = new AdminDTO();

				dto.setBrandNo(rs.getInt("brandNo"));
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

	public int brandCount() {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT NVL(COUNT(*), 0) FROM brand";
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

	public void addBrands(AdminDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;

		try {
			conn.setAutoCommit(false);

			sql = "INSERT INTO brand (brandNo, brandName) VALUES (?,?)";

			pstmt = conn.prepareStatement(sql);

			// PreparedStatement에 값을 설정합니다.
			pstmt.setLong(1, dto.getBrandNo());
			pstmt.setString(2, dto.getBrandName());

			pstmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e2) {
			}
			e.printStackTrace();
			throw e;
		} finally {
			if (pstmt != null) {
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

	public int memberCount() {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT NVL(COUNT(*), 0) FROM client";
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

	public List<AdminDTO> listMember(int offset, int size) {
		List<AdminDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append(
					"SELECT m.clientNo, m.memberId, m.pwd, m.email, m.gender, m.name, m.tel, a.addrNo, a.addressCode, a.address, a.addressDetail, c.createDate, c.updateDate, c.deleteDate, c.gubun");
			sb.append(" FROM member m");
			sb.append(" JOIN client c ON m.clientNo = c.clientNo");
			sb.append(" JOIN addressinfo a ON c.clientNo = a.clientNo");
			sb.append(" ORDER BY m.clientNo ASC");
			sb.append(" OFFSET ? ROWS FETCH FIRST ? ROWS ONLY");

			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setInt(1, offset);
			pstmt.setInt(2, size);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminDTO dto = new AdminDTO();

				dto.setClientNo(rs.getLong("clientNo"));
				dto.setMemberId(rs.getString("memberId"));
				dto.setPwd(rs.getString("pwd"));
				dto.setEmail(rs.getString("email"));

				dto.setGender(rs.getString("gender"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setAddrNo(rs.getLong("addrNo"));
				dto.setAddressCode(rs.getString("addressCode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddressDetail(rs.getString("addressDetail"));
				dto.setCreateDate(rs.getString("createDate"));
				dto.setUpdateDate(rs.getString("updateDate"));
				dto.setDeleteDate(rs.getString("deleteDate"));
				dto.setGubun(rs.getString("gubun"));

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

	public void deleteMember(long clientNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "DELETE FROM addressinfo WHERE clientNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, clientNo);
			pstmt.executeUpdate();
			pstmt.close();

			sql = "DELETE FROM perinquiries WHERE clientNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, clientNo);
			pstmt.executeUpdate();
			pstmt.close();

			sql = "DELETE FROM orderDetail WHERE clientNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, clientNo);
			pstmt.executeUpdate();
			pstmt.close();

			sql = "DELETE FROM review WHERE clientNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, clientNo);
			pstmt.executeUpdate();
			pstmt.close();

			sql = "DELETE FROM ordering WHERE clientNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, clientNo);
			pstmt.executeUpdate();
			pstmt.close();

			sql = "DELETE FROM client WHERE clientNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, clientNo);
			pstmt.executeUpdate();
			pstmt.close();

			sql = "DELETE FROM member WHERE clientNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, clientNo);

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

	public AdminDTO readMember(long clientNo) {
		AdminDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT m.clientNo, m.memberId, m.pwd, m.email, m.gender, m.name, m.tel, a.addrNo, a.addressCode, a.address, a.addressDetail, c.createDate, c.updateDate, c.deleteDate, c.gubun"
					+ " FROM member m " + " JOIN client c ON m.clientNo = c.clientNo"
					+ " JOIN addressinfo a ON c.clientNo = a.clientNo" + " WHERE m.clientNo = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, clientNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new AdminDTO();
				dto.setClientNo(rs.getLong("clientNo"));
				dto.setMemberId(rs.getString("memberId"));
				dto.setPwd(rs.getString("pwd"));
				dto.setEmail(rs.getString("email"));
				dto.setGender(rs.getString("gender"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setAddrNo(rs.getLong("addrNo"));
				dto.setAddressCode(rs.getString("addressCode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddressDetail(rs.getString("addressDetail"));
				dto.setCreateDate(rs.getString("createDate"));
				dto.setUpdateDate(rs.getString("updateDate"));
				dto.setDeleteDate(rs.getString("deleteDate"));
				dto.setGubun(rs.getString("gubun"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

		return dto;
	}

	public List<AdminDTO> getDailyMemberCounts() {
		List<AdminDTO> clientCounts = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT createDate, COUNT(*) as count FROM client GROUP BY createDate ORDER BY createDate";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminDTO dto = new AdminDTO();

				dto.setCreateDate(rs.getString("createDate"));
				clientCounts.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clientCounts;
	}
}
