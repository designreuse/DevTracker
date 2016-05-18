<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
   
    <meta charset="utf-8">
    <title>C M T</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
  <%@include file="CommonRepo.jsp" %>

    <!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>

<script src="js/jquery.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	
	$('#inputIMEI').focus();
	
	$('#submitForm').click(function(){
		
		var imei = $('#inputIMEI').val();
		var trackerType =  $('#inputTrackerType').val();
		var simCardNo = $('#inputSimCardNo').val();
		var simcardProvider = $('#inputSimCardProvider').val();
		var phoneNo = $('#inputPhoneNumber').val();
		var letters = /^[0-9]+$/;
		
		if(imei == ''){
			alert("IMEI number must not be empty");
			return false;
		} else{				
			if(imei.length<15){
				alert("IMEI number must not be less than 15 digit");
				return false;
			}
			
			var result = letters.test(imei);			
			if(result == false){
				alert("IMEI number must be numerical!");
				return result;
			}
		} 
		
		if(trackerType == ''){
			alert("Tracker type must not be empty");
			return false;
		}
		
		if(simCardNo == ''){
			alert("SIM card number must not be empty");
			return false;
		}else{			
			var result = letters.test(phoneNo);			
			if(result == false){
				alert("SIM card number must be numerical!");
				return result;
			}				
		} 
		
		if(simcardProvider == ''){
			alert("SIM provider must not be empty");
			return false;
		}
		
		if(phoneNo == ''){
			alert("Phone number must not be empty");
			return false;
		}else{	
			if(phoneNo.length<10){
				alert("Phone number must not be less than 10 digit");
				return false;
			}
			var result = letters.test(phoneNo);			
			if(result == false){
				alert("Phone number must be numerical!");
				return result;
			}
				
		} 
		
		
	});
	
	function disableBack() { window.history.forward() }

    window.onload = disableBack();
    window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
		   
});
</script>	


    
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
            <a href="trackerDetails">Tracker Details</a>
        </li>
    </ul> -->
</div>
<div class=" row">
     
</div>

	
<div class="row">
   
    <!--/span-->

    <div class="box col-md-12" >
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon"></i> Tracker Details</h2>
            </div>
            <div class="box-content">
                <div class="box-content">
                    <div class="bs-example">
   			
			<form:form method="post" action="/DevTracker/confirmDetails" modelAttribute="trackerDetail" class="form-horizontal" >
        <div class="form-group">
            <label for="inputIMEI" class="control-label col-xs-2">IMEI Number</label>
            <div class="col-xs-5">
                <form:input path="imei" type="text" class="form-control capsText" name="inputIMEI" maxlength="15" id="inputIMEI" placeholder="Enter IMEI No" onKeyPress="return numerical(this, event)"/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputtrackerType" class="control-label col-xs-2">Tracker Type</label>
            <div class="col-xs-5">
                <form:input path="type" type="text" class="form-control capsText" name="inputTrackerType" id="inputTrackerType" placeholder="Enter Tracker Type" onKeyPress="return alphanumeric(this, event)"/>
            </div>
        </div>
        
         <div class="form-group">
            <label for="inputSimCard" class="control-label col-xs-2">Sim Card Number</label>
            <div class="col-xs-5">
                <form:input path="sim" type="text" class="form-control capsText" name="inputSimCardNo" id="inputSimCardNo" placeholder="Enter Sim Card No" onKeyPress="return numerical(this, event)"/>
            </div>
        </div>
        
         <div class="form-group">
            <label for="inputSimCardProvider" class="control-label col-xs-2">Sim Card Provider</label>
            <div class="col-xs-5">
                <form:input path="provider" type="text" class="form-control capsText" name="inputSimCardProvider" id="inputSimCardProvider" placeholder="Enter Sim Card Provider"/>
            </div>
        </div>
      
      <div class="form-group">
            <label for="inputPhoneNumber" class="control-label col-xs-2">Phone Number</label>
            <div class="col-xs-5">
                <form:input path="mobile" type="text" class="form-control capsText" name="inputPhoneNumber" maxlength="10" id="inputPhoneNumber" placeholder="Enter Phone Number" onKeyPress="return numerical(this, event)"/>
            </div>
        </div>
        
        
      <%--   <div class="form-group">
                       <label for="connectedWith" class="control-label col-xs-2">Connected with</label>
 					<div class="col-xs-5">
                        <form:select path="connectedWith" items="${map.vehicleList}" data-placeholder="Tracker Connected With" id="selectError2" data-rel="chosen" />
                    </div>
        </div> --%>
                   
                   <div class="form-group"> 
                    <p class="center col-md-5">
                    <input type="submit" id="submitForm" value="Save Tracker" title="Save Tracker Details." data-toggle="tooltip" data-placement="left" style="margin-left:5%"  class="btn btn-primary" />
                       
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
