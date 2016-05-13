package com.tmt.logistics.dao;

import com.tmt.logistics.bean.LoginBean;

public interface LoginDao {
	
	public LoginBean isSigninAllowed(String username, String password);

	//public boolean signupUser(String username, String password, String phone);

	public boolean forgotPassword(String email);

}
