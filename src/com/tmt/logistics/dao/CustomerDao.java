package com.tmt.logistics.dao;


import com.tmt.logistics.bean.CustomerDetails;
import com.tmt.logistics.bean.DriverDetails;

public interface CustomerDao {
	
	public void insertCustomerData(CustomerDetails customerDetails);
	
	public void insertDriverData(DriverDetails driverDetails);

	//public List<CustomerDetails> getVehicleList();

	public void deleteVehicleData(String id);

	//public CustomerDetails getVehicle(String id);

	//public void updateVehicleData(CustomerDetails customerDetails);

}
