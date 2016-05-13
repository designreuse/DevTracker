package com.tmt.logistics.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.logistics.bean.VehicleReportDetail;

public class VehicleReportDaoImpl implements VehicleReportDao {

	@Autowired
	DataSource dataSource;

	public List<VehicleReportDetail> retrieveVehicleData() {
		String sql = "select v.vehicle_number, l.username, l.name, l.phone, l.role_id, l.status  from vehicle_connector v  LEFT OUTER JOIN login l on  v.parent_id = l.handler_id;";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<VehicleReportDetail> vehicleReportList  = jdbcTemplate.query(sql, new BeanPropertyRowMapper(VehicleReportDetail.class));		
		return vehicleReportList;
	}

	@Override
	public void detachVehicleUpdation(String vehicleNumber) {
		String sql = "delete from vehicle_connector where vehicle_number= ? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, new Object[] {vehicleNumber});		
	}

}
