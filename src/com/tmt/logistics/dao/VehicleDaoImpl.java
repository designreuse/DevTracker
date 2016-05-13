package com.tmt.logistics.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.common.Utils;
import com.tmt.logistics.bean.VehicleDetails;

public class VehicleDaoImpl implements VehicleDao {

	@Autowired
	DataSource dataSource;
		
	@Override
	public int insertVehicleData(VehicleDetails vehicleDetails) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "INSERT INTO vehicle_connector "
				+ "(vehicle_number, vehicle_type, role_id, handler_id, parent_id) VALUES (?, ?, ?, ?, ?)";
		int result = jdbcTemplate.update(
				sql,
				new Object[] {vehicleDetails.getVehicleNumber(), vehicleDetails.getVehType(), vehicleDetails.getRole_id(), Utils.generate5DigtRandomNum(), vehicleDetails.getParent_id()});
		return result; 		
	}

	@Override
	public List<String> fetchVehicleType() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select vehicleType from vehicle_type;";
		List<String> vehicleTypeList = (List<String>)jdbcTemplate.queryForList(sql, String.class);
		return vehicleTypeList;
	}
	
	public int isVehicleNumberAlreadyExists(String vehicleNumber){
        String sql = "select count(vehicle_number) from vehicle_connector where vehicle_number='"+vehicleNumber+"'";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int getCount = jdbcTemplate.queryForObject(sql, Integer.class);
		return getCount;
	}
}
