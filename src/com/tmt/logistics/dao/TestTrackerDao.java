package com.tmt.logistics.dao;

/**
 *
 * @author Nagesh Chauhan
 */

import java.util.List;

import com.tmt.logistics.bean.TestTracker;

public interface TestTrackerDao {
	
	public void insertTestTrackerData(TestTracker testTracker);

	public List<String> getTestTrackerUserList();

	public void deleteTestTrackerData(String id);

	public TestTracker getTestTracker(String id);

	public void updateTestTrackerData(TestTracker testTracker);

}
