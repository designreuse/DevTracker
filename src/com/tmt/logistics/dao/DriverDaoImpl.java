package com.tmt.logistics.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tmt.logistics.bean.DriverDetails;

public class DriverDaoImpl implements DriverDao {

	@Autowired
	DataSource dataSource;

	public int insertDriverData(DriverDetails driverDetails) {
		String sql = "INSERT INTO driverdetails "
				+ "(vehicle_number, driverName, driverMobile, driverLicense, driverAddress, handler_id) VALUES (?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		int result = jdbcTemplate.update(
				sql,
				new Object[] {driverDetails.getVehicleNumber(), driverDetails.getDriverName(), driverDetails.getDriverMobile(),
						driverDetails.getDriverLicense(), driverDetails.getDriverAddress(), driverDetails.getHandlerId()});
		return result;

	}

	public List<String> getVehicleList(DriverDetails driverDetails) {
		String sql = "";
		if(driverDetails.getRole_id().equals("1") || driverDetails.getRole_id().equals("7")){
			sql = "select vehicle_number from vehicle_connector";
		}else{
			sql = "select vehicle_number from vehicle_connector where parent_id='"+driverDetails.getParent_id()+"'";
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<String> vehicleList = (List<String>)jdbcTemplate.queryForList(sql, String.class);
		return vehicleList;
		
	}
	
	
	public String getVehicleHandlerId(String vehicleNumber) {
		String sql = "select handler_id from vehicle_connector where vehicle_number='"+vehicleNumber+"'";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String handlerId = jdbcTemplate.queryForObject(sql, String.class);
		return handlerId;
		
	}

	@Override
	public void deleteDriverData(String id) {
		String sql = "delete from user where user_id=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);

	}

	@Override
	public void updateDriverData(DriverDetails driverDetails) {
		String sql = "UPDATE user set first_name = ?,last_name = ?, gender = ?, city = ? where user_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { driverDetails.getDriverName(), driverDetails.getDriverMobile(),
						driverDetails.getDriverLicense(), driverDetails.getDriverAddress() });

	}

	@Override
	public DriverDetails getDriver(String id) {		
		String sql = "select * from user where user_id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		return jdbcTemplate.query(sql, new ResultSetExtractor<DriverDetails>() {

			@Override
			public DriverDetails extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					DriverDetails driverDetails = new DriverDetails();
					driverDetails.setDriverName(rs.getString("contact_id"));
					driverDetails.setDriverMobile(rs.getString("name"));
					driverDetails.setDriverLicense(rs.getString("email"));
					driverDetails.setDriverAddress(rs.getString("address"));
					return driverDetails;
				}
				return null;
			}
		});
	}

}
