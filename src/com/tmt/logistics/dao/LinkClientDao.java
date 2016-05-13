package com.tmt.logistics.dao;

import com.tmt.logistics.bean.LinkClient;


public interface LinkClientDao {	
	
	public LinkClient retrieveLinkClientData(LinkClient linkClient);
	
	public void updateClientLink(LinkClient linkClient);
}
