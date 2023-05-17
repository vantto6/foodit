package com.member;

public class memberDAOTest {
	public static void main(String[] args) {
		
		MemberDAO dao = new MemberDAO();
		
		String emailExist = "admin@test.com";
		String emailNotExist = "in@test.co";
		
		System.out.println(dao.emailCheck(emailExist));
		System.out.println(dao.emailCheck(emailNotExist));
		
		
		
		
	}
}
