package com.tmt.logistics.dao;

import java.util.List;

import com.tmt.logistics.bean.VehicleDetails;


public interface VehicleDao {	
	
	public int insertVehicleData(VehicleDetails vehicleDetails);
	
	public List<String> fetchVehicleType();
	
	public int isVehicleNumberAlreadyExists(String vehicleNumber);
}
