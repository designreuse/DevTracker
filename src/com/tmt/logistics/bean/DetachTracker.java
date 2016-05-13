package com.tmt.logistics.bean;

public class DetachTracker {

	private String imei;
	private String sim;
	private String provider;
	private String mobile;
	private String connectedWith;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getConnectedWith() {
		return connectedWith;
	}

	public void setConnectedWith(String connectedWith) {
		this.connectedWith = connectedWith;
	}

}
