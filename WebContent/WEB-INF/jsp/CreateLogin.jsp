<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
   
    <meta charset="utf-8">
    <title>C M T</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
   
    
   <link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="css/charisma-app.css" rel="stylesheet">
    <link href='bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='css/jquery.noty.css' rel='stylesheet'>
    <link href='css/noty_theme_default.css' rel='stylesheet'>
    <link href='css/elfinder.min.css' rel='stylesheet'>
    <link href='css/elfinder.theme.css' rel='stylesheet'>
    <link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='css/uploadify.css' rel='stylesheet'>
    <link href='css/animate.min.css' rel='stylesheet'>

    <!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	
$('#submitForm').click(function(){
		
		var userName = $('#userName').val();
		var password =  $('#password').val();
		var confirmPassword = $('#confirmPassword').val();
		var name = $('#name').val();
		var email = $('#email').val();
		var mobile_num = $('#phone').val();
		var alt_mobile = $('#mobile').val();
		var address = $('#address').val();
		var select1 = $('#select1').val();
		var letters = /^[0-9]+$/;
		var emailVald =  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		
		if(userName == ''){
			alert("User name must not be empty");
			return false;
		}
		
		if(password == ''){
			alert("password must not be empty");
			return false;
		}else{
			if(password.length<6){
				alert("Password length should be atleast 6 characters!");
				return false;
			}
		}
		
		if(confirmPassword == ''){
			alert("Confirm password must not be empty");
			return false;
		}
		
		if(password != confirmPassword){
			alert("Password and Confirm password does not match");
			return false;
		}
		
		if(name == ''){
			alert("Name must not be empty");
			return false;
		}
		
		if(email == ''){
			alert("Email must not be empty");
			return false;
		}else{
			var result = emailVald.test(email);
			if(result == false){
				alert("Email address invalid!");
				return result;
			}
		}
		
		if(mobile_num == ''){
			alert("Mobile number must not be empty");
			return false;
		}else{	
			if(mobile_num.length<10){
				alert("Mobile number must not be less than 10 digit");
				return false;
			}
			var result = letters.test(mobile_num);			
			if(result == false){
				alert("Mobile number must be numerical!");
				return result;
			}
		} 
				
			if(alt_mobile.length<10){
				alert("Alternate mobile number must not be less than 10 digit");
				return false;
			}
			var result = letters.test(alt_mobile);			
			if(result == false){
				alert("Alternate mobile number must be numerical!");
				return result;
			}
		 
		
		if(address == ''){
			alert("Address must not be empty");
			return false;
		}
		
		if(select1 == 'NONE'){
			alert("Role must be selected");
			return false;
		}
		
});
    });
</script>  


<script src="https://maps.googleapis.com/maps/api/js??sensor=false"></script>
    
    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">
    
    
    <style>
      #map_canvas {
        width: 100%;
        height: 420px;
        background-color: #CCC;
      }
      
      .bs-example{
    	margin: 20px;
    }
	/* Fix alignment issue of label on extra small devices in Bootstrap 3.2 */
    .form-horizontal .control-label{
        padding-top: 7px;
        margin-left: 15%;
        padding-right:25%
    }
    </style>


</head>

<body >
   <%@include file="Header.jsp" %>
<div class="ch-container">
    <div class="row">
        <%@include file="LeftMenu.jsp" %>
        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>

        <div id="content" class="col-lg-9 col-sm-9">
            <!-- content starts -->
            <div>
    <!-- <ul class="breadcrumb">
        <li>
            <a href="home">Home</a>
        </li>
        <li>
            <a href="trackerDetails">Create Login</a>
        </li>
    </ul> -->
</div>
<div class=" row">
     
