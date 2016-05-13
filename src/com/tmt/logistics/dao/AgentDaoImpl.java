package com.tmt.logistics.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.common.Utils;
import com.tmt.logistics.bean.CFAgent;

public class AgentDaoImpl implements AgentDao {

	@Autowired
	DataSource dataSource;
		
	public int insertAgentDetails(CFAgent agent) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "INSERT INTO invoice (vehicle_number, invoice_number, from_address, start_time, to_address, handler_id, parent_id, imei, from_coordinates, to_coordinates, distance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		int result = jdbcTemplate.update(
					sql,
					new Object[] {agent.getVehicleNumber(), agent.getInvoiceNumber(), agent.getFromAddress(), Utils.getDateTime(), agent.getToAddress(), Utils.generate5DigtRandomNum(), agent.getParent_id(), agent.getImei(), agent.getFromAddressLatitude()+","+agent.getFromAddressLongitude(), agent.getToAddressLatitude()+","+agent.getToAddressLongitude(), agent.getDistance()});
		return result;				
	}

	@Override
	public List<String> retrieveDeliveryAddress(String id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);		
		String addressSql = "SELECT godown_name from delivery_address where parent_id = "+id;
		List<String> addressList = (List<String>)jdbcTemplate.queryForList(addressSql, String.class);		
		return addressList;
	}

	@Override
	public String retrieveIMEINumber(String vehicleNumber) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);		
		String imeiSql = "SELECT imei from vehicle_connector where vehicle_number = '"+vehicleNumber+"'";
		String imeiNumber =null;
		try {
			imeiNumber = (String)jdbcTemplate.queryForObject(imeiSql, String.class);
			System.out.println("=====> "+imeiNumber);
		} catch (EmptyResultDataAccessException  e) {
			return null;
		}		
		return imeiNumber;
	}
	
	public String isIMEINumberActive(String imeiNumber) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);		
		String imeiSql = "SELECT status from trackerdetails where imei = '"+imeiNumber+"'";
		String imeiStatus =null;
		try {
			imeiStatus = (String)jdbcTemplate.queryForObject(imeiSql, String.class);
			//System.out.println("=====> "+imeiNumber);
		} catch (EmptyResultDataAccessException  e) {
			return null;
		}		
		return imeiStatus;
	}
	
	
	@Override
	public String getToAddress(String aliasToAddress) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);		
		String toAddressSql = "select address from delivery_address where godown_name = '"+aliasToAddress+"'";
		String toAddress = (String)jdbcTemplate.queryForObject(toAddressSql, String.class);				
		return toAddress;
	}
	
	public int isInvoiceAlreadyExists(String invoiceNumber){
        String sql = "select count(invoice_number) from invoice where invoice_number='"+invoiceNumber+"'";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int getCount = jdbcTemplate.queryForObject(sql, Integer.class);
		return getCount;
	}
}
