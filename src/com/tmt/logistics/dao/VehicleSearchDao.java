package com.tmt.logistics.dao;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tmt.logistics.bean.VehicleDetails;

public interface VehicleSearchDao {
	
	public JSONArray retrieveVehicleData(String imei, String vehicleNumber);
	public JSONObject retrievePast24hrData(String imei, String vehicleNumber, String currentDate, String yesterDate);
	public List<String> retrieveVehicleNumbersList(VehicleDetails vehicleDetail);
	public int isIMEIExists(String imeiNumber);
	
	
}
