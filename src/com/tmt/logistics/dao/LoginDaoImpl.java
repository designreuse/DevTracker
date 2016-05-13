package com.tmt.logistics.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tmt.logistics.bean.LoginBean;

public class LoginDaoImpl implements LoginDao {

	@Autowired
	DataSource dataSource;

	@Override
	public LoginBean isSigninAllowed(String username, String password) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);		
		String sql = "select username, handler_id, role_id from login where username = '"+username+"' and password = '"+password+"' and status = 'A'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<LoginBean>() {

			@Override
			public LoginBean extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					LoginBean loginData = new LoginBean();
					loginData.setUserName(rs.getString("username"));
					loginData.setHandler_id(rs.getString("handler_id"));
					loginData.setRole_id(rs.getString("role_id"));
					loginData.setUserExists(true);
					return loginData;
				}
				return null;
			}
		});
	}

	/*@Override
	public boolean signupUser(String username, String password, String phone) {
		
		if(isSigninAllowed(username, password)){
			return false;
		}else{
			String sql = "INSERT INTO login "
					+ "(username, password, phone) VALUES (?, ?, ?)";

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			jdbcTemplate.update(
					sql,
					new Object[] { username, password, phone});
			return true;
		}
	}*/
	
	@Override
	public boolean forgotPassword(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}
