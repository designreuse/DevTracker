<!DOCTYPE html>
<html lang="en">
<head>
   
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    

    <link href="css/charisma-app.css" rel="stylesheet">
  

        <link rel="shortcut icon" href="login/img/favicon.ico">
        <link rel="stylesheet" href="login/css/bootstrap.css">
        <link rel="stylesheet" href="login/css/flat-ui.css">
        <link rel="stylesheet" href="login/css/icon-font.css">
        <link rel="stylesheet" href="login/css/style.css">
        <script src="login/js/jquery-1.10.2.min.js"></script>
        <script src="login/js/bootstrap.min.js"></script>
        <script src="login/js/modernizr.custom.js"></script>
        <script src="login/js/startup-kit.js"></script>

    <!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

   <script src="bower_components/jquery/jquery.min.js"></script> 
          <script type="text/javascript">
  
     $(document).ready(function() {
    	 
        $('#login_btn').on('click', function() {
    	 
    	 
	   var uname = $("#login_username").val(); 
	   var passwrd = $("#password").val(); 
	  
	   $.ajax({
	        type: "POST",
	        url: "LoginServlet",
	        data: { userName : uname, password:passwrd  }
	      }).done(function( msg ) {
	       if(msg == "1"){
	    	   document.location.href='/DevTracker/trackerDetails';
	       }else if(msg == "2"){
	    	   document.location.href='/DevTracker/vehicleSearch';
	       }else if(msg == "3"){
	    	   document.location.href='/DevTracker/vehicleSearch';
	       }else if(msg == "4"){
	    	   document.location.href='/DevTracker/trackerDetails';
	       }else if(msg == "5"){
	    	   document.location.href='/DevTracker/trackerConfigure';
	       }else{
	    	   $("#login_fail").show();
	       }
	      });  
		   
   }); 
    	    	 
    	 $("#locateVehicle").on('click', function(){
    		var invoice = $("#searchInvoice").val(); 
    		 
    		var dataString = "searchInvoice="+invoice;
    		  if(invoice != ''){ 
    			  document.location.href='/DevTracker/trackInMap?searchInvoice='+invoice;
    			 /*  $.ajax({
    			        type: "POST",
    			        url: "trackInMap",
    			        data: dataString,
    		            dataType: "json",
    		            //if received a response from the server
    		            success: function( data) {
    		               alert(data.success);
    		                 if(data.success ){  
    		                	 document.location.href='/DevTracker/Map';
    		                 }else {
    		                     $("#ajaxResponse").html("<div><b>Invoice doesn't exist!</b></div>");
    		                 }
    		            }
    				   
    		   });  */
    	        }else{
    	        	 alert("Invoice number cannot be empty!");
    	        }    
    	 }); 
    	    	 
       	 $('#login').on('hidden.bs.modal', function (e) {
    		  $(this)
    		    .find("input,textarea,select")
    		       .val('')
    		       .end();
    		})
    		
    		 $("#reset").on('click', function(){
    			 $("#login_username").val("");
    			 $("#password").val("");
    		 });
    	 
    	 $('#login').on('shown.bs.modal', function () {
    		    $('#login_username').focus();
    		});
    	 
    	 
     });
    
    
    </script>

</head>

<body>
    <!-- topbar starts -->
    <div class="navbar" role="navigation">

        <div class="navbar-inner"style=" padding-left: 25px;">
            
            <a class="" style="float: left;color: #2c3e50; font-family: Helvetica Neue,Helvetica,Arial,sans-serif;  font-size: 25px; font-weight: 600";
    letter-spacing: -1px;"><img src="login/img/logo@2x.png" width="80" height="50" alt=""> Cargo Movement Trackers</a>

            <!-- user dropdown starts -->
            <div class="btn-group pull-right">
             <a class="btn btn-primary" href="/DevTracker/payForPlay" >Pay for Play</a>
                <a class="btn btn-primary" href="" data-toggle="modal" data-target=".loginform">SIGN IN</a>                
            </div>
            <!-- user dropdown ends -->

        </div>
    </div>
    <!-- topbar ends -->
<div class="ch-container">
    <div class="row">
        
        <section class="header-11-sub ">
                <div class="background" style="padding-bottom:35%">
                    &nbsp;
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-sm-4">
                           
                            <div class="signup-form">
                                <form>
                                    <div class="form-group">
                                        <input class="form-control capsText" type="text" id="searchInvoice" placeholder="Search Invoice Number" onKeyPress="return numerical(this, event)">
                                    </div>									
                                    <div class="form-group">
                                        <button type="button" class="btn btn-block btn-info" id="locateVehicle">Locate Vehicle</button>
                                    </div>
                                </form>
                            </div>
                           
                        </div>
                        
                    </div>
                </div>
            </section>

        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>

    
</div>

</div><!--/row-->
    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div><!--/fluid-row-->


    <hr>

   


</div><!--/.fluid-container-->


<div id="login" class="modal fade loginform" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">												
												<div class="modal-dialog modal-lg">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
															<h4 class="modal-title">Login</h4>
														</div>
														<div class="modal-body" style="width:100%;height:150px;">
															
															<div style="width:250px;height:150px;float:left;margin-right:50px;">
																<div class="form-group">
																	<input id="login_username" type="text" class="form-control capsText" placeholder="Username" autofocus="autofocus" onKeyPress="return alphanumeric(this, event)">
																</div>
																<div class="form-group">
																	<input id="password" type="password" class="form-control" placeholder="PASSWORD" onKeyPress="return alphanumeric(this, event)">
																</div>
																<div class="form-group">																	
																		<label>																			
																			<span id="login_fail" class="response_error" style="font-size:15px;display: none;">Login Failed, Please try again.</span>
																		</label>
																</div>																
															</div>
															
														</div>
														<div class="modal-footer">
															<!-- <a data-toggle="modal" data-target=".forgotpassword" href="#" style="float:left;font-size:14px;">Forgot Password</a> -->
															<button type="button" class="btn btn-primary" id="reset">Reset</button>
															<button type="button" id="login_btn" class="btn btn-primary">Login</button>
														</div>
													</div><!-- /.modal-content -->
												</div>
											</div>

											<div id= "forgot"class="modal fade forgotpassword" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
												<div class="modal-dialog modal-lg">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
															<h4 class="modal-title">Forgot Password</h4>
														</div>
														<div class="modal-body" style="width:100%;height:100px;">
															<p style="font-size:12px;">Please enter the email address </p>
															<div style="width:250px;height:100px;float:left;margin-right:50px;">
																
																<div class="form-group">
																	<input type="text" class="form-control" placeholder="Email">
																</div>
																
															</div>
															
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
															<button type="button" class="btn btn-primary">Reset</button>
														</div>
													</div><!-- /.modal-content -->
												</div>
											</div>




<!-- external javascript -->

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
