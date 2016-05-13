package com.tmt.logistics.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.logistics.bean.LoginReportDetail;

public class LoginReportDaoImpl implements LoginReportDao {

	@Autowired
	DataSource dataSource;

	public List<LoginReportDetail> retrieveLoginData() {
		String sql = "select username, password, phone, status from login;";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<LoginReportDetail> loginReportList  = jdbcTemplate.query(sql, new BeanPropertyRowMapper(LoginReportDetail.class));	
		return loginReportList;
	}
	
	public void updateLoginData(String userName, String status){
		String sql = "update login set status= ? where username= ? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, new Object[] {status, userName});
	}

}
