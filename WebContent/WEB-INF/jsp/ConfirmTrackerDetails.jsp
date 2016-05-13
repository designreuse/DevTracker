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
       
    <form:form method="post" action="/DevTracker/insertTrackerData" modelAttribute="trackerDetail" >
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well">
                <h2><i class="glyphicon"></i> Tracker Details - Confirmation</h2>
                
            </div>
            <div class="box-content">
                <table class="table table-bordered table-striped">
                   
                    <tbody>
                    <tr>
                        <td>
                            <label  class="control-label"> IMEI Number </label>
                        </td>
                        <td>
                            <code>${trackerDetail.imei}</code>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label  class="control-label "> Tracker Type  </label> 
                        </td>
                        <td>
                           <code> ${trackerDetail.type}</code>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label  class="control-label ">Sim Card Number</label>
                        </td>
                        <td>
                            <code>${trackerDetail.sim}</code>
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label  class="control-label ">Sim Card Provider</label>
                        </td>
                        <td>
                            <code>${trackerDetail.provider}</code>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label  class="control-label ">Phone Number</label>
                        </td>
                        <td>
                           <code>${trackerDetail.mobile} </code>
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
        <form:hidden path="imei" value="${trackerDetail.imei}"/> 
        <form:hidden path="type" value="${trackerDetail.type}"/>
        <form:hidden path="sim" value="${trackerDetail.sim}"/> 
        <form:hidden path="provider" value="${trackerDetail.provider}"/>
        <form:hidden path="mobile" value="${trackerDetail.mobile}"/>
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
