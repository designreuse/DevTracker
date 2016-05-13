package com.tmt.logistics.dao;

import java.util.List;

import com.tmt.logistics.bean.TrackCustomerDetails;



public interface TrackCustomerDao {	
	
	public void saveDeliveryAddress(TrackCustomerDetails trackCustomerDetails);
	
	public List retrieveAddressDetails(String id);
	
	public List retrieveInvoiceDetails(String id);
	
	public int isGodownNameExists(String godownName);
}
