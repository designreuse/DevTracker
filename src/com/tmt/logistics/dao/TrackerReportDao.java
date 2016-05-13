package com.tmt.logistics.dao;

/**
 *
 * @author Nagesh Chauhan
 */

import java.util.List;

import com.tmt.logistics.bean.TrackerReportDetail;

public interface TrackerReportDao {
	
	public List<TrackerReportDetail> retrieveTrackerData();

	
}
