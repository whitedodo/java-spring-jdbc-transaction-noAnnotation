/*
 * 	주제(Subject): Java Spring JDBC에서 어노테이션(X) - 트랜젝션 구현
 *  작성일자(Create Date): 2020-10-09
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  파일명(Filename): AccountRowMapper.java
 *  비고(Description):
 * 
 * 
 */

package com.website.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.website.example.vo.AccountVO;

public class AccountRowMapper implements RowMapper<AccountVO>  {

	@Override
	public AccountVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		AccountVO vo = new AccountVO();

		vo.setIdx(rs.getInt(1));
		vo.setName(rs.getString(2));
		vo.setBalance(rs.getInt(3));
		vo.setRegidate(rs.getTimestamp(4));
		
		return vo;
	}

}
