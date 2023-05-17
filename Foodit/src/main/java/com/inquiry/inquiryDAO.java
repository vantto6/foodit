package com.inquiry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class inquiryDAO {

	private Connection conn = DBConn.getConnection();
	
	// 질문등록
	public void insertInquiry(inquiryDTO dto) throws SQLException{
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "INSERT INTO inquiry(inquiryNo, clientNo, subject, content, inquiryDate, isPublic ) "
					+ " VALUES(inquiry_seq.NEXTVAL, ?, ?, ?, SYSDATE, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, dto.getClientNo());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setLong(4, dto.getIsPublic());
			
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
	
	// 총 데이터 개수
	public int dataCount( ) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT COUNT(*) FROM inquiry";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
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
	
	// 클라이언트 데이터 개수
	public int dataCount(int clientNo ) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT COUNT(*) FROM inquiry WHERE clientNo= ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, clientNo);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
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
	
	
	// 게시물 리스트
	public List<inquiryDTO> listInquiry(int offset, int size){
		List<inquiryDTO> list = new ArrayList<inquiryDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql ="SELECT inquiryNo, clientNo, content, subject, inquiryDate, isPublic, answer, answerDate "
					+ " FROM inquiry "
					+ " ORDER BY inquiryNo DESC "
					+ " OFFSET ? ROWS FETCH FIRST ? ROWS ONLY ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, size);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				inquiryDTO dto = new inquiryDTO();
				
				dto.setInquiryNo(rs.getLong("inquiryNo"));
				dto.setClientNo(rs.getLong("clientNo"));
				dto.setContent(rs.getString("content"));
				dto.setSubject(rs.getString("subject"));
				dto.setInquiryDate(rs.getString("inquiryDate"));
				dto.setIsPublic(rs.getInt("isPublic"));
				dto.setAnswer(rs.getString("answer"));
				dto.setAnswerDate(rs.getString("answerDate"));
				
				list.add(dto);
			}
			
		}  catch (SQLException e) {
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
	
	// 게시물 보기
	public inquiryDTO readInquiry(long inquiryNo){
		inquiryDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "SELECT inquiryNo, clientNo, content, subject, inquiryDate, isPublic, answer, answerDate "
					+ " FROM inquiry "
					+ " WHERE inquiryNo =?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, inquiryNo);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new inquiryDTO();
				
				dto.setInquiryNo(rs.getLong("inquiryNo"));
				dto.setClientNo(rs.getLong("clientNo"));
				dto.setContent(rs.getString("content"));
				dto.setSubject(rs.getString("subject"));
				dto.setInquiryDate(rs.getString("inquiryDate"));
				dto.setIsPublic(rs.getInt("isPublic"));
				dto.setAnswer(rs.getString("answer"));
				dto.setAnswerDate(rs.getString("answerDate"));
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
	
	
	public void updateInquiry(inquiryDTO dto) throws SQLException{
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql ="UPDATE inquiry SET subject=?, content =? inquiryDate= SYSDATE, isPublic, answer=?, answerDate=?  WHERE inquiryNo =? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getIsPublic());
			pstmt.setString(4, dto.getAnswer());
			pstmt.setString(5, dto.getAnswerDate());
			pstmt.setLong(6, dto.getInquiryNo());
		
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
	 
	
	public void deleteInquiry(long inquiryNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "DELETE FROM inquiry WHERE inquiryNo = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, inquiryNo);
			
			pstmt.executeUpdate();
			
		}  catch (SQLException e) {
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
}
