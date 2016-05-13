package com.tmt.logistics.bean;

public class LoginBean {
	
	private boolean isUserExists = false;
	private String userName;
	private String handler_id;
	private String role_id;
	private String parent_id;
	
	public boolean isUserExists() {
		return isUserExists;
	}
	public void setUserExists(boolean isUserExists) {
		this.isUserExists = isUserExists;
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

		
}
