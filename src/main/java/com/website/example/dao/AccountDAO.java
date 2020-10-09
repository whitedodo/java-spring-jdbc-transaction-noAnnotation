/*
 * 	주제(Subject): Java Spring JDBC에서 어노테이션(X) - 트랜젝션 구현
 *  작성일자(Create Date): 2020-10-09
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  파일명(Filename): AccountDAO.java
 *  비고(Description):
 * 
 * 
 */

package com.website.example.dao;

import java.sql.SQLException;

import com.website.example.vo.AccountVO;

public interface AccountDAO {
	
	void createAccount(AccountVO vo) throws SQLException;
	int getBalance(String name);
	void minus(String name, int money) throws SQLException;
	void plus(String name, int money) throws SQLException;
	
}
