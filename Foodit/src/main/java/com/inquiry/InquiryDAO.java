package com.inquiry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class InquiryDAO {

	private Connection conn = DBConn.getConnection();
	
	// 질문등록.
	public void insertInquiry(InquiryDTO dto) throws SQLException{
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "INSERT INTO perInquiries(inquiryNo, clientNo, subject, content, inquiryDate, isPublic ) "
					+ " VALUES(perInquiries_seq.NEXTVAL, ?, ?, ?, SYSDATE, ?)";
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
			sql = "SELECT COUNT(*) FROM perInquiries";
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
	
	// 답글 개수
	public int dataCount(long inquiryNo ) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT  NVL(COUNT(*), 0) FROM perInquiries WHERE inquiryNo= ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, inquiryNo);
			
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
	public List<InquiryDTO> listInquiry(int offset, int size){
		List<InquiryDTO> list = new ArrayList<InquiryDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql =" SELECT inquiryNo, p.clientNo, content, subject,  TO_CHAR(inquiryDate, 'YYYY-MM-DD')inquiryDate, isPublic, answer,  TO_CHAR(answerDate, 'YYYY-MM-DD')answerDate, memberId "
					+ "     FROM perInquiries p "
					+ "     JOIN member m ON p.clientNo = m.clientNo "
					+ "		ORDER BY inquiryNo DESC "
					+ "		OFFSET ? ROWS FETCH FIRST ? ROWS ONLY ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, size);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				InquiryDTO dto = new InquiryDTO();

				dto.setInquiryNo(rs.getLong("inquiryNo"));
				dto.setClientNo(rs.getLong("clientNo"));
				dto.setContent(rs.getString("content"));
				dto.setSubject(rs.getString("subject"));
				dto.setInquiryDate(rs.getString("inquiryDate"));
				dto.setIsPublic(rs.getInt("isPublic"));
				dto.setAnswer(rs.getString("answer"));
				dto.setAnswerDate(rs.getString("answerDate"));
				dto.setMemberId(rs.getString("memberId"));
				
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
	public InquiryDTO readInquiry(long inquiryNo){
		InquiryDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql =" SELECT inquiryNo, p.clientNo, content, subject, TO_CHAR(inquiryDate, 'YYYY-MM-DD')inquiryDate, isPublic, answer, TO_CHAR(answerDate, 'YYYY-MM-DD')answerDate, memberId "
					+ "     FROM perInquiries p "
					+ "     JOIN member m ON p.clientNo = m.clientNo "
					+ " WHERE inquiryNo =?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, inquiryNo);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new InquiryDTO();
				
				dto.setInquiryNo(rs.getLong("inquiryNo"));
				dto.setClientNo(rs.getLong("clientNo"));
				dto.setContent(rs.getString("content"));
				dto.setSubject(rs.getString("subject"));
				dto.setInquiryDate(rs.getString("inquiryDate"));
				dto.setIsPublic(rs.getInt("isPublic"));
				dto.setAnswer(rs.getString("answer"));
				dto.setAnswerDate(rs.getString("answerDate"));
				dto.setMemberId(rs.getString("memberId"));

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
	
	
	public void updateInquiry(InquiryDTO dto) throws SQLException{
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql ="UPDATE perInquiries SET answer=?, answerDate=SYSDATE  WHERE inquiryNo =? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAnswer());
			pstmt.setLong(2, dto.getInquiryNo());
		
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
			
			sql = "DELETE FROM perInquiries WHERE inquiryNo = ?";
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
