package com.tmt.logistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmt.logistics.bean.TrackerReportDetail;
import com.tmt.logistics.dao.TrackerReportDao;

@Controller
public class ReportTrackerDetailsController{
	
	@Autowired
	TrackerReportDao trackerReportDao;
	
	
	@RequestMapping("/trackerReport")
	public ModelAndView getTrackerReportScreen() {		
		List<TrackerReportDetail> trackerReportDetail = trackerReportDao.retrieveTrackerData();				
		return new ModelAndView("trackerReport", "trackerReportDetail", trackerReportDetail);
	}
	
	
	
}
