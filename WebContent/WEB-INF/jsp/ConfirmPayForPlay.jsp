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
       
    <form:form method="post" action="/DevTracker/confirmPlay4PayData" modelAttribute="PlayForPay" >
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well">
                <h2><i class="glyphicon"></i> Pay for Play - Confirmation</h2>
                
            </div>
            <div class="box-content">
                <table class="table table-bordered table-striped">
                   
                    <tbody>
                    <tr>
                        <td>
                            <label  class="control-label"> Vehicle Number </label>
                        </td>
                        <td>
                            <code class="capsText">${payForPlay.vehicleNumber}</code>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label  class="control-label "> Invoice Number  </label> 
                        </td>
                        <td>
                           <code>${payForPlay.invoiceNumber}</code>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label  class="control-label ">From Address</label>
                        </td>
                        <td>
                            <code>${payForPlay.fromAddress}</code>
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label  class="control-label ">To Address</label>
                        </td>
                        <td>
                            <code>${payForPlay.toAddress}</code>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label  class="control-label ">Total Distance</label>
                        </td>
                        <td>
                           <code>${payForPlay.distance} </code>
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
        <form:hidden path="vehicleNumber" value="${payForPlay.vehicleNumber}"/> 
        <form:hidden path="invoiceNumber" value="${payForPlay.invoiceNumber}"/>
        <form:hidden path="fromAddress" value="${payForPlay.fromAddress}"/> 
        <form:hidden path="toAddress" value="${payForPlay.toAddress}"/>
        <form:hidden path="imeiNumber" value="${payForPlay.imeiNumber}"/>
        <form:hidden path="fromAddressLatitude" value="${payForPlay.fromAddressLatitude}"/>
        <form:hidden path="fromAddressLongitude" value="${payForPlay.fromAddressLongitude}"/>
        <form:hidden path="toAddressLatitude" value="${payForPlay.toAddressLatitude}"/>
        <form:hidden path="toAddressLongitude" value="${payForPlay.toAddressLongitude}"/>
        <form:hidden path="distance" value="${payForPlay.distance}"/>
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