</div>

	
<div class="row">
   
    <!--/span-->

    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon"></i> Create Login</h2>

            </div>
            <div class="box-content">
                <div class="box-content">
                    <div class="bs-example">
   			
		<form:form method="post" action="/DevTracker/confirmCreateLogin" modelAttribute="createLogin" class="form-horizontal">
        
        
        <%-- <div class="form-group">
            <label for="customerName" class="control-label col-xs-2">Customer Name </label>
            <div class="col-xs-5">
                   <form:select path="customerName" id="selectError2" data-rel="chosen" >
                      
                      <form:option value="NONE" label="----------- Select ------------"/>
                       <form:options items="${map.customerList}" />
                       </form:select>
                       <form:errors path="customerName" cssClass="error" id="ajaxResponse"/>
                    </div>
        </div> --%>
                                 
        <div class="form-group">
            <label for="userName" class="control-label col-xs-2">User Name</label>
            <div class="col-xs-5">
                <form:input path="userName" type="text" class="form-control capsText" id="userName" placeholder="Enter User Name" onKeyPress="return alphanumeric(this, event)"/>
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="control-label col-xs-2">Password</label>
            <div class="col-xs-5">
                <form:input path="password" type="password" class="form-control" id="password" placeholder="ENTER PASSWORD" onKeyPress="return alphanumeric(this, event)"/>
            </div>
        </div>
        
        <div class="form-group">
            <label for="confirmPassword" class="control-label col-xs-2">Confirm Password</label>
            <div class="col-xs-5">
                <form:input path="confirmPassword" type="password" class="form-control" id="confirmPassword" placeholder="CONFIRM PASSWORD" onKeyPress="return alphanumeric(this, event)"/>
            </div>
        </div>
        
        <div class="form-group">
            <label for="name" class="control-label col-xs-2">Company Name </label>
            <div class="col-xs-5">
                <form:input path="name" type="name" class="form-control capsText" id="name" placeholder="Enter Name" onKeyPress="return alphanumeric(this, event)"/>
            </div>
        </div>
        
        <div class="form-group">
            <label for="email" class="control-label col-xs-2">Email</label>
            <div class="col-xs-5">
                <form:input path="email" type="text" class="form-control" id="email" placeholder="ENTER EMAIL ADDRESS"/>
            </div>
        </div>
        
        <div class="form-group">
            <label for="phone" class="control-label col-xs-2">Phone Number</label>
            <div class="col-xs-5">
                <form:input path="phone" type="text" class="form-control" id="phone" maxlength="10" placeholder="ENTER PHONE NUMBER" onKeyPress="return numerical(this, event)"/>
            </div>
        </div>
        
        <div class="form-group">
            <label for="mobile" class="control-label col-xs-2">Alternate Mobile</label>
            <div class="col-xs-5">
                <form:input path="mobile" type="mobile" class="form-control" id="mobile" maxlength="10" placeholder="ENTER MOBILE NUMBER" onKeyPress="return numerical(this, event)"/><br>
            </div>
        </div>
        
        <div class="form-group">
            <label for="address" class="control-label col-xs-2">Address</label>
            <div class="col-xs-5">
                <form:textarea path="address" class="autogrow" style="width:100%;" id="address" placeholder="ENTER ADDRESS DETAILS."/>
            </div>
        </div>
                                    
        <!-- <div class="radio">
                    <label>
                        <input  type="radio" name="radioOpt"  value="2" checked>
                        Owner
                    </label>
                     <label style="padding-left:100px;">
                        <input  type="radio" name="radioOpt"  value="3">
                        Broker
                    </label>
        </div> -->
        
        <div class="form-group">
            <label for="role" class="control-label col-xs-2">Role </label>
            <div class="col-xs-5">
                   <form:select path="role" id="select1" data-rel="chosen" style="width:84%;">
                      
                      <form:option value="NONE" label="----------- Select ------------"/>
                       <form:options items="${map.roles}" />
                       </form:select>
                       <form:errors path="role" cssClass="error" id="ajaxResponse"/>
                    </div>
        </div>
        </br>
   
        <form:hidden path="parent_id" value="${handler_id}"/>
        <form:hidden path="role_id" value="${role_id}"/>                          
        <div class="form-group"> 
                    <p class="center col-md-5">
                   	<input type="submit" id="submitForm" name="connect" value="Create Login" title="Create Login." data-toggle="tooltip" data-placement="left" style="margin-left:5%"  class="btn btn-primary" />
                    </p>
        </div>
       
      </form:form>     

    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!--/span-->
    
    
    
   
    
</div><!--/row-->


</div>
</div>
<%@include file="Footer.jsp" %>

</div>

<script src="bower_components/flot/excanvas.min.js"></script>
<script src="bower_components/flot/jquery.flot.js"></script>
<script src="bower_components/flot/jquery.flot.pie.js"></script>
<script src="bower_components/flot/jquery.flot.stack.js"></script>
<script src="bower_components/flot/jquery.flot.resize.js"></script>
<!-- chart libraries end -->
<script src="js/init-chart.js"></script>


<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='bower_components/moment/min/moment.min.js'></script>
<script src='bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script src="bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script src="bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script src="bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="js/charisma.js"></script>
<script src="js/logistics.js"></script>
   

</body>
</html>
