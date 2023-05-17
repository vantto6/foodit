package com.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBConn;

public class MemberDAO {
	private Connection conn = DBConn.getConnection();
	
	public MemberDTO loginMember(String userId, String userPwd) {
		MemberDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = " SELECT clientNo, memberId ,name, pwd "
					+ " FROM member"
					+ " WHERE memberId = ? AND pwd = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setClientNo(rs.getLong("clientNo"));
				dto.setMemberId(rs.getString("memberId"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
			
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
	public void insertMember(MemberDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		
		//insertAll로 다시 짜기 
		try {
			conn.setAutoCommit(false);
			
			sql = "INSERT ALL"
             + " INTO member (clientNo, memberId, pwd, name, gender,  email, tel) VALUES (client_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)"
             + " INTO client (clientNo,createDate,updateDate,deleteDate,gubun) VALUES (client_seq.CURRVAL, SYSDATE, SYSDATE, null, 1)"
             + " INTO Addressinfo (addrNo, addressCode, address, addressDetail, clientNo) VALUES (address_seq.NEXTVAL , ?, ?, ?, client_seq.CURRVAL)"
             + " SELECT * FROM dual";
             
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getMemberId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, Integer.parseInt(dto.getGender()));
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getTel());
			pstmt.setString(7, dto.getAddressCode());
			pstmt.setString(8, dto.getAddress());
			pstmt.setString(9, dto.getAddressDetail());
			
			
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
	
	
	
	public MemberDTO readMember(String memberId) {
		MemberDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			sb.append("SELECT m.clientNo, memberId, pwd, name, gender ,email, tel, addressCode, address, addressDetail ");
			sb.append(" FROM member m ");
			sb.append(" LEFT JOIN client c ON m.clientNo= c.clientNo ");
			sb.append(" LEFT JOIN addressinfo ai on c.clientNo = ai.clientNo ");
			sb.append(" WHERE m.memberId = ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				dto = new MemberDTO();
				
				dto.setClientNo(rs.getInt("clientNo"));
				dto.setMemberId(rs.getString("memberId"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				dto.setAddressCode(rs.getString("addressCode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddressDetail(rs.getString("addressDetail"));
				
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
	public void deleteMember(String memberId, long clientNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "DELETE FROM member WHERE memberId=? AND clientNo=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setLong(2, clientNo);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = null;
			
			sql = "DELETE FROM client WHERE clientNo=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, clientNo);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
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
	public Boolean emailCheck(String email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		Boolean result = false;
		
		try {
			sb.append("select count(*) as cnt "
					+ " from member"
					+ " where email = ? ");
			
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				
				if(rs.getInt("cnt") == 1) {
					result = true;
				}
				
			
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
		
	}

