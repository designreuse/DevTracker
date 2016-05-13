package com.tmt.logistics.dao;

import java.util.List;

import com.tmt.logistics.bean.LoginReportDetail;

public interface LoginReportDao {
	
	public List<LoginReportDetail> retrieveLoginData();
	
	public void updateLoginData(String userName, String status);
	
}
