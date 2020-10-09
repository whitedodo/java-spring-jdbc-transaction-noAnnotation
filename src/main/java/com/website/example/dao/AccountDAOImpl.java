/*
 * 	주제(Subject): Java Spring JDBC에서 어노테이션(X) - 트랜젝션 구현
 *  작성일자(Create Date): 2020-10-09
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  파일명(Filename): AccountDAOImpl.java
 *  비고(Description):
 * 
 * 
 */

package com.website.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.website.example.common.MyDataSourceFactory;
import com.website.example.vo.AccountVO;

public class AccountDAOImpl implements AccountDAO {

	// Spring Framework - JDBC
	private JdbcTemplate jdbcTemplate = null;
	private DataSource ds = null;
	
	private final String INSERT = "insert into account_tbl(name, balance, regidate) values(?, ?, ?)";
	private final String SELECT_BALANCE = "select * from account_tbl where name = ?";
	private final String UPDATE_MINUS = "update account_tbl set balance = (select balance from account_tbl where name = ?) - ? " +
										" where name = ?";
	private final String UPDATE_PLUS = "update account_tbl set balance = (select balance from account_tbl where name = ?) + ? " + 
										" where name = ?";
	
	
	public AccountDAOImpl(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
		this.ds = ds;
	}
	
	public void createAccount(AccountVO vo) throws SQLException {
		
		TransactionSynchronizationManager.initSynchronization();	// 동기화
		Connection c = DataSourceUtils.getConnection(ds); //커넥션을 생성
		
		// 데이터 저장소 바인딩(트랜젝션)
		c.setAutoCommit(false); //트랜잭션을 시작
		
	    try{
		
	    	this.jdbcTemplate.update(INSERT, vo.getName(), vo.getBalance(), vo.getRegidate());
	    	c.commit(); // 커밋
	    	
	    }
	    catch(Exception e) {
	    	c.rollback(); // 예외가 발생하면 롤백
	    	
	    }finally {
	    	
	    	DataSourceUtils.releaseConnection(c, ds); // 스프링 유틸리티를 이용해 DB커넥션을 닫는다.
	    	TransactionSynchronizationManager.unbindResource(ds); // 동기화 작업을 종료하고
	    	TransactionSynchronizationManager.clearSynchronization();     // 정리한다.
	    	
	    }
		
	}
	
    public int getBalance(String name){
    	
    	Object[] args = {name};
    	
    	AccountVO vo = this.jdbcTemplate.queryForObject(SELECT_BALANCE, args, new AccountRowMapper());
        
        int result = vo.getBalance();
    	
        return result;
    }
     
    public void minus(String name, int money) throws SQLException{

    	// 예외 발생시키기
    	if(true){
    		throw new SQLException(); // 의도적 예외 발생
   	    }
    	
        this.jdbcTemplate.update(UPDATE_MINUS, name, money, name);
        
    }
     
    public void plus(String name, int money) throws SQLException{
    	    	
        this.jdbcTemplate.update(UPDATE_PLUS, name, money, name);
    }
	
}
