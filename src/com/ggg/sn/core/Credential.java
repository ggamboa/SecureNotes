package com.ggg.sn.core;

import java.io.Serializable;

public class Credential implements Serializable {
	private String userid;
	private String password;
	
	public Credential(String userid, String password) {
		super();
		this.userid = userid;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Credential [userid=" + userid + ", password=" + password + "]";
	}
	
	
}
