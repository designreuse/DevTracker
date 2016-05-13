<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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




<script src="https://maps.googleapis.com/maps/api/js??sensor=false"></script>

<script type="text/javascript">
$(document).ready(function(){
	
$('#submitForm').click(function(){
		
		var vehicleNumber = $('#vehicleNumber').val();
		var invoiceNumber =  $('#invoiceNumber').val();
		var fromAddress = $('#fromAddress').val();
		var select1 = $('#select1').val();
		
		if(vehicleNumber == ''){
			alert("Vehicle number must not be empty");
			return false;
		}
		
		if(invoiceNumber == ''){
			alert("Invoice number must not be empty");
			return false;
		}
		
		if(fromAddress == ''){
			alert("From address must not be empty");
			return false;
		}
		
		if(select1 == 'NONE'){
			alert("To address must be selected");
			return false;
		}
		
});
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
   <!--  <ul class="breadcrumb">
        <li>
            <a href="home">Home</a>
        </li>
        <li>
            <a href="vehicleDetails">CF Agent</a>
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
                <h2><i class="glyphicon"></i> CF Agent</h2>
        </div>
            <div class="box-content">
                <div class="box-content">
                    <div class="bs-example">
    
   <form:form method="post" action="/DevTracker/confirmAgentData" modelAttribute="CFAgent" class="form-horizontal">    
        
         <div class="form-group">
            <label for="vehicleNumber" class="control-label col-xs-2"> Vehicle Number </label>
            <div class="col-xs-3">
                <form:input path="vehicleNumber" type="vehicleNumber" class="form-control capsText" id="vehicleNumber" placeholder="Enter Vehicle Number" onKeyPress="return alphanumeric(this, event)"/>
            </div>
        </div>
        
        </br>
        <div class="form-group">
            <label for="invoiceNumber" class="control-label col-xs-2"> Invoice Number </label>
            <div class="col-xs-3">
                <form:input path="invoiceNumber" type="invoiceNumber" class="form-control capsText" id="invoiceNumber" placeholder="Enter Invoice Number" onKeyPress="return numerical(this, event)"/>
            </div>
        </div>
        <br>
        <div class="form-group">
            <label for="fromAddress" class="control-label col-xs-2"> From Address</label>
            <div class="col-xs-3">
                <form:input path="fromAddress" type="fromAddress" class="form-control capsText" id="fromAddress" placeholder="Search Address"/>
            </div>
        </div>
        <br>
        <div class="form-group">
            <label for="toAddress" class="control-label col-xs-2">To Address </label>
            <div class="col-xs-3">
                   <form:select path="toAddress" id="select1" data-rel="chosen" >
                      
                       <form:option value="NONE" label="----------- Select ------------"/>
                       <form:options items="${map.addressList}" /> 
                       </form:select>
                       <form:errors path="toAddress" cssClass="error" id="ajaxResponse"/>
                    </div>
        </div>
        <form:hidden path="parent_id" value="${handler_id}"/>
        <form:hidden path="role_id" value="${role_id}"/> 
       <div class="form-group"> 
                    <p class="center col-md-5">
                    <input type="submit" id="submitForm" value="Agent Details" title="Submit Agent Details" data-toggle="tooltip" data-placement="left" style="margin-right:25%"  class="btn btn-primary" />
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

 <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false&libraries=places"></script>
    <script type="text/javascript">
        google.maps.event.addDomListener(window, 'load', function () {
            var places = new google.maps.places.Autocomplete(document.getElementById('fromAddress'));
     
        });
    </script>

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


</body>
</html>
