package com.itemInquiry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.inquiry.inquiryDTO;
import com.util.DBConn;

public class ItemInquiryDAO {
	private Connection conn = DBConn.getConnection();
	
	
	public void insertInquiry(ItemInquiryDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "INSERT INTO itemInquiry(inquiryNo,memberId,subject,content,createDate,updateDate,isSecret,itemNo) "
					+ "VALUES (itemInquiry_seq.NEXTVAL,?,?,?,SYSDATE,SYSDATE,1,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getMemberId());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setLong(4, dto.getItemNo());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
	
	}
	
	
	public ItemInquiryDTO readItemName(long itemNo) {
		ItemInquiryDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select brandName,itemName "
					+ "from items i "
					+ "join brand b on i.brandNo = b.brandNo "
					+ "where itemNo = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, itemNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ItemInquiryDTO();
				
				dto.setBrandName(rs.getString("brandName"));
				dto.setItemName(rs.getString("itemName"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return dto;
	}
	
	
	
	public List<ItemInquiryDTO> inquiryList(long itemNo, int offset, int size){
		List<ItemInquiryDTO> list = new ArrayList<ItemInquiryDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT i.itemName itemName, inquiryNo,iq.memberId,subject,content,TO_CHAR(iq.createDate,'YYYY-DD-MM') createDate,iq.updateDate updateDate,isSecret,iq.itemNo itemNo "
					+ "FROM itemInquiry iq "
					+ "JOIN items i ON i.itemNo = iq.itemNo "
					+ "WHERE i.itemNo = ? "
					+ "OFFSET ? ROWS FETCH FIRST ? ROWS ONLY ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, itemNo);
			pstmt.setInt(2, offset);
			pstmt.setInt(3, size);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemInquiryDTO dto = new ItemInquiryDTO();
				
				dto.setItemName(rs.getString("itemName"));
				dto.setInquiryNo(rs.getLong("inquiryNo"));
				dto.setMemberId(rs.getString("memberId"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setCreateDate(rs.getString("createDate"));
				dto.setUpdateDate(rs.getString("updateDate"));
				dto.setIsSecret(rs.getInt("isSecret"));
				dto.setItemNo(rs.getLong("itemNo"));
				list.add(dto);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
	
	// 상품별 문의 개수
	public int dataCount(long itemNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT NVL(COUNT(*), 0) FROM itemInquiry WHERE itemNo = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, itemNo);

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

}
