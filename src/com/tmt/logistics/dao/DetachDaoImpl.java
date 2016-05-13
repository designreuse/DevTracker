package com.tmt.logistics.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tmt.logistics.bean.DetachTracker;
import com.tmt.logistics.bean.TrackerConfigure;

public class DetachDaoImpl implements DetachDao {

	@Autowired
	DataSource dataSource;

	/*public void insertDetachData(DetachTracker detachTracker) {

		String sql = "INSERT INTO user "
				+ "(imei, mobile, provider, sim, connectedWith) VALUES (?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { detachTracker.getImei(),
				detachTracker.getMobile(), detachTracker.getProvider(),
				detachTracker.getSim(), detachTracker.getConnectedWith() });

	}*/

	public List<String> getDetachUserList() {
		String sql = "select c.vehicle_number from vehicle_connector c, trackerdetails t  where c.imei = t.imei and t.status = 'Active' and c.map_status='Y';";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<String> trackerList = (List<String>)jdbcTemplate.queryForList(sql, String.class);
		return trackerList;
	}
	
	public List<TrackerConfigure> getTrackerData() {
		String sql = "select t.imei, v.vehicle_number, t.mobile, t.last_tested_location, t.status from trackerdetails t left outer join vehicle_connector v on t.imei = v.imei;";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TrackerConfigure> trackerConfigure  = jdbcTemplate.query(sql, new BeanPropertyRowMapper(TrackerConfigure.class));		
		return trackerConfigure;
	}

	@Override
	public DetachTracker getDetachedUser(String id) {
		String sql = "select imei, mobile, provider, sim from trackerdetails  where imei =  (select imei from vehicle_connector where vehicle_number='" + id+"')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<DetachTracker>() {

			@Override
			public DetachTracker extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					DetachTracker tstTracker = new DetachTracker();
					tstTracker.setImei(rs.getString("imei"));
					tstTracker.setMobile(rs.getString("mobile"));
					tstTracker.setProvider(rs.getString("provider"));
					tstTracker.setSim(rs.getString("sim"));
					return tstTracker;
				}
				return null;
			}
		});
	}
		

	@Override
	public void updateDetachedData(String imeiNumbr, String chngeStatus) {

		String sql = "UPDATE trackerdetails set status = ? where imei = ? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] {chngeStatus, imeiNumbr});
	}
	
	@Override
	public void updateDetachVehicleConnector(String vehicleNumber) {

		String sql = "UPDATE vehicle_connector set map_status = ?, imei= ? where vehicle_number = ? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] {"N", "", vehicleNumber});
	}
}
