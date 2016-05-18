<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
   
    <meta charset="utf-8">
    <title>C M T</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
<%@include file="CommonRepo.jsp" %>
    <!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>

    
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

<script type="text/javascript">

 $(document).ready(function() {	 
	 $('button').on('click', function() {
		 var getTokens  = this.id.split('_'); 		 
		 if(this.id != ''){			 
			 if(getTokens[0].match("^veh")){			
				 var confrm = confirm("Remove mapping with this vehicle "+getTokens[1]);
				 if(confrm == true){			 
					 dataString = "dataString=" + getTokens[1]+"#"+${role_id}+"#"+${handler_id};	
					   $.ajax({
					        type: "POST",
					        url: "disassociateVehicle",
					        data: dataString,
				            dataType: "json",
				            //if received a response from the server
				            success: function( data, textStatus, jqXHR) {
				                //our country code was correct so we have some information to display
				                 if(data.success){  
				                	document.location.href='/DevTracker/customerReport';
				                 } 
				                 //display error message
				                 else {
				                     $("#ajaxResponse").html("<div><b>Invalid Vehicle Number!</b></div>");
				                 }
				            }
						   
				   });
				 }		
			 }else{
				 var confrm = confirm("Remove mapping with this broker ");
				 if(confrm == true){			 
					 dataString = "dataString=" + getTokens[1]+"#"+${handler_id}+"#"+getTokens[2];	
					   $.ajax({
					        type: "POST",
					        url: "disassociateBroker",
					        data: dataString,
				            dataType: "json",
				            //if received a response from the server
				            success: function( data, textStatus, jqXHR) {
				                //our country code was correct so we have some information to display
				                 if(data.success){  
				                	document.location.href='/DevTracker/customerReport';
				                 } 
				                 //display error message
				                 else {
				                     $("#ajaxResponse").html("<div><b>Invalid Vehicle Number!</b></div>");
				                 }
				            }
						   
				   });
				 } 
			 }
			 }
	 });
}); 

</script>

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
            <a href="#">Home</a>
        </li>
        <li>
            <a href="#">Customer Report</a>
        </li>
    </ul> -->
</div>
<div class=" row">
  
  

    
</div>

	


  <div class="row">
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon"></i> Customer Report</h2>
    </div>
    <div class="box-content">
   
    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
    <thead>
    <tr>
        <th>Vehicle Number</th>
        <th>Owner</th>        
        <th>Broker</th>       
        <th>Admin</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="customerReportDetailList" items="${customerReportDetailList}">
    <tr>
        <td class="center">${customerReportDetailList.vehicle_number}</td>
        <td class="center">${customerReportDetailList.ownerName}</td>
        <td class="center">${customerReportDetailList.brokerName}</td>      
        <td class="center">${customerReportDetailList.username}</td>        
        <td class="col-md-4">            
            <c:if test="${customerReportDetailList.map_status == 'Y'}">
            <button class="btn btn-danger" id="veh_${customerReportDetailList.vehicle_number}">
                Remove Vehicle
            </button>
            </c:if>
            <c:if test="${customerReportDetailList.brokerName != null}">
            <button class="btn btn-danger" id="bro_${customerReportDetailList.phone}_${customerReportDetailList.vehicle_number}">
                Remove Broker
            </button>
            </c:if>
        </td>
    </tr>
    </c:forEach>    
    </tbody>
    </table>
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


</body>
</html>
