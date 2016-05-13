package com.tmt.logistics.bean;

public class TrackerReportDetail {

	private String imei;
	private String vehicle_number;
	private String mobile;
	private String provider;
	private String expiry;
	private String status;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}
