package com.tmt.logistics.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tmt.common.Geocoder;
import com.tmt.logistics.bean.Coordinates;

public class CoordinatesDaoImpl implements CoordinatesDao {

	@Autowired
	DataSource dataSource;
	
	@Override
	public Coordinates getCoordinates(String vehicleNumber) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String getImei = "select imei from vehicle_connector where vehicle_number = ?;";
		String imeiNumber = (String)jdbcTemplate.queryForObject(getImei, new Object[] { vehicleNumber }, String.class);
		System.out.println("ImeiNumber >>>>> "+imeiNumber);
		String sql = "SELECT * FROM coordinates where imei='"+imeiNumber+"' order by id desc limit 1;";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Coordinates>() {

			@Override
			public Coordinates extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Coordinates coordinate = new Coordinates();
					coordinate.setImei(rs.getString("imei"));
					coordinate.setLatitude(rs.getString("latitude"));
					coordinate.setLongitude(rs.getString("longitude"));
					try {
						coordinate.setAddress(Geocoder.getAddress(Double.parseDouble(coordinate.getLatitude()), Double.parseDouble(coordinate.getLongitude())));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					coordinate.setSpeed(rs.getString("speed"));
					coordinate.setDatet(rs.getString("date"));
					return coordinate;
				}
				return null;
			}
		});
	}
		
	public int isIMEIAlreadyExists(String invoiceNumber){
        String sql = "select count(invoice_number) from invoice where invoice_number='"+invoiceNumber+"'";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int getCount = jdbcTemplate.queryForObject(sql, Integer.class);
		return getCount;
	}
	
	@Override
	public List<String> retrieveVehicleNumbersList() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		String sqlVehicleList = "SELECT vehicle_number FROM vehicle_connector;";
			
		List<String> vehicleNumList = (List<String>)jdbcTemplate.queryForList(sqlVehicleList, String.class);
		return vehicleNumList;
	}
	
}
