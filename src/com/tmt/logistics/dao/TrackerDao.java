package com.tmt.logistics.dao;



import java.util.List;

import com.tmt.logistics.bean.TrackerDetail;

public interface TrackerDao {
	
	public int insertTrackerData(TrackerDetail trackerDetail);

	public List<String> getTrackerList();

	public void deleteTrackerData(String id);

	public TrackerDetail getTracker(String id);

	public void updateTrackerData(TrackerDetail trackerDetail);
	
	public int isTrackerExists(TrackerDetail trackerDetail);

}
