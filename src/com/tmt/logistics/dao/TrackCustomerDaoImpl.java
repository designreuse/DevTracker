package com.tmt.logistics.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.common.Utils;
import com.tmt.logistics.bean.TrackCustomerDetails;

public class TrackCustomerDaoImpl implements TrackCustomerDao {

	@Autowired
	DataSource dataSource;
		
	@Override
	public void saveDeliveryAddress(TrackCustomerDetails trackCustomerDetails) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "INSERT INTO delivery_address "
				+ "(godown_name, latitude, longitude, address, handler_id, parent_id) VALUES (?, ?, ?, ?, ?, ?)";
		
			jdbcTemplate.update(
					sql,
					new Object[] {trackCustomerDetails.getGodown_name(), trackCustomerDetails.getLatitude(), trackCustomerDetails.getLongitude(), trackCustomerDetails.getAddress(), Utils.generate5DigtRandomNum(), trackCustomerDetails.getParent_id()});
						
	}

	@Override
	public List<TrackCustomerDetails> retrieveAddressDetails(String id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);		
		String addressSql = "SELECT godown_name, address, status from delivery_address where parent_id = "+id;
		List<TrackCustomerDetails> addressList  = jdbcTemplate.query(addressSql, new BeanPropertyRowMapper(TrackCustomerDetails.class));		
		return addressList;
	}
	
	@Override
	public List<TrackCustomerDetails> retrieveInvoiceDetails(String id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);		
		String addressSql = "SELECT * from invoice where parent_id = "+id;
		List<TrackCustomerDetails> invoiceList  = jdbcTemplate.query(addressSql, new BeanPropertyRowMapper(TrackCustomerDetails.class));		
		
		return invoiceList;
	}
	
	public int isGodownNameExists(String godownName){
        String sql = "select count(godown_name) from delivery_address where godown_name='"+godownName+"'";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int getCount = jdbcTemplate.queryForObject(sql, Integer.class);
		return getCount;
	}
}
