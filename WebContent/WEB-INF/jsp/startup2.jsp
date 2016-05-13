<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>C M T</title>
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta name="apple-mobile-web-app-status-bar-style" content="black" />
        <link rel="shortcut icon" href="login/img/favicon.ico">
        <link rel="stylesheet" href="login/css/bootstrap.css">
        <link rel="stylesheet" href="login/css/flat-ui.css">
        <link rel="stylesheet" href="login/css/icon-font.css">
        <link rel="stylesheet" href="login/css/style.css">
        <script src="login/js/jquery-1.10.2.min.js"></script>
        <script src="login/js/bootstrap.min.js"></script>
        <script src="login/js/modernizr.custom.js"></script>
        <script src="login/js/startup-kit.js"></script>
        <script src="login/js/script.js"></script>
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
	    	   document.location.href='/DevTracker/assignVehicle';
	       }else if(msg == "3"){
	    	   document.location.href='/DevTracker/driverDetails';
	       }else if(msg == "4"){
	    	   document.location.href='/DevTracker/agent';
	       }else if(msg == "false"){
	    	   $("#login_fail").show(); 
	    	   //document.location.href='/DevTracker/startup';
	       }
	      });  
		   
   }); 
    	    	 
    	 $("#locateVehicle").on('click', function(){
    		 var invoice = $("#searchInvoice").val(); 
    		dataString = "searchInvoice=" + invoice;	
    		document.location.href='/DevTracker/trackInMap?searchInvoice='+ invoice;
  		   
    		      
    	 
    	 }); 
    	 
    	
    	 
     });
    
    
    </script>
         
    </head>

    <body>
        <div class="page-wrapper">
            <!-- header-11 -->
            <header class="header-11">
                <div class="container">
                    <div class="row">
                        <div class="navbar col-sm-12" role="navigation">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle"></button>
                                <a class="brand" href="#"><img src="login/img/logo@2x.png" width="60" height="50" alt=""> Cargo Movement Trackers</a>
                            </div>
                         <!-- <div class="collapse navbar-collapse pull-right">
                              
                              <form class="navbar-form pull-left">
                                    <a class="btn btn-primary" href="/DevTracker/payForPlay" >Pay for Play</a>
                                </form>
                              
                                <form class="navbar-form pull-left">
                                    <a class="btn btn-primary" href="" data-toggle="modal" data-target=".loginform">SIGN IN</a>
                                </form>
                            </div> -->
                        </div>
                    </div>
                </div>
            </header>
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
                                        <input class="form-control" type="text" id="searchInvoice" placeholder="Search Invoice Number">
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

           
           <section class="content-13 subscribe-form bg-white">
                <div class="container">
                    <div class="row">
                       
                    </div>
                </div>
            </section>
        </div>
                
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
																	<input id="login_username" type="text" class="form-control" placeholder="Username">
																</div>
																<div class="form-group">
																	<input id="password" type="password" class="form-control" placeholder="Password">
																</div>
																<div class="form-group">																	
																		<label>																			
																			<span id="login_fail" class="response_error" style="font-size:15px;display: none;">Login failed, please try again.</span>
																		</label>
																</div>																
															</div>
															
														</div>
														<div class="modal-footer">
															<a data-toggle="modal" data-target=".forgotpassword" href="#" style="float:left;font-size:14px;">Forgot Password</a>
															<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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

        <!-- Placed at the end of the document so the pages load faster -->
        
    </body>
</html>