/*
 * 	주제(Subject): Java Spring JDBC에서 어노테이션(X) - 트랜젝션 구현
 *  작성일자(Create Date): 2020-10-09
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  파일명(Filename): MainTest.java
 *  비고(Description):
 * 
 * 
 */

package com.website.example.unit;

import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

import com.website.example.common.MyDataSourceFactory;
import com.website.example.service.AccountService;
import com.website.example.service.AccountServiceImpl;
import com.website.example.vo.AccountVO;

class MainTest {

	@Test
	void test() throws SQLException {

		MyDataSourceFactory sourceFactory = new MyDataSourceFactory();
		DataSource ds = sourceFactory.getOracleDataSource();
		
		AccountService service = new AccountServiceImpl(ds);
		AccountVO vo = new AccountVO();
		
		/*
		// 1. 계정 생성
		vo.setName("홍길동");
		vo.setBalance(10000);
		vo.setRegidate(Timestamp.valueOf("2020-01-20 10:05:20"));
		service.accountCreate(vo);
		
		// 2. 계정 생성
		vo.setName("홍길자");
		vo.setBalance(0);
		vo.setRegidate(Timestamp.valueOf("2020-01-20 22:05:20"));
		service.accountCreate(vo);
		*/
		
		// 3. 거래 처리
		service.accountTransfer("홍길동", "홍길자", 500);
		
		
	}

}
