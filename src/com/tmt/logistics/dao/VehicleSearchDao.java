package com.tmt.logistics.dao;

import java.util.List;

import com.tmt.logistics.bean.Coordinates;
import com.tmt.logistics.bean.VehicleDetails;

import net.sf.json.JSONArray;

public interface VehicleSearchDao {
	
	public JSONArray retrieveVehicleData(String imei, String vehicleNumber);
	public List<String> retrieveVehicleNumbersList(VehicleDetails vehicleDetail);
	public int isIMEIExists(String imeiNumber);
	
	
}
