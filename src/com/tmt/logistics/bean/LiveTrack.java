package com.tmt.logistics.bean;

public class LiveTrack {

	private String datefilter;
	
	private String vehicleNumber;

	private String parent_id;
	
	private String role_id;
	
	private String frequency;
	
	private String fromDate;
	
	private String toDate;
	
	public String getDatefilter() {
		return datefilter;
	}

	public void setDatefilter(String datefilter) {
		this.datefilter = datefilter;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
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

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
