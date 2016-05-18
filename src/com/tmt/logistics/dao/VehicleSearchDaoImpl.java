package com.tmt.logistics.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.common.Utils;
import com.tmt.logistics.bean.Coordinates;
import com.tmt.logistics.bean.VehicleDetails;
import com.tmt.tests.MockCoordinatesTestCase;

public class VehicleSearchDaoImpl implements VehicleSearchDao {

	@Autowired
	DataSource dataSource;

	@Override
	public JSONArray retrieveVehicleData(String imeiNumber, String vehicleNumber) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		String sqlCoordinates = "SELECT * FROM coordinates WHERE id = (SELECT MAX(id) FROM coordinates WHERE imei='"+imeiNumber+"');";
		List<Coordinates> coordinatesList  = jdbcTemplate.query(sqlCoordinates, new BeanPropertyRowMapper(Coordinates.class));		
		Coordinates coordinateData = coordinatesList.get(0);
		coordinateData.setVehicleNumber(vehicleNumber);
		JSONArray myArray = new JSONArray();
		myArray.addAll(coordinatesList);
		return myArray;
	}
	
	
	@Override
	public JSONObject retrievePast24hrData(String imeiNumber, String vehicleNumber, String currentDate, String yesterDate) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println("IMEI NUmber:  "+imeiNumber);
		System.out.println("vehicleNumber: "+vehicleNumber);
		System.out.println("CurrentDate:: "+currentDate);
		System.out.println("yesterDate:: "+yesterDate);
		String sqlCoordinates = "select c.imei, c.latitude, c.longitude, c.speed, c.address,c.datet, c.time, v.vehicle_number from coordinates c, vehicle_connector v where c.imei='"+imeiNumber+"' and v.imei = '"+imeiNumber+"' and CONCAT_WS(' ', c.dateTime) between '"+yesterDate+"' and '"+currentDate+"' order by c.dateTime asc;";
		System.out.println("---> "+sqlCoordinates);
		List<Coordinates> coordinatesList  = jdbcTemplate.query(sqlCoordinates, new BeanPropertyRowMapper(Coordinates.class));		
		//Coordinates coordinateData = new Coordinates();
		System.out.println("coordinatesList::::: "+coordinatesList.size());
		
		Set set = new TreeSet(new VehicleComparator());
		set.addAll(coordinatesList);

		//System.out.println("\n***** After removing duplicates *******\n");

	    //final ArrayList newList = new ArrayList(set);

	  /*  *//** Printing original list **//*
		System.out.println("original list: "+set.size());
		
		
		Coordinates lastRecordOfOriginalList = coordinatesList.get(coordinatesList.size()-1);
		
		System.out.println("Date******> "+lastRecordOfOriginalList.getDatet());
		System.out.println("Time******> "+lastRecordOfOriginalList.getTime());
		System.out.println("Address******> "+lastRecordOfOriginalList.getAddress());
		
		List<Coordinates> sortedSet = new ArrayList<Coordinates>(set);
		Coordinates lastRecordOfSortedSet = sortedSet.get(set.size()-1);
		
		System.out.println("Dat e@@@@@@@@@@@> "+lastRecordOfSortedSet.getDatet());
		System.out.println("Time @@@@@@@@@@@> "+lastRecordOfSortedSet.getTime());
		System.out.println("Address @@@@@@@@> "+lastRecordOfSortedSet.getAddress());*/
		/*
		Iterator iter = coordinatesList.iterator();
		while(iter.hasNext()){
			Coordinates coordinateData = (Coordinates) iter.next();
			//System.out.println(" -------------> "+Utils.showBean(coordinateData, false));
		}
		
		*/
		/*System.out.println("original list: "+set.size());
		Iterator iter1 = set.iterator();
		while(iter1.hasNext()){
			Coordinates coordinateData1 = (Coordinates) iter1.next();
			System.out.println(">>>>>>>>>>>>>>>>>> "+Utils.showBean(coordinateData1, false));
		}
		
		List<Coordinates> coordinteListbj = MockCoordinatesTestCase.createMockCoordinatesListObject();*/
		
		//coordinateData.setVehicleNumber(vehicleNumber);
		//coordinateData.setCoordinateList(coordinatesList);
		JSONObject obj = new JSONObject();
		
		
		JSONArray myArray = new JSONArray();
		myArray.addAll(coordinatesList);
		obj.put("arrayList", myArray);
		return obj;
	}
	
	
	class VehicleComparator implements Comparator<Coordinates> {
	    @Override
	    public int compare(Coordinates o1, Coordinates o2) {
			if(o1.getAddress().equalsIgnoreCase(o2.getAddress())){
		        return 0;
		     }
		    return 1;
		}
	}
	
	@Override
	public List<String> retrieveVehicleNumbersList(VehicleDetails vehicleDetail) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		String sqlVehicleList ="";
		if(vehicleDetail.getRole_id().equals("3")){
			sqlVehicleList = "SELECT vehicle_number FROM vehicle_connector WHERE broker_parent_id='"+vehicleDetail.getParent_id()+"';";
		}else if(vehicleDetail.getRole_id().equals("2")){
			sqlVehicleList = "SELECT vehicle_number FROM vehicle_connector WHERE parent_id='"+vehicleDetail.getParent_id()+"';";
		}else{
			sqlVehicleList = "SELECT vehicle_number FROM vehicle_connector;";
		}		
		List<String> vehicleNumList = (List<String>)jdbcTemplate.queryForList(sqlVehicleList, String.class);
		return vehicleNumList;
	}
	
	
	public int isIMEIExists(String imeiNumber){
        String sql = "select count(imei) from coordinates where imei='"+imeiNumber+"'";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int getCount = jdbcTemplate.queryForObject(sql, Integer.class);
		return getCount;
	}
}
