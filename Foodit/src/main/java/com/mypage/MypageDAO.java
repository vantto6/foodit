package com.mypage;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class MypageDAO {
	private Connection conn = DBConn.getConnection();
	
	// 개인정보 수정 로그인 체크
		public MemberDTO pwdCheck(String userId, String userPwd) {
			MemberDTO dto = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;
			
			try {
				sql = " SELECT memberId, pwd "
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
	// 개인정보 수정을 페이지 이메일 중복 체크
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
		
	// 배송지 데이터 개수
	public int dataCount(String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT NVL(COUNT(*), 0) FROM addressinfo WHERE clientNo = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bringClientNo(memberId));
			
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
	
	public int orderDataCount(String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT NVL(COUNT(*), 0) FROM ordering od"
					+ " JOIN client c ON od.clientno = c.clientno"
					+ " JOIN member m ON c.clientno = m.clientno"
					+ " WHERE m.memberid = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
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
	
	// 후기 데이터 개수
	public int reviewDataCount(String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT NVL(COUNT(*), 0) FROM ITEMINQUIRY "
					+ " WHERE memberId = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
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
	public List<AddrDTO> addrListBoard(String memberId, int offset, int size) {
		List<AddrDTO> list = new ArrayList<AddrDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT addrNo, address, addressDetail, clientNo FROM addressinfo WHERE clientNo = ? ORDER BY addrNo OFFSET ? ROWS FETCH FIRST ? ROWS ONLY ";
			

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bringClientNo(memberId));
			pstmt.setInt(2, offset);
			pstmt.setInt(3, size);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				AddrDTO dto = new AddrDTO();
				
				dto.setAddrNo(rs.getInt("addrNo"));
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
	
	public List<OrderingDTO> orderListBoard(String memberId, int offset, int size) {
		List<OrderingDTO> list = new ArrayList<OrderingDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT itemName, odt.orderNo, totPrice, saveFilename, payDate, od.cnt"
					+ " FROM ordering od"
					+ " JOIN orderdetail odt ON od.orderNo = odt.orderNo"
					+ " JOIN items i ON odt.itemNo = i.itemNo"
					+ " JOIN itemsimg img ON i.itemNo = img.itemNo"
					+ " JOIN client c ON od.clientno = c.clientno"
					+ " JOIN member m ON c.clientno = m.clientno"
					+ " WHERE odt.ordetailno = ("
					+ "  SELECT MIN(ordetailno)"
					+ "  FROM orderdetail"
					+ "  WHERE orderNo = od.orderNo"
					+ " ) AND m.memberid = ? "
					+ " ORDER BY od.orderNo DESC"
					+ " OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setInt(2, offset);
			pstmt.setInt(3, size);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrderingDTO dto = new OrderingDTO();

				dto.setItemName(rs.getString("itemName"));
				dto.setOrderNo(rs.getInt("orderNo"));
				dto.setTotPrice(rs.getInt("totPrice"));
				dto.setSaveFilename(rs.getString("saveFilename"));
				dto.setPayDate(rs.getString("payDate"));
				dto.setCnt(rs.getInt("cnt"));
				
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
	
	public List<InquiryDTO> reviewListBoard(String memberId, int offset, int size) {
		List<InquiryDTO> list = new ArrayList<InquiryDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT inquiryNo, subject, iq.createDate, itemName, iq.ItemNo FROM ITEMINQUIRY iq"
					+ " JOIN items it ON iq.itemNo = it.itemNo"
					+ " WHERE memberId = ?"
					+ " ORDER BY inquiryNo DESC"
					+ " OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setInt(2, offset);
			pstmt.setInt(3, size);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				InquiryDTO dto = new InquiryDTO();
				
				dto.setInquiryNo(rs.getInt("inquiryNo"));
				dto.setSubject(rs.getString("subject"));
				dto.setCreateDate(rs.getString("createDate"));
				dto.setItemName(rs.getString("itemName"));
				dto.setItemNo(rs.getInt("ItemNo"));
				
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
	
	
	
	// memberId로 clientNO 가져오기
	public String bringClientNo(String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		String clientNo = "";
		
		try {
			sql = "SELECT clientNo FROM member WHERE memberId = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				clientNo = rs.getString("clientNo");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (Exception e2) {
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
					}
				}
		}
		
		
		return clientNo;
	}
	
	// 배송지 추가
	public void insertAddr(AddrDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "INSERT INTO Addressinfo(addrNo, addressCode, address, addressDetail, clientNo) VALUES(addressInfo_seq.NEXTVAL, ?, ?, ?, ?)";
             
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAddressCode());
			pstmt.setString(2, dto.getAddress());
			pstmt.setString(3, dto.getAddressDetail());
			pstmt.setString(4, dto.getClientNo());
			
			pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if(pstmt != null) {
						pstmt.close();
				}
			} catch (SQLException e) {
			}
			
		}
		}
	
	public void deleteAddr(int addrNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "DELETE FROM Addressinfo WHERE addrNo = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, addrNo);
			
			pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
			}
			
		}
		}
	

}
