package com.tmt.logistics.dao;

import java.util.List;

import javax.sql.DataSource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.logistics.bean.Coordinates;
import com.tmt.logistics.bean.VehicleDetails;

public class VehicleSearchDaoImpl implements VehicleSearchDao {

	@Autowired
	DataSource dataSource;

	@Override
	public JSONArray retrieveVehicleData(String imeiNumber, String vehicleNumber) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		String sqlCoordinates = "SELECT * FROM coordinates WHERE id = (SELECT MAX(id) FROM coordinates WHERE imei='"+imeiNumber+"');";
		List<Coordinates> coordinatesList  = jdbcTemplate.query(sqlCoordinates, new BeanPropertyRowMapper(Coordinates.class));		
		Coordinates coordinateData = coordinatesList.get(0);
		coordinateData.setVehicleNumber(vehicleNumber);
		JSONArray myArray = new JSONArray();
		myArray.addAll(coordinatesList);
		return myArray;
	}	
	
	@Override
	public List<String> retrieveVehicleNumbersList(VehicleDetails vehicleDetail) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		String sqlVehicleList ="";
		if(vehicleDetail.getRole_id().equals("3")){
			sqlVehicleList = "SELECT vehicle_number FROM vehicle_connector WHERE broker_parent_id='"+vehicleDetail.getParent_id()+"';";
		}else if(vehicleDetail.getRole_id().equals("2")){
			sqlVehicleList = "SELECT vehicle_number FROM vehicle_connector WHERE parent_id='"+vehicleDetail.getParent_id()+"';";
		}else{
			sqlVehicleList = "SELECT vehicle_number FROM vehicle_connector;";
		}		
		List<String> vehicleNumList = (List<String>)jdbcTemplate.queryForList(sqlVehicleList, String.class);
		return vehicleNumList;
	}
	
	
	public int isIMEIExists(String imeiNumber){
        String sql = "select count(imei) from coordinates where imei='"+imeiNumber+"'";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int getCount = jdbcTemplate.queryForObject(sql, Integer.class);
		return getCount;
	}
}
