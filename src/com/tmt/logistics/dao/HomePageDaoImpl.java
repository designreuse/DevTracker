package com.tmt.logistics.dao;

import java.util.List;

import javax.sql.DataSource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.common.Utils;
import com.tmt.logistics.bean.Coordinates;
import com.tmt.logistics.bean.Invoice;
import com.tmt.logistics.bean.PlayForPay;

public class HomePageDaoImpl implements HomePageDao {

	@Autowired
	DataSource dataSource;

	public Invoice retrieveInvoiceDetails(String invoiceNumber) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);		
		String invoiceSql = "SELECT vehicle_number, invoice_number, from_address, start_time, to_address, parent_id, imei from invoice where invoice_number = "+invoiceNumber;
		Invoice invoiceData;
		try {
			invoiceData = (Invoice)jdbcTemplate.queryForObject(invoiceSql, new BeanPropertyRowMapper(Invoice.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return invoiceData;
	}

	@Override
	public JSONArray retrieveCoordinates(Invoice invoiceData) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		System.out.println("IMEI::::: "+invoiceData.getImei());
		String sqlCoordinates = "select distinct latitude, longitude, address from coordinates where imei='"+invoiceData.getImei()+"' and STR_TO_DATE(`datet`, '%d-%m-%Y') BETWEEN STR_TO_DATE('"+invoiceData.getStart_time()+"', '%d-%m-%Y') AND STR_TO_DATE('"+Utils.getDateTime()+"', '%d-%m-%Y') group by address order by id;";
		
		System.out.println("--------> "+sqlCoordinates);
		List<Coordinates> coordinatesList  = jdbcTemplate.query(sqlCoordinates, new BeanPropertyRowMapper(Coordinates.class));	
		
		System.out.println("--------> "+coordinatesList.size());
							
		JSONArray myArray = new JSONArray();
		
		myArray.addAll(coordinatesList);
		System.out.println(myArray.toString());
		
		return myArray;
	}

	
	public void insertPlayForPayData(PlayForPay playForPay) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);		
		String sql = "INSERT INTO invoice (vehicle_number, invoice_number, from_address, start_time, to_address, imei, from_coordinates, to_coordinates, distance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(
					sql,
					new Object[] {playForPay.getVehicleNumber(), playForPay.getInvoiceNumber(), playForPay.getFromAddress(), Utils.getDateTime(), playForPay.getToAddress(), playForPay.getImeiNumber(), playForPay.getFromAddressLatitude()+","+playForPay.getFromAddressLongitude(), playForPay.getToAddressLatitude()+","+playForPay.getToAddressLongitude(),playForPay.getDistance()});
		
	}
	
}
