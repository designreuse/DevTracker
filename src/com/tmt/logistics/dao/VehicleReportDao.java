package com.tmt.logistics.dao;

import java.util.List;

import com.tmt.logistics.bean.VehicleReportDetail;

public interface VehicleReportDao {
	
	public List<VehicleReportDetail> retrieveVehicleData();
	
	public void detachVehicleUpdation(String vehicleNumber);

	
}
