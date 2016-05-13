package com.tmt.logistics.dao;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tmt.common.Geocoder;
import com.tmt.logistics.bean.Coordinates;
import com.tmt.logistics.bean.TrackCustomerDetails;

public class TrackerConfigureDaoImpl implements TrackerConfigureDao {

	@Autowired
	DataSource dataSource;

	@Override
	public void updateDetachTracker(String imeiNumber) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);		
		String vehNumberSql = "SELECT vehicle_number from vehicle_connector where imei = ? ";
		String vehicle_number = (String)jdbcTemplate.queryForObject(vehNumberSql, new Object[] { imeiNumber }, String.class);		
		String sql = "UPDATE vehicle_connector set map_status = ?, imei= ? where vehicle_number = ? ";
		jdbcTemplate.update(sql, new Object[] {"N", "", vehicle_number});
		//String trackerUpdatesql = "UPDATE trackerdetails set status = ? where imei= ? ";
		//jdbcTemplate.update(trackerUpdatesql, new Object[] {"Detach", imeiNumber});
		
	}

	@Override
	public void updateDisableTracker(String imeiNumber) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		String trackerUpdatesql = "UPDATE trackerdetails set status = ? where imei= ? ";
		jdbcTemplate.update(trackerUpdatesql, new Object[] {"Disable", imeiNumber});
	}

	@Override
	public void updateActivateTracker(String imeiNumber) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		String trackerUpdatesql = "UPDATE trackerdetails set status = ? where imei= ? ";
		jdbcTemplate.update(trackerUpdatesql, new Object[] {"Active", imeiNumber});
		
	}

	@Override
	public Coordinates getLastTrackedDate(String imeiNumber) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		String coordinateSql = "SELECT * from coordinates where imei='"+imeiNumber+"' order by id desc limit 1";
		return jdbcTemplate.query(coordinateSql, new ResultSetExtractor<Coordinates>() {

			@Override
			public Coordinates extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Coordinates coordinate = new Coordinates();
					coordinate.setImei(rs.getString("imei"));
					coordinate.setDatet(rs.getString("datet"));
					coordinate.setTime(rs.getString("time"));
					coordinate.setAddress(rs.getString("address"));										
					return coordinate;
				}
				return null;
			}
		});
	}

	

}
