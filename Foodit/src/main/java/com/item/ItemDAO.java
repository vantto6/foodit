package com.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBConn;

public class ItemDAO {
	private Connection conn = DBConn.getConnection();
	
	// 상품보기 (상품눌렀을때)
	public ItemDTO readItem(long itemNo) {
		ItemDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		return dto;
	}

}
