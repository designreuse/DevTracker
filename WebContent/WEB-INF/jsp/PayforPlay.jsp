<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
   
    <meta charset="utf-8">
    <title>C M T</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
<%@include file="CommonRepo.jsp" %>
    <!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>


<script type="text/javascript">
$(document).ready(function(){
	
	$('#submitForm').click(function(){
	
		var vehicleNumber = $('#vehicleNumber').val();
		var invoiceNumber =  $('#invoiceNumber').val();
		var fromAddress = $('#fromAddress').val();
		var toAddress = $('#toAddress').val();
		
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
		
		if(toAddress == ''){
			alert("To address must not be empty");
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
    <div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
            <button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp" style="width:30%"> 
                <span>C M T</span></a>

                       

        </div>
    </div>
<div class="ch-container">
    <div class="row">
         <div class="col-sm-2 col-lg-2">
            <div class="">
                <div class="nav-canvas">
                  
                    <ul class="nav nav-pills nav-stacked main-menu">
                       
                       <li style="margin-left:0px;padding-top:9px;"><a class="ajax-link" href="startup"><i class=" glyphicon"></i><span> Return Home</span></a></li>  
                       
                        
                       </ul>
                </div>
            </div>
        </div>
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
   
</div>

	


<div class="row">
   
    <!--/span-->

    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon"></i> Pay for Play</h2>
            </div>
            <div class="box-content">
                <div class="box-content">
                    <div class="bs-example">
    
   <form:form method="post" action="/DevTracker/confirmPayForPlay" modelAttribute="PlayForPay" class="form-horizontal">    
        
         <div class="form-group">
            <label for="vehicleNumber" class="control-label col-xs-2"> Vehicle Number </label>
            <div class="col-xs-3" style="width:28.5%">
               
            <form:select path="vehicleNumber" id="selectError2" data-rel="chosen" >
                      
                      <form:option value="DISABLED" label="----------- Select ------------"/>
                       <form:options items="${map.vehicleNum}" />
                       </form:select>
                       <form:errors path="vehicleNumber" cssClass="error" id="ajaxResponse"/>
           
           
            </div>
        </div>
        
        </br>
        <div class="form-group">
            <label for="invoiceNumber" class="control-label col-xs-2"> Invoice Number </label>
            <div class="col-xs-3 input-group " style="margin-left: 43.5%;">
            	<form:input path="compAbbrv" type="text" maxlength="5" class="form-control capsText" placeholder="Abbrv" style="width:99%"/>
            	<span class="input-group-addon" style="border-left: 0; border-right: 0;">-</span>
                <form:input path="invoiceNumber" type="invoiceNumber" class="form-control" id="invoiceNumber" placeholder="Number"/>
            	
            </div>
        </div>
        <br>
        <div class="form-group">
            <label for="fromAddress" class="control-label col-xs-2"> From Address</label>
            <div class="col-xs-3" style="width:28.5%">
                <form:input path="fromAddress" type="fromAddress" class="form-control" id="fromAddress" placeholder="Search From Address"/>
            </div>
        </div>
        <br>
        <div class="form-group">
            <label for="toAddress" class="control-label col-xs-2">To Address </label>
            <div class="col-xs-3" style="width:28.5%">
                   <form:input path="toAddress" type="toAddress" class="form-control" id="toAddress" placeholder="Search To Address"/>
            </div>
        </div>
        
       <div class="form-group"> 
                    <p class="center col-md-5">
                    <input type="submit" id="submitForm" value="Confirm	 Details" title="Confirm Details" data-toggle="tooltip" data-placement="left" style="margin-right:25%"  class="btn btn-primary" />
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
  <!-- <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false&libraries=toplaces"></script> -->
    <script type="text/javascript">
        google.maps.event.addDomListener(window, 'load', function () {
            var places = new google.maps.places.Autocomplete(document.getElementById('fromAddress'));
     		var places = new google.maps.places.Autocomplete(document.getElementById('toAddress'));
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
