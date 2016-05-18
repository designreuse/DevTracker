<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

        <div id="content" class="col-lg-10 col-sm-9">
            <!-- content starts -->
            <div>
   
</div>
<div class=" row">
  
  

    
</div>

	


<div class="row">
       
    <form:form method="post" action="/DevTracker/driver" modelAttribute="driverDetails" >
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well">
                <h2><i class="glyphicon"></i> Driver Details - Confirmation</h2>
                
            </div>
            <div class="box-content">
                <table class="table table-bordered table-striped">
                   
                    <tbody>
                    <tr>
                        <td>
                            <label  class="control-label"> Vehicle Number </label>
                        </td>
                        <td>
                            <code>${driverDetails.vehicleNumber}</code>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label  class="control-label "> Driver Name  </label> 
                        </td>
                        <td>
                           <code> ${driverDetails.driverName}</code>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label  class="control-label ">Driver Mobile</label>
                        </td>
                        <td>
                            <code>${driverDetails.driverMobile}</code>
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label  class="control-label ">Driver Address</label>
                        </td>
                        <td>
                            <code>${driverDetails.driverAddress}</code>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label  class="control-label ">Driver License</label>
                        </td>
                        <td>
                           <code>${driverDetails.driverLicense} </code>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
             <div class="form-group">                     
                    <input type="submit" value="Submit Details" title="Submit  Details" data-toggle="tooltip" data-placement="left" style="margin-left:38%"  class="btn btn-primary" />
             </div>           
        </div>
    </div>
    
        <form:hidden path="vehicleNumber" value="${trackerDetail.vehicleNumber}"/>
        <form:hidden path="driverName" value="${driverDetails.driverName}"/> 
        <form:hidden path="driverMobile" value="${driverDetails.driverMobile}"/>
        <form:hidden path="driverAddress" value="${driverDetails.driverAddress}"/> 
        <form:hidden path="driverLicense" value="${driverDetails.driverLicense}"/>
      </form:form>
    
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
