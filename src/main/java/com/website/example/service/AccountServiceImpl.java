/*
 * 	주제(Subject): Java Spring JDBC에서 어노테이션(X) - 트랜젝션 구현
 *  작성일자(Create Date): 2020-10-09
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  파일명(Filename): AccountServiceImpl.java
 *  비고(Description):
 * 
 * 
 */

package com.website.example.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.website.example.dao.AccountDAO;
import com.website.example.dao.AccountDAOImpl;
import com.website.example.vo.AccountVO;

public class AccountServiceImpl implements AccountService{

	private AccountDAO accountDAO;
	private DataSource ds = null;
	
	public AccountServiceImpl(DataSource ds) {
		this.accountDAO = new AccountDAOImpl(ds);
		this.ds = ds;
	}

	@Override
	public void accountCreate(AccountVO vo) throws SQLException {
		this.accountDAO.createAccount(vo);		
	}
	
	@Override
	public void accountTransfer(String sender, String receiver, int money) throws SQLException {
		
    	int balance = accountDAO.getBalance(sender); // 보내는 사람 잔액 체크
    	Connection conn = null;
    	
        if(balance >= money){ // 보내는 돈이 잔액보다 많으면

        	TransactionSynchronizationManager.initSynchronization(); // 트랜잭션 동기화 작업 초기화

        	conn = ds.getConnection();
        	conn.setAutoCommit(false);
        	
        	try {
	        	accountDAO.minus(sender, money);
				accountDAO.plus(receiver, money);
	        	
				conn.commit();	// 성공
				
        	}catch(SQLException e) {
        		conn.rollback();	// 실패
        	}
        	finally {
        		// 커넥션 종료(Spring)
        		DataSourceUtils.releaseConnection(conn, this.ds);
        		
                // 동기화 작업을 종료하고 저장소를 비운다
                TransactionSynchronizationManager.clearSynchronization();
        	}
            
        } else{

        	System.out.println("돈 없음");
        	//throw new NoMoneyException();
        }
		
	}


}
