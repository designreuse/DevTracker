package com.tmt.logistics.dao;

import net.sf.json.JSONArray;

import com.tmt.logistics.bean.Invoice;
import com.tmt.logistics.bean.PlayForPay;

public interface HomePageDao {
	
	public Invoice retrieveInvoiceDetails(String invoiceNumber);
	
	public JSONArray retrieveCoordinates(Invoice invoiceData);
	
	public void insertPlayForPayData (PlayForPay playForPay);

}
