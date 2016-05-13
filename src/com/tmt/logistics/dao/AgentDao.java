package com.tmt.logistics.dao;

import java.util.List;

import com.tmt.logistics.bean.CFAgent;

public interface AgentDao {	
	
	public List<String> retrieveDeliveryAddress(String id);
	
	public int insertAgentDetails(CFAgent agent);
	
	public String retrieveIMEINumber(String vehicleNumber);
	
	public String getToAddress(String aliasToAddress);
	
	public int isInvoiceAlreadyExists(String invoiceNumber);
	
	public String isIMEINumberActive(String imeiNumber);
}
