package com.tmt.logistics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tmt.common.Utils;
import com.tmt.logistics.bean.LoginBean;
import com.tmt.logistics.dao.LoginDao;

@SessionAttributes({"loginData"})
public class LoginServlet  implements HttpRequestHandler {
	
	private static final long serialVersionUID = 1L;

    private LoginDao loginDao;
		
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			IOException {
		
		response.setContentType("text/html");
		boolean isToOpen = false;
				
		String userName = request.getParameter("userName").toLowerCase().trim();
	    String password = request.getParameter("password").toLowerCase().trim();
	    String phone    = request.getParameter("phone");
	    	    
	    LoginBean loginData = loginDao.isSigninAllowed(userName, password);
	    	  
	    if(loginData != null){
	    System.out.println("username:: "+loginData.getUserName()+ " Handler_ID: "+loginData.getHandler_id()+" Role_id ::: "+loginData.getRole_id());
	    Utils.setParent_id(loginData.getHandler_id());
	    HttpSession session=request.getSession();  
        session.setAttribute("username",loginData.getUserName());  
        session.setAttribute("handler_id", loginData.getHandler_id());
        session.setAttribute("role_id", loginData.getRole_id());
       
       //setting session to expiry in 10 mins
       // session.setMaxInactiveInterval(10*60);
	    }
	    //response.setContentType("text/plain");
        response.getWriter().write(String.valueOf(loginData != null  ? loginData.getRole_id() : false));
		
	}

}
