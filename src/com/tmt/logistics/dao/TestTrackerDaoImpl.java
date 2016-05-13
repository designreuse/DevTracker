package com.tmt.logistics.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tmt.logistics.bean.TestTracker;

public class TestTrackerDaoImpl implements TestTrackerDao {

	@Autowired
	DataSource dataSource;

	public void insertTestTrackerData(TestTracker testTracker) {

		String sql = "INSERT INTO user "
				+ "(first_name,last_name, gender, city) VALUES (?, ?, ?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { testTracker.getImei(), testTracker.getMobile(),
						testTracker.getProvider(), testTracker.getSim(), testTracker.getConnectedWith() });

	}

	public List<String> getTestTrackerUserList() {
		String sql = "select imei from trackerdetails where status = 'Active'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);		
		List<String>	trackerList = (List<String>)jdbcTemplate.queryForList(sql, String.class);
		return trackerList;
	}

	@Override
	public void deleteTestTrackerData(String id) {
		String sql = "delete from user where user_id=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);

	}

	@Override
	public void updateTestTrackerData(TestTracker testTracker) {

		String sql = "UPDATE user set first_name = ?,last_name = ?, gender = ?, city = ? where user_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { testTracker.getImei(), testTracker.getMobile(),
						testTracker.getProvider(), testTracker.getSim(), testTracker.getConnectedWith() });

	}

	@Override
	public TestTracker getTestTracker(String imeiNumber) {
		System.out.println("==> "+imeiNumber);
		String sql = "select * from trackerdetails where imei= '" + imeiNumber+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<TestTracker>() {

			@Override
			public TestTracker extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					TestTracker tstTracker = new TestTracker();
					tstTracker.setMobile(rs.getString("mobile"));
					tstTracker.setProvider(rs.getString("provider"));
					tstTracker.setSim(rs.getString("sim"));
					return tstTracker;
				}
				return null;
			}
		});
	}

}
