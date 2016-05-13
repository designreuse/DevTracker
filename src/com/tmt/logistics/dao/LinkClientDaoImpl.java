package com.tmt.logistics.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.logistics.bean.LinkClient;

public class LinkClientDaoImpl implements LinkClientDao {

	@Autowired
	DataSource dataSource;
		
	@Override
	public LinkClient retrieveLinkClientData(LinkClient linkClient) {
		String sql = "select username from login where status='A' and role_id != 1;";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<String> userList = (List<String>)jdbcTemplate.queryForList(sql, String.class);
		String sql1 = "SELECT customerName FROM customerdetails;";		
		List<String> clientList = (List<String>)jdbcTemplate.queryForList(sql1, String.class);
		
		LinkClient linkClientData = new LinkClient();
		linkClientData.setUserList(userList);
		linkClientData.setClientList(clientList);
		return linkClientData;
}

	@Override
	public void updateClientLink(LinkClient linkClient) {		
        String sql = "UPDATE vehicle_connector set map_status = 'Y', parent_id = '"+linkClient.getParent_id()+"', imei= ? where vehicle_number = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] {linkClient.getClientName(), linkClient.getParent_id()});
	}
	
	
	

}
