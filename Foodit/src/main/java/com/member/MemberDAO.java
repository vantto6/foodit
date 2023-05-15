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
			sql = " SELECT memberId, pwd,email , birth, gender,name,tel "
					+ " FROM member"
					+ " WHERE memberId = ? AND pwd = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO();
				
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
		
		try {
			conn.setAutoCommit(false);
			
			sql = "INSERT INTO member(memberNo, memberId, pwd, gender, email, tel) VAULES (member_seq.NEXTVAL,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getMemberId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getGender());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getTel());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = null;
			
			sql = "INSERT INTO Addressinfo(clientNo, addressCode, address, addressDetail)"
					+ " VALUES (client_seq.NEXTVAL, member_seq.NEXTVAL, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = null;
			
			
			sql = "INSERT INTO client(key2, clientNo, addressCode, address, addressDetail, tel) "
					+ "VALUES (key2_seq.NEXTVAL, client_seq.CURRVAL, ?, ?, ?, ?)";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAddressCode());
			pstmt.setString(2, dto.getAddress());
			pstmt.setString(3, dto.getAddressDetail());
			
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
			sb.append("SELECT m.memberNo, memberId, pwd, name, ");
			sb.append("      gender ,email, tel, addressCode, address, addressDetail");
			sb.append("  FROM member m");
			sb.append("  LEFT OUTER JOIN client c ON m.memberNo= c.memberNo ");
			sb.append("  WHERE m.memberId = ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO();
				
				dto.setMemberId(rs.getString("memberId"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				if(dto.getTel() != null) {
					String[] ss = dto.getTel().split("-");
					if(ss.length == 3) {
						dto.setTel1(ss[0]);
						dto.setTel2(ss[1]);
						dto.setTel3(ss[2]);
					}
				}
				dto.setAddressCode(rs.getString("addressCode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddressDetail(rs.getString("addressDetail"));
				dto.setPwd(rs.getString("pwd"));
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
	
	public void deleteMember(String userId) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "";
			pstmt = conn.prepareStatement(sql);
			
			
			
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = null;
			
			sql = "DELETE FROM client WHERE memberNo=?";
			pstmt = conn.prepareStatement(sql);
			
			
			
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
	
		
		
	}

