package com.tmt.logistics.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.logistics.bean.TrackerReportDetail;

public class TrackerReportDaoImpl implements TrackerReportDao {

	@Autowired
	DataSource dataSource;

	public List<TrackerReportDetail> retrieveTrackerData() {
		String sql = "select t.imei, v.vehicle_number, t.mobile, t.provider, t.expiry, t.status  from trackerdetails t LEFT OUTER JOIN vehicle_connector v on t.imei = v.imei; ";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TrackerReportDetail> trackerReportList  = jdbcTemplate.query(sql, new BeanPropertyRowMapper(TrackerReportDetail.class));	
		return trackerReportList;
	}

}
