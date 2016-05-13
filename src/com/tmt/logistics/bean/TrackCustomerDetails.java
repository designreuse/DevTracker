package com.tmt.logistics.bean;

import java.util.List;

public class TrackCustomerDetails {

	private String agentName;
	private String role_id;
	private String parent_id;
	private String godown_name;
	private double latitude;
	private double longitude;
	private String address;
	private String status;
	
	private String invoice_number;
	private String from_address;
	private String start_time;
	private String to_address;
	
	private List<String> agentList;

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public List<String> getAgentList() {
		return agentList;
	}

	public void setAgentList(List<String> agentList) {
		this.agentList = agentList;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGodown_name() {
		return godown_name;
	}

	public void setGodown_name(String godown_name) {
		this.godown_name = godown_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	public String getFrom_address() {
		return from_address;
	}

	public void setFrom_address(String from_address) {
		this.from_address = from_address;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getTo_address() {
		return to_address;
	}

	public void setTo_address(String to_address) {
		this.to_address = to_address;
	}

}
