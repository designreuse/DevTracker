package com.tmt.logistics.bean;

public class TrackerConfigure {
	
	private String imei;
	private String vehicle_number;
	private String mobile;
	private String last_tested_location;
	private String status;
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getVehicle_number() {
		return vehicle_number;
	}
	public void setVehicle_number(String vehicle_number) {
		this.vehicle_number = vehicle_number;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLast_tested_location() {
		return last_tested_location;
	}
	public void setLast_tested_location(String last_tested_location) {
		this.last_tested_location = last_tested_location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
