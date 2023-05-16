package com.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.member.MemberDTO;
import com.util.DBConn;

public class MypageDAO {
	private Connection conn = DBConn.getConnection();

	// 마이페이지 회원정보 수정
		public void updateMember(MemberDTO dto) throws SQLException {
			PreparedStatement pstmt = null;
			String sql;
			
			try {
				sql = "UPDATE member SET pwd = ? ,email = ?, gender = ? ,name = ? ,tel = ?  WHERE memberId = ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, dto.getPwd());
				pstmt.setString(2, dto.getEmail());
				pstmt.setString(3, dto.getGender());
				pstmt.setString(4, dto.getName());
				pstmt.setString(5, dto.getTel());
				pstmt.setString(6, dto.getMemberId());
				
				System.out.println(dto.getMemberId() + dto.getPwd());
				
				pstmt.executeUpdate();
				
				/*
				 * pstmt.close(); pstmt = null;
				 * 
				 * sql = "UPDATE client SET updateDate = SYSDATE WHERE memberNo = ?"; pstmt =
				 * conn.prepareStatement(sql);
				 * 
				 * pstmt.setString(1, dto.getMemberId());
				 * 
				 * pstmt.executeUpdate();
				 */
				

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
	// 데이터 개수
	public int dataCount() {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT NVL(COUNT(*), 0) FROM addressinfo";
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

	

	// 게시물 리스트
	public List<addrmanageDTO> listBoard(int offset, int size) {
		List<addrmanageDTO> list = new ArrayList<addrmanageDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT addrNo, addressCode, address, addressDetail, clientNo FROM addressinfo ORDER BY addrNo OFFSET ? ROWS FETCH FIRST ? ROWS ONLY ";
			/*
			 * sb.append(" SELECT boardNum, b.userId, userName, ");
			 * sb.append("       subject, groupNum, orderNo, depth, hitCount,");
			 * sb.append("       TO_CHAR(reg_date, 'YYYY-MM-DD') reg_date ");
			 * sb.append(" FROM board b ");
			 * sb.append(" JOIN member1 m ON b.userId = m.userId ");
			 * sb.append(" ORDER BY groupNum DESC, orderNo ASC ");
			 * sb.append(" OFFSET ? ROWS FETCH FIRST ? ROWS ONLY ");
			 */

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, offset);
			pstmt.setInt(2, size);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				addrmanageDTO dto = new addrmanageDTO();

				dto.setAddress(rs.getString("address"));
				dto.setAddressDetail(rs.getString("addressDetail"));
				dto.setClientNo(rs.getString("clientNo"));
				
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


	// 이전글
	public addrmanageDTO preReadBoard(long groupNum, int orderNo, String condition, String keyword) {
		addrmanageDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			if (keyword != null && keyword.length() != 0) {
				sb.append(" SELECT boardNum, subject ");
				sb.append(" FROM board b ");
				sb.append(" JOIN member1 m ON b.userId = m.userId ");
				sb.append(" WHERE ( (groupNum = ? AND orderNo < ?) OR (groupNum > ?) ) ");
				if (condition.equals("all")) {
					sb.append("   AND ( INSTR(subject, ?) >= 1 OR INSTR(content, ?) >= 1 ) ");
				} else if (condition.equals("reg_date")) {
					keyword = keyword.replaceAll("(\\-|\\/|\\.)", "");
					sb.append("   AND ( TO_CHAR(reg_date, 'YYYYMMDD') = ? ) ");
				} else {
					sb.append("   AND ( INSTR(" + condition + ", ?) >= 1 ) ");
				}
				sb.append(" ORDER BY groupNum ASC, orderNo DESC ");
				sb.append(" FETCH FIRST 1 ROWS ONLY ");

				pstmt = conn.prepareStatement(sb.toString());
				
				pstmt.setLong(1, groupNum);
                pstmt.setInt(2, orderNo);
                pstmt.setLong(3, groupNum);
                pstmt.setString(4, keyword);
				if (condition.equals("all")) {
					pstmt.setString(5, keyword);
				}
			} else {
				sb.append(" SELECT boardNum, subject FROM board ");
				sb.append(" WHERE (groupNum = ? AND orderNo < ?) OR (groupNum > ?) ");
				sb.append(" ORDER BY groupNum ASC, orderNo DESC ");
				sb.append(" FETCH FIRST 1 ROWS ONLY ");

				pstmt = conn.prepareStatement(sb.toString());
				
				pstmt.setLong(1, groupNum);
                pstmt.setInt(2, orderNo);
                pstmt.setLong(3, groupNum);
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new addrmanageDTO();
				
				/*
				 * dto.setBoardNum(rs.getLong("boardNum"));
				 * dto.setSubject(rs.getString("subject"));
				 */
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

	// 다음글
	public addrmanageDTO nextReadBoard(long groupNum, int orderNo, String condition, String keyword) {
		addrmanageDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			if (keyword != null && keyword.length() != 0) {
				sb.append(" SELECT boardNum, subject ");
				sb.append(" FROM board b ");
				sb.append(" JOIN member1 m ON b.userId = m.userId ");
				sb.append(" WHERE ( (groupNum = ? AND orderNo > ?) OR (groupNum < ?) ) ");
				if (condition.equals("all")) {
					sb.append("   AND ( INSTR(subject, ?) >= 1 OR INSTR(content, ?) >= 1 ) ");
				} else if (condition.equals("reg_date")) {
					keyword = keyword.replaceAll("(\\-|\\/|\\.)", "");
					sb.append("   AND ( TO_CHAR(reg_date, 'YYYYMMDD') = ? ) ");
				} else {
					sb.append("   AND ( INSTR(" + condition + ", ?) >= 1 ) ");
				}
				sb.append(" ORDER BY groupNum DESC, orderNo ASC ");
				sb.append(" FETCH FIRST 1 ROWS ONLY ");

				pstmt = conn.prepareStatement(sb.toString());
				
				pstmt.setLong(1, groupNum);
                pstmt.setInt(2, orderNo);
                pstmt.setLong(3, groupNum);
				pstmt.setString(4, keyword);
				if (condition.equals("all")) {
					pstmt.setString(5, keyword);
				}
			} else {
				sb.append(" SELECT boardNum, subject FROM board ");
				sb.append(" WHERE (groupNum = ? AND orderNo > ?) OR (groupNum < ?) ");
				sb.append(" ORDER BY groupNum DESC, orderNo ASC ");
				sb.append(" FETCH FIRST 1 ROWS ONLY ");

				pstmt = conn.prepareStatement(sb.toString());
				
				pstmt.setLong(1, groupNum);
                pstmt.setInt(2, orderNo);
                pstmt.setLong(3, groupNum);
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new addrmanageDTO();
				
				/*
				 * dto.setBoardNum(rs.getLong("boardNum"));
				 * dto.setSubject(rs.getString("subject"));
				 */
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

}
