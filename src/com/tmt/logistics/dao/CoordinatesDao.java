package com.tmt.logistics.dao;


import java.util.List;

import com.tmt.logistics.bean.Coordinates;

public interface CoordinatesDao {
	
	public Coordinates  getCoordinates(String vehicleNumber);
	
	public List<String> retrieveVehicleNumbersList();

}
