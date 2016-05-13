package com.tmt.logistics.dao;

import com.tmt.logistics.bean.AssignVehicle;

public interface AssignVehicleDao {
	
	public AssignVehicle retrieveListData(AssignVehicle assgnVehicle);

	public int updateCustVehicleMapping(AssignVehicle assgnVehicle);
}
