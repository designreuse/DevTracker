package com.tmt.logistics.controller;

/**
 *
 * @author Gerald Amalraj
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmt.logistics.bean.ResultBean;
import com.tmt.logistics.bean.TrackerDetail;
import com.tmt.logistics.dao.TrackerDao;

@Controller
public class TrackerDetailsController {
	
		
	@Autowired
	TrackerDao trackerdao;
	
	
	@RequestMapping("/trackerDetails")
	public ModelAndView getTrackerScreen(@ModelAttribute TrackerDetail trackerDetail) {		
		 /*List<String> vehicleList = trackerService.getTrackerList();		  
		 Map<String, List<String>> map = new HashMap<String, List<String>>();		  
		 map.put("vehicleList", vehicleList);	*/	
		return new ModelAndView("trackerDetails", "map", null);
	}
	
	
	@RequestMapping("/confirmDetails")
	public ModelAndView getConfirmScreen(@ModelAttribute TrackerDetail trackerDetail) {		
		
		int pageRedirect = trackerdao.isTrackerExists(trackerDetail);
		System.out.println("--------------------------> "+pageRedirect);
		ResultBean resultMsg = new ResultBean();
		if(pageRedirect > 0){			
			resultMsg.setErrorMessage("IMEI number "+trackerDetail.getImei()+" has already been registerd!");
			resultMsg.setShowResult("0");
			resultMsg.setPageUrl("/DevTracker/trackerDetails");
			return new ModelAndView("ResultPage", "resultMsg", resultMsg);
		}else{			
			return new ModelAndView("ConfirmTrackerDetails", "map", null);
		}
	}
	
	@RequestMapping("/insertTrackerData")
	public ModelAndView insertTrackerData(@ModelAttribute TrackerDetail trackerDetail) {
		System.out.println("Tracker Detail:::  " + trackerDetail.getImei()+" ------> "+trackerDetail.getType()+" ------> "+trackerDetail.getMobile()+" ---> "+trackerDetail.getProvider()+ " -------> "+trackerDetail.getSim()+ " -----> "+trackerDetail.getConnectedWith() );
		
		int pageRedirect = trackerdao.insertTrackerData(trackerDetail);
		
		ResultBean resultMsg = new ResultBean();
		if(pageRedirect == 1){
			resultMsg.setSuccessMessage("IMEI number "+trackerDetail.getImei()+" has been successfully registerd!");
			resultMsg.setShowResult("1");
		}else{			
			resultMsg.setErrorMessage("IMEI number "+trackerDetail.getImei()+" has not been registerd!");
			resultMsg.setShowResult("0");			
		}
		resultMsg.setPageUrl("/DevTracker/trackerDetails");
		return new ModelAndView("ResultPage", "resultMsg", resultMsg);
	}

	
}
