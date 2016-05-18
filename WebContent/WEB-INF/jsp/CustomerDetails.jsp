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
	$('#driverDet').hide();
	
    $('input[type="checkbox"]').click(function(){

        if($(this).prop("checked") == true){
            $('#driverDet').show();
        }

        else if($(this).prop("checked") == false){
            $('#driverDet').hide();
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
    <!-- <ul class="breadcrumb">
        <li>
            <a href="home">Home</a>
        </li>
        <li>
            <a href="vehicleDetails">Client Details</a>
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
                <h2><i class="glyphicon"></i> Client Details</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                </div>
            </div>
            <div class="box-content">
                <div class="box-content">
                    <div class="bs-example">
    
    
    
   <form:form method="post" action="/DevTracker/CustomerAttach" modelAttribute="customerDetails" class="form-horizontal">    
        
         <div class="form-group">
            <label for="custName" class="control-label col-xs-2">Client Name </label>
            <div class="col-xs-5">
                <form:input path="custName" type="custName" class="form-control" id="custName" placeholder="Enter Customer Name"/>
            </div>
        </div>
        
         <div class="form-group">
            <label for="address" class="control-label col-xs-2">Address</label>
            <div class="col-xs-5">
                <form:textarea path="address" class="autogrow" style="width:60%;" placeholder="Enter Address Details."/>
            </div>
        </div>
      
      <div class="form-group">
            <label for="mobile" class="control-label col-xs-2">Mobile</label>
            <div class="col-xs-5">
                <form:input path="mobile" type="mobile" class="form-control" id="mobile" placeholder="Enter Mobile Number"/><br>
                <%-- <form:input path="ownerMobile1" type="ownerMobile1" class="form-control" id="ownerMobile1" placeholder="Enter Owner Second Mobile Number"/><br>
                <form:input path="ownerMobile2" type="ownerMobil1e2" class="form-control" id="ownerMobile2" placeholder="Enter Owner Third Mobile Number"/> --%>
            </div>
        </div>
        
        <form:hidden path="parent_id" value="${handler_id}"/>
        
        <%-- <div class="form-group">
       		 <div class="checkbox">
                    <label style="padding-left:23%;">
                        <input type="checkbox" id="driver">
                        Click to enter driver details of this owner
                    </label>
       		 </div>
        </div>
        <div id="driverDet">      
            <div class="form-group">
            <label for="driverName" class="control-label col-xs-2">Driver Name</label>
            <div class="col-xs-5">
            <form:input path="driverName" type="driverName" class="form-control" id="driverName" placeholder="Enter Driver Name"/>
            </div>
        </div>
        
         <div class="form-group">
            <label for="driverMobNum" class="control-label col-xs-2">Driver Mobile</label>
            <div class="col-xs-5">
            <form:input path="driverMobile" type="driverMobile" class="form-control" id="driverMobile" placeholder="Enter Driver Mobile Number"/>
            </div>
        </div>
        
         <div class="form-group">
            <label for="driverAddress" class="control-label col-xs-2">Driver Address</label>
            <div class="col-xs-5">
            <form:textarea path="driverAddress" class="autogrow" style="width:60%; height: 57px;" placeholder="   Enter Driver Address Details."/>
            </div>
        </div>
      
      <div class="form-group">
            <label for="driverLicenseNumber" class="control-label col-xs-2">Driver License</label>
            <div class="col-xs-5">
            <form:input path="driverLicense" type="driverLicense" class="form-control" id="driverLicense" placeholder="Enter Driver License Number"/>
            </div>
        </div>
 </div>  --%>     
       <div class="form-group"> 
                    <p class="center col-md-5">
                    <input type="submit" value="Client Details" title="Submit Client Details" data-toggle="tooltip" data-placement="left" style="margin-left:5%"  class="btn btn-primary" />
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


</body>
</html>
