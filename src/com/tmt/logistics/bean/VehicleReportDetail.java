package com.tmt.logistics.bean;

public class VehicleReportDetail {

	private String vehicle_number;
	private String username;
	private String name;
	private String phone;
	private String role_id;
	private String status;
	
	public String getVehicle_number() {
		return vehicle_number;
	}
	public void setVehicle_number(String vehicle_number) {
		this.vehicle_number = vehicle_number.toUpperCase();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
}
