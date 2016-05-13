package com.tmt.logistics.bean;

public class CFAgent {

	private String vehicleNumber;
	private String invoiceNumber;
	private String fromAddress;
	private String toAddress;
	private double fromAddressLatitude;
	private double fromAddressLongitude;
	private double toAddressLatitude;
	private double toAddressLongitude;
	private String distance;
	private String parent_id;
	private String role_id;
	private String imei;
	
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
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

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public double getFromAddressLatitude() {
		return fromAddressLatitude;
	}

	public void setFromAddressLatitude(double fromAddressLatitude) {
		this.fromAddressLatitude = fromAddressLatitude;
	}

	public double getFromAddressLongitude() {
		return fromAddressLongitude;
	}

	public void setFromAddressLongitude(double fromAddressLongitude) {
		this.fromAddressLongitude = fromAddressLongitude;
	}

	public double getToAddressLatitude() {
		return toAddressLatitude;
	}

	public void setToAddressLatitude(double toAddressLatitude) {
		this.toAddressLatitude = toAddressLatitude;
	}

	public double getToAddressLongitude() {
		return toAddressLongitude;
	}

	public void setToAddressLongitude(double toAddressLongitude) {
		this.toAddressLongitude = toAddressLongitude;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	

}
