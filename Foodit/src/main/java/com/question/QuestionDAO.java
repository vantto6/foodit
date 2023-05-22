package com.question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class QuestionDAO {
	
	private Connection conn = DBConn.getConnection();
	
	
	public void insertQuestion(QuestionDTO dto)throws SQLException{
		PreparedStatement pstmt = null;
		String sql;
		
		
		try {
			
			sql = "INSERT INTO question(questionNo, category, subject, content)VALUES(question_seq.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getCategory());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			
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
	
	public int dataCount() {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT COUNT(*) FROM question";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
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
		return result;
	}
	
	public QuestionDTO readQuestion(long questionNo) {
		QuestionDTO dto = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			
			sql = "SELECT questionNo, category, subject, content FROM question WHERE questionNo = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, questionNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new QuestionDTO();
				dto.setQuestionNo(rs.getLong("questionNo"));
				dto.setCategory(rs.getString("category"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
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

	public List<QuestionDTO> listQuestion(int offset, int size){
		List<QuestionDTO> list = new ArrayList<QuestionDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT questionNo, category, subject, content FROM question "
					+ " ORDER BY questionNo DESC "
					+ " OFFSET ? ROWS FETCH FIRST ? ROWS ONLY ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, offset);
			pstmt.setInt(2, size);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QuestionDTO dto = new QuestionDTO();
				
				dto.setQuestionNo(rs.getLong("questionNo"));
				dto.setCategory(rs.getString("category"));
				dto.setContent(rs.getString("content"));
				dto.setSubject(rs.getString("subject"));
				
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
	
	public List<QuestionDTO> listQuestion(){
		List<QuestionDTO>list = new ArrayList<QuestionDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT questionNo, category, subject, content FROM question ORDER BY questionNo DESC ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QuestionDTO dto = new QuestionDTO();
				
				dto.setQuestionNo(rs.getLong("questionNo"));
				dto.setCategory(rs.getString("category"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				
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
	
	public void updateQuestion(QuestionDTO dto)throws SQLException{
		PreparedStatement pstmt = null;
		String sql;
		
		
		try {
			sql = "UPDATE question SET category =?, subject=?, content=? WHERE questionNo =?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getCategory());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setLong(4, dto.getQuestionNo());
			pstmt.executeUpdate();
			
		}catch (SQLException e) {
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
	
	
	public void deleteQuestionList(long[] nums) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "DELETE FROM question WHERE questionNo IN (";
			for (int i = 0; i < nums.length; i++) {
				sql += "?,";
			}
			sql = sql.substring(0, sql.length() - 1) + ")";

			pstmt = conn.prepareStatement(sql);
			
			for (int i = 0; i < nums.length; i++) {
				pstmt.setLong(i + 1, nums[i]);
			}

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
				}
			}
		}

	}
	
	
	
}
