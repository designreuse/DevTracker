<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
   
    <meta charset="utf-8">
    <title>C M T</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
  <%@include file="CommonRepo.jsp" %>

    
    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">
   
<script src="bower_components/datepicker/jquery-1.js"></script>
<link rel="stylesheet" href="bower_components/datepicker/datepicker.css">
<link rel="stylesheet" href="bower_components/datepicker/bootstrap1.css">

<script src="bower_components/datepicker/bootstrap-datepicker.js"></script>

<script type="text/javascript">
            // When the document is ready
            $(document).ready(function () {
                
                 $('#input-fromDateRange').datepicker({                	 
                    todayBtn: "linked"
                });
                 
                 $('#input-toDateRange').datepicker({                	 
                     todayBtn: "linked"
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
    
</div>
<div class=" row">
     
</div>

	
<div class="row">
   
    <!--/span-->

    <div class="box col-md-12" >
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon"></i> Live Tracking</h2>
            </div>
            <div class="box-content">
                <div class="box-content">
                    <div class="bs-example">
   			
			<form:form method="post" action="/DevTracker/confirmDetails" modelAttribute="liveTrack" class="form-horizontal" >
        
  <div class="form-group">
    <label for="vehicleNumber" class="control-label col-xs-3" style="padding-top:7px;"> Vehicle Number </label>
    <div class="col-xs-3" >
       <div class="form-group">            
            <div class="col-xs-3">
                   <form:select path="vehicleNumber" id="selectError2" data-rel="chosen" style="width: 171px;">                      
                      <form:option value="DISABLED" label="----------- Select ------------"/>
                      <form:options items="${map.vehicleNum}" />
                      </form:select>
                      <form:errors path="vehicleNumber" cssClass="error" id="ajaxResponse"/>
            </div>
        </div>               
     </div>   
  </div> 
  <div class="form-group">
     <label for="dateRange" class="control-label col-xs-3">Date Range</label>
     <div class="col-xs-3 input-group" style="margin-left: 33.5%;">
            	<form:input path="fromDate" type="text" maxlength="5" class="form-control" id="input-fromDateRange" placeholder="From Date" style="width:100%; padding-right:20px;"/>
            	<span class="input-group-addon" style="border-left: 0; border-right: 0;">-</span>
                <form:input path="toDate" type="text" class="form-control" id="input-toDateRange" placeholder="To Date"/>            	
      </div>
   </div>
   <div class="form-group">
            <label for="inputSimCard" class="control-label col-xs-3" style="padding-top:20px;">Frequency</label>
            <div class="col-xs-3">
               <div class="form-group">
            
            <div class="col-xs-3" style= "padding-top: 15px;">
                   <form:select path="frequency" id="selectError2" data-rel="chosen"  style= "width: 171px;">
                      
                      <form:option value="DISABLED" label="----------- Select ------------"/>
                       <form:options items="${map.vehicleNum}" />
                       </form:select>
                       <form:errors path="frequency" cssClass="error" id="ajaxResponse"/>
                    </div>
        </div>   </div>
        </div>
          
                   <div class="form-group"> 
                    <p class="center col-md-5">
                    <input type="submit" id="fetchReport" value="Fetch Report" title="Fetch Report." data-toggle="tooltip" data-placement="left" style="margin-left:5%"  class="btn btn-primary" />
                       
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
