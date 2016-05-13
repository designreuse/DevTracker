package com.tmt.logistics.bean;

import java.util.List;

public class AssignVehicle {
	
	private List<String> userList;	
	private List<String> VehicleNumber;
	private List<String> imeiList;
	private String parent_id;
	private String role_id;
	private String handler_id;
	private String userName;
	private String vehNumber;
	private String inputImei;
		
	
	public List<String> getVehicleNumber() {
		return VehicleNumber;
	}
	public void setVehicleNumber(List<String> vehicleNumber) {
		VehicleNumber = vehicleNumber;
	}	
	public String getVehNumber() {
		return vehNumber;
	}
	public void setVehNumber(String vehNumber) {
		this.vehNumber = vehNumber;
	}
	public List<String> getImeiList() {
		return imeiList;
	}
	public void setImeiList(List<String> imeiList) {
		this.imeiList = imeiList;
	}
	public String getInputImei() {
		return inputImei;
	}
	public void setInputImei(String inputImei) {
		this.inputImei = inputImei;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public List<String> getUserList() {
		return userList;
	}
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHandler_id() {
		return handler_id;
	}
	public void setHandler_id(String handler_id) {
		this.handler_id = handler_id;
	}
		
}
