package com.tmt.tests;

import java.util.ArrayList;
import java.util.List;

import com.tmt.logistics.bean.Coordinates;

public class MockCoordinatesTestCase {
	
	
	public static List<Coordinates> createMockCoordinatesListObject(){
		List<Coordinates> addList = new ArrayList<Coordinates>();
		Coordinates coordinate = new Coordinates();
		
		coordinate.setDatet("2016-4-15");
		coordinate.setTime("23:21:42");
		coordinate.setAddress("Unnamed Road, Tamil Nadu 602109, India");
		coordinate.setLatitude("12.982957777777777");
		coordinate.setLongitude("79.99587944444444");
		
		addList.add(coordinate);
		
		Coordinates coordinate1 = new Coordinates();
		coordinate1.setDatet("2016-4-16");
		coordinate1.setTime("05:14:29");
		coordinate1.setAddress("Perumalpattu-Kottamedu Rd, Thandal, Tamil Nadu 602105, India");
		coordinate1.setLatitude("12.989646666666667");
		coordinate1.setLongitude("79.98238944444445");
		
		addList.add(coordinate1);
		
		Coordinates coordinate2 = new Coordinates();
		coordinate2.setDatet("2016-4-16");
		coordinate2.setTime("05:19:29");
		coordinate2.setAddress("SIPCOT Project Office, 6th Cross St, Mevalurkuppam, Tamil Nadu 602117, India");
		coordinate2.setLatitude("13.007991111111112");
		coordinate2.setLongitude("79.99634444444445");
		
		addList.add(coordinate2);
		
		/*Coordinates coordinate3 = new Coordinates();
		coordinate3.setDatet("2016-4-16");
		coordinate3.setTime("05:25:19");
		coordinate3.setAddress("Perumalpattu-Kottamedu Rd, Kuthambakkam, Tamil Nadu 600124, India");
		coordinate3.setLatitude("13.033636666666666");
		coordinate3.setLongitude("80.03892777777777");
		
		addList.add(coordinate3);*/
		
		return addList;
	}

}
