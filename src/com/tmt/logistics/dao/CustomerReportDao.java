package com.tmt.logistics.dao;

import java.util.List;

import com.tmt.logistics.bean.CustomerReportDetail;

public interface CustomerReportDao {
	
	public List<CustomerReportDetail> retrieveCustomerData();
	public List<CustomerReportDetail> retrieveVehicleData();	
	public List<CustomerReportDetail> retrieveLoginData();
	public List<CustomerReportDetail> retrieveDriverData();
	public void updateVehicleDisassociation(CustomerReportDetail custReportVehicle);
}
