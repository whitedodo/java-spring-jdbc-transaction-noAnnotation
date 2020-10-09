package com.website.example.vo;

import java.sql.Timestamp;;

public class AccountVO {
	
	private int idx;
	private String name;
	private int balance;
	private Timestamp regidate;
	
	public int getIdx() {
		return idx;
	}
	
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Timestamp getRegidate() {
		return regidate;
	}
	
	public void setRegidate(Timestamp regidate) {
		this.regidate = regidate;
	}
	
}
