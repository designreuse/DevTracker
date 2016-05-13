package com.tmt.logistics.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tmt.common.DataField;
import com.tmt.common.Utils;
import com.tmt.logistics.bean.CreateLogin;
import com.tmt.logistics.bean.ResultBean;
import com.tmt.logistics.dao.CreateLoginDao;

@Controller
public class CreateLoginController {

	@Autowired
	CreateLoginDao createLoginDao;

	@RequestMapping("/CreateLogin")
	public ModelAndView getConnectTrackerScreen(@ModelAttribute CreateLogin createLogin) {			
		   
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			map.put("roles", Arrays.asList("Owner", "Broker", "Office Assistant", "Fitter"));		
			return new ModelAndView("CreateLogin", "map", map);
		}

	@RequestMapping(value = "/InsertCreateLogin", method = RequestMethod.POST)
	public ModelAndView insertCreateLoginData(@ModelAttribute CreateLogin createLogin) {
		try {
			Utils.trimReflective(createLogin);
		} catch (Exception e) {
			System.out.println("Error Occurs");
		}
		System.out.println(createLogin.getUserName()+" ======> "+createLogin.getPassword()+"    =========>  "+createLogin.getConfirmPassword());
		   createLogin.setRole_id(DataField.valueOf(createLogin.getRole().toLowerCase().replaceAll(" ", "")).toString());
		   System.out.println("======> "+ Utils.showBean(createLogin, true));
		   
		    int pageRedirect = createLoginDao.insertLoginData(createLogin);
		   
		   ResultBean resultMsg = new ResultBean();
			if(pageRedirect == 1){
				resultMsg.setSuccessMessage("Login "+createLogin.getUserName()+" is successfully created!");
				resultMsg.setShowResult("1");
			}else{			
				resultMsg.setErrorMessage("Login creation failed!");
				resultMsg.setShowResult("0");			
			}		
			resultMsg.setPageUrl("/DevTracker/CreateLogin");
			return new ModelAndView("ResultPage", "resultMsg", resultMsg);
		}	
	
	@RequestMapping("/confirmCreateLogin")
	public ModelAndView getConfirmScreen(@ModelAttribute CreateLogin createLogin) {		
		int isExists = createLoginDao.isLoginExists(createLogin);
		ResultBean resultMsg = new ResultBean();
			if(isExists == 1){
					resultMsg.setErrorMessage("Login already exists!");
					resultMsg.setShowResult("0");	
					resultMsg.setPageUrl("/DevTracker/CreateLogin");
					return new ModelAndView("ResultPage", "resultMsg", resultMsg);
			}else{
				return new ModelAndView("ConfirmLoginDetails", "map", null);
			}
	}
}
