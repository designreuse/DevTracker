package com.tmt.logistics.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.common.Utils;
import com.tmt.logistics.bean.AssignVehicle;

public class AssignVehicleDaoImpl implements AssignVehicleDao {

	@Autowired
	DataSource dataSource;

	
	@Override
	public AssignVehicle retrieveListData(AssignVehicle assgnVehicle) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println(assgnVehicle.getRole_id()+" ######################### "+assgnVehicle.getParent_id());
		String sql, sql1, sql2 = "";
		AssignVehicle assignVehicle = new AssignVehicle();
		
		if(assgnVehicle.getRole_id().equals("1") ){
			sql = "select username from login where status='A' and role_id = 2;";
			sql1 = "select vehicle_number from vehicle_connector where map_status='N'";	
			sql2 = "select imei from trackerdetails where status = 'Active' and imei not in (select imei from vehicle_connector) ;";
		}else if(assgnVehicle.getRole_id().equals("5") || assgnVehicle.getRole_id().equals("4")){    
			sql = "select username from login where status='A' and role_id = 2;";
			sql1 = "select vehicle_number from vehicle_connector where map_status='N' and parent_id='"+assgnVehicle.getParent_id()+"';";
			sql2 = "select imei from trackerdetails where status = 'Active' and imei not in (select imei from vehicle_connector) ;";
		}else{		
			sql = "select username from login where status='A' and role_id = 3;";
			sql1 = "select vehicle_number from vehicle_connector where broker_parent_id= '0' and imei != '0' and parent_id='"+assgnVehicle.getParent_id()+"';";
		}	
				
		List<String> userList = (List<String>)jdbcTemplate.queryForList(sql, String.class);			
		List<String> vehicleList = (List<String>)jdbcTemplate.queryForList(sql1, String.class);
		
		assignVehicle.setUserList(userList);
		assignVehicle.setVehicleNumber(vehicleList);
		
		if(assgnVehicle.getRole_id().equals("1") || assgnVehicle.getRole_id().equals("5") || assgnVehicle.getRole_id().equals("4")){
		List<String> imeiList = (List<String>)jdbcTemplate.queryForList(sql2, String.class);
		assignVehicle.setImeiList(imeiList);
		}
		return assignVehicle;
}
	
	public int updateCustVehicleMapping(AssignVehicle assgnVehicle){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String loginSql = "select handler_id, role_id from login where username='"+assgnVehicle.getUserName()+"'";
		List<AssignVehicle> loginData = jdbcTemplate.query(loginSql, new BeanPropertyRowMapper(AssignVehicle.class));
				
		System.out.println("-----> "+Utils.showBean(assgnVehicle, true));
		String sql ="";
		int result;
		if(assgnVehicle.getRole_id().equals("1")){
			sql = "UPDATE vehicle_connector set map_status = 'Y', role_id = ?, parent_id = ?, imei= ? where vehicle_number = ?";
			
			//System.out.println(((AssignVehicle)loginData.get(0)).getHandler_id()+"   ----------------------------> "+((AssignVehicle)loginData.get(0)).getRole_id());

			 result = jdbcTemplate.update(
					sql,
					new Object[] {((AssignVehicle)loginData.get(0)).getRole_id(), ((AssignVehicle)loginData.get(0)).getHandler_id(),  assgnVehicle.getInputImei(), assgnVehicle.getVehNumber()});
		}else{
			sql = "UPDATE vehicle_connector set map_status = 'Y', role_id = ?, broker_parent_id = ? where vehicle_number = ?";
			
			result = jdbcTemplate.update(
					sql,
					new Object[] {((AssignVehicle)loginData.get(0)).getRole_id(), ((AssignVehicle)loginData.get(0)).getHandler_id(), assgnVehicle.getVehNumber()});
		}
		return result;
	}
	
}
