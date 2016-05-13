package com.tmt.logistics.dao;

import java.util.List;

import com.tmt.logistics.bean.DetachTracker;
import com.tmt.logistics.bean.TrackerConfigure;

public interface DetachDao {
	
	//public void insertDetachData(DetachTracker detachTracker);

	public List<String> getDetachUserList();

	public DetachTracker getDetachedUser(String id);

	public void updateDetachedData(String imeiNumbr, String changeStatus);
	
	public void updateDetachVehicleConnector(String vehicleNumber);
	
	public List<TrackerConfigure> getTrackerData();

}
