/*
 * 	주제(Subject): Java Spring JDBC에서 어노테이션(X) - 트랜젝션 구현
 *  작성일자(Create Date): 2020-10-09
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  파일명(Filename): AccountService.java
 *  비고(Description):
 * 
 * 
 */

package com.website.example.service;

import java.sql.SQLException;

import com.website.example.vo.AccountVO;

public interface AccountService {

	void accountCreate(AccountVO vo) throws SQLException;
	void accountTransfer(String sender, String receiver, int money) throws SQLException;
	
}
