/*
 * 	주제(Subject): Java Spring JDBC에서 어노테이션(X) - 트랜젝션 구현
 *  작성일자(Create Date): 2020-10-09
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  파일명(Filename): MyDataSourceFactory.java
 *  비고(Description):
 * 
 * 
 */

package com.website.example.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

import oracle.jdbc.pool.OracleDataSource;

public class MyDataSourceFactory {
	
	private InputStream is = null;
    private Properties props = null;
	
	public MyDataSourceFactory()  {
		
        String resource = "db.properties";
        this.is = getClass().getClassLoader().getResourceAsStream(resource);
        this.props = new Properties();
        
	}
	
	public DataSource getMySQLDataSource() {
		
        MysqlDataSource mysqlDS = null;
        
        try {
        	
            props.load(is);
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
            mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return mysqlDS;
        
    }
     
    public DataSource getOracleDataSource(){
    	
        OracleDataSource oracleDS = null;
        
        try {
        	
            props.load(is);
            oracleDS = new OracleDataSource();
            oracleDS.setURL(props.getProperty("ORACLE_DB_URL"));
            oracleDS.setUser(props.getProperty("ORACLE_DB_USERNAME"));
            oracleDS.setPassword(props.getProperty("ORACLE_DB_PASSWORD"));
            
            
        } catch (IOException e) {
        	
            e.printStackTrace();
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
        }
        
        return oracleDS;
        
    }

}
