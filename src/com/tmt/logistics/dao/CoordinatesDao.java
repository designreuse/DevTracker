package com.tmt.logistics.dao;

/**
 *
 * @author Nagesh Chauhan
 */

import com.tmt.logistics.bean.Coordinates;

public interface CoordinatesDao {
	
	public Coordinates  getCoordinates(String vehicleNumber);

}
