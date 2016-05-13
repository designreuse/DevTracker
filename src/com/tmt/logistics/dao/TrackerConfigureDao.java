package com.tmt.logistics.dao;

import com.tmt.logistics.bean.Coordinates;


public interface TrackerConfigureDao {
	
	public void updateDetachTracker(String imeiNumber);
	
	public void updateDisableTracker(String imeiNumber);
	
	public void updateActivateTracker(String imeiNumber);
	
	public Coordinates getLastTrackedDate(String imeiNumber);
}
