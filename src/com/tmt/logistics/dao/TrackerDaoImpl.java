package com.tmt.logistics.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tmt.logistics.bean.TrackerDetail;

public class TrackerDaoImpl implements TrackerDao {

	@Autowired
	DataSource dataSource;

	public int insertTrackerData(TrackerDetail trackerDetail) {

		String sql = "INSERT INTO trackerdetails "
				+ "(imei, type, mobile, provider, sim) VALUES (?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		int result = jdbcTemplate.update(sql,new Object[] { trackerDetail.getImei(), trackerDetail.getType(), trackerDetail.getMobile(),
						trackerDetail.getProvider(), trackerDetail.getSim()});
		
		System.out.println("RESULT :::::: "+result);
		return result;
	}
	
	public int isTrackerExists(TrackerDetail trackerDetail) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "select count(imei) from trackerdetails where imei='"+trackerDetail.getImei()+"'";
		int result = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println("RESULT_IMEI_COUNT :::::: "+result);
		return result;
	}

	public List<String> getTrackerList() {
		String sql = "select c.vehicle_number from vehicle_connector c, trackerdetails t  where c.imei = t.imei and t.status = 'Active';";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<String> trackerList = (List<String>)jdbcTemplate.queryForList(sql, String.class);
		System.out.println("trackerList:::: "+trackerList);
		return trackerList;
	}

	@Override
	public void deleteTrackerData(String id) {
		String sql = "delete from user where user_id=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);

	}

	@Override
	public void updateTrackerData(TrackerDetail trackerDetail) {

		String sql = "UPDATE user set first_name = ?,last_name = ?, gender = ?, city = ? where user_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { trackerDetail.getImei(), trackerDetail.getMobile(),
						trackerDetail.getProvider(), trackerDetail.getSim(), trackerDetail.getType(), trackerDetail.getConnectedWith() });
	}

	@Override
	public TrackerDetail getTracker(String id) {
		String sql = "select * from user where user_id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(sql, new ResultSetExtractor<TrackerDetail>() {

			@Override
			public TrackerDetail extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					TrackerDetail trackerDetail = new TrackerDetail();
					trackerDetail.setImei(rs.getString("contact_id"));
					trackerDetail.setMobile(rs.getString("name"));
					trackerDetail.setProvider(rs.getString("email"));
					trackerDetail.setSim(rs.getString("address"));
					trackerDetail.setType(rs.getString("address"));
					trackerDetail.setConnectedWith(rs.getString("address"));
					return trackerDetail;
				}
				return null;
			}
		});
	}

}
