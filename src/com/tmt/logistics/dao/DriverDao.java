package com.tmt.logistics.dao;

import java.util.List;

import com.tmt.logistics.bean.DriverDetails;

public interface DriverDao {
	
	public int insertDriverData(DriverDetails driverDetails);

	public List<String> getVehicleList(DriverDetails driverDetails);

	public void deleteDriverData(String id);

	public DriverDetails getDriver(String id);
	
	public void updateDriverData(DriverDetails driverDetails);
	
	public String getVehicleHandlerId(String vehicleNumber);

}
