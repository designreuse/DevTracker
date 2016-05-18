<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">
<head>
   
    <meta charset="utf-8">
    <title>C M T</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
   <%@include file="CommonRepo.jsp" %>
    
    
    <!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>
    <script src="bower_components/jquery/jquery-ui.min.js"></script>
    <script src="bower_components/jquery/listgroup.js"></script>
    <script src="bower_components/jquery/listgroup.min.js"></script>

    
    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">
    
    
    <style>
     
	/* Fix alignment issue of label on extra small devices in Bootstrap 3.2 */
    .form-horizontal .control-label{
        padding-top: 7px;
        margin-left: 15%;
        padding-right:25%
    }
       
        .dual-list .list-group {
            margin-top: 8px;
        }

        .list-left li, .list-right li {
            cursor: pointer;
        }

        .list-arrows {
            padding-top: 100px;
        }

            .list-arrows button {
                margin-bottom: 20px;
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

    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon"></i> Configure Association</h2>
            </div>
            <div class="box-content">
                <div class="box-content">
                    <div class="bs-example">
   			
		<form:form method="post" action="/DevTracker/confirmMap" modelAttribute="configureAssociation" class="form-horizontal">
                
        
         <c:if test="${role_id == 1}">
        <div class="form-group">
            <label for="selectRole" class="control-label col-xs-2">Select Role</label>
            <div class="col-xs-5">
                   <form:select path="selectRole" id="selectRole" data-rel="chosen" >
                      
                      <form:option value="NONE" label="----------- Select ------------"/>
                       <form:options items="${map.configureRoles}" />
                       </form:select>
                       <form:errors path="selectRole" cssClass="error" id="ajaxResponse"/>
            </div>
        </div>
        </c:if>
         <form:hidden path="parent_id" value="${handler_id}"/>
        <form:hidden path="role_id" value="${role_id}"/>   
         
       </form:form>   

    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!--/span-->
    
    
    
   
    
</div><!--/row-->



<div class="row" id="configureAssociate" style="display:none;">
   
    <!--/span-->

    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon"></i> Configure Association</h2>
            </div>
            <div class="box-content">
                <div class="box-content">
                    <div class="bs-example">
   			
		<form:form method="post" action="/DevTracker/confirmMap" modelAttribute="configureAssociation" class="form-horizontal">
                
        <c:if test="${role_id == 1}">
        <div class="form-group">
        
        
     <div id="adminOwnerMapping" style="display:none;">  
        <div class="dual-list list-left col-xs-6">
        <label for="selectRole" class="control-label col-xs-8"> Vehicles</label>
            <div class="well">
                <div class="row">
                    
                    <div class="col-md-12">
                        <div class="input-group">
                            <input type="text" name="SearchDualList" class="form-control" placeholder="search" />
                            <span class="input-group-addon glyphicon glyphicon-search"></span>
                        </div>
                    </div>
                </div>
                <ul class="list-group" style="height: 250px; overflow: auto" data-toggle="items" id="adminVehicles">
                    <c:forEach var="student" items="${map.configureRoles}">
									<li class="list-group-item">${student}</li>
					</c:forEach>
                    
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Morbi leo risus</li>
                    <li class="list-group-item">Porta ac consectetur ac</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                    
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Morbi leo risus</li>
                    <li class="list-group-item">Porta ac consectetur ac</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                </ul>
            </div>
        </div>   
                
        <div class="dual-list list-right col-xs-6">
        <label for="selectRole" class="control-label col-xs-8"> Owner</label>
            <div class="well">
                <div class="row">
                    
                    <div class="col-md-12">
                        <div class="input-group">
                            <input type="text" name="SearchDualList" class="form-control" placeholder="search" />
                            <span class="input-group-addon glyphicon glyphicon-search"></span>
                        </div>
                    </div>
                </div>
                <ul class="list-group" style="height: 250px; overflow: auto" id="ownerVehicles">
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Morbi leo risus</li>
                    <li class="list-group-item">Porta ac consectetur ac</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                </ul>
            </div>
        </div>
     </div>  
     
     <c:if test="${role_id == 1 || role_id == 2}">
     <div id="ownerBrokerMapping" style="display:none;">             
            <div class="dual-list list-right col-xs-4">
            <label for="selectRole" class="control-label col-xs-8"> Owner</label> 
            <div class="well text-right">
                <div class="row">
                    <div class="col-md-12">
                        <div class="input-group">                            
                            <input type="text" name="SearchDualList" class="form-control" placeholder="search" />
                            <span class="input-group-addon glyphicon glyphicon-search"></span>
                        </div>
                    </div>                  
                </div>
                <ul class="list-group" style="height: 250px; overflow: auto">
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Morbi leo risus</li>
                    <li class="list-group-item">Porta ac consectetur ac</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                </ul>
            </div>
        </div> 
        
        <div class="dual-list list-left col-xs-4">
        <label for="selectRole" class="control-label col-xs-8"> Vehicles</label>
            <div class="well">
                <div class="row">
                    
                    <div class="col-md-12">
                        <div class="input-group">
                            <input type="text" name="SearchDualList" class="form-control" placeholder="search" />
                            <span class="input-group-addon glyphicon glyphicon-search"></span>
                        </div>
                    </div>
                </div>
                <ul class="list-group" style="height: 250px; overflow: auto" data-toggle="items">
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Morbi leo risus</li>
                    <li class="list-group-item">Porta ac consectetur ac</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                    
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Morbi leo risus</li>
                    <li class="list-group-item">Porta ac consectetur ac</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                </ul>
            </div>
        </div>   
                
        <div class="dual-list list-right col-xs-4">
        <label for="selectRole" class="control-label col-xs-8"> Broker</label>
            <div class="well">
                <div class="row">
                    
                    <div class="col-md-12">
                        <div class="input-group">
                            <input type="text" name="SearchDualList" class="form-control" placeholder="search" />
                            <span class="input-group-addon glyphicon glyphicon-search"></span>
                        </div>
                    </div>
                </div>
                <ul class="list-group" style="height: 250px; overflow: auto">
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Morbi leo risus</li>
                    <li class="list-group-item">Porta ac consectetur ac</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                </ul>
            </div>
        </div>
        
       </div> 
        </c:if>
        </div>
        </c:if>
         <form:hidden path="parent_id" value="${handler_id}"/>
        <form:hidden path="role_id" value="${role_id}"/>                                 
        
        <div class="form-group"> 
                    <p class="center col-md-5">
                   	<input type="submit" id="submitForm" name="Associate" value="Associate Mapping" title="Associate Mapping." data-toggle="tooltip" data-placement="left" style="margin-left:5%"  class="btn btn-primary" />
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


<script>

        $(function () {

            $('body').on('click', '.list-group .list-group-item', function () {
               // $(this).toggleClass('active');
            });
           
           
            $('[name="SearchDualList"]').keyup(function (e) {
                var code = e.keyCode || e.which;
                if (code == '9') return;
                if (code == '27') $(this).val(null);
                var $rows = $(this).closest('.dual-list').find('.list-group li');
                var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
                $rows.show().filter(function () {
                    var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
                    return !~text.indexOf(val);
                }).hide();
            });

        });
        
        
        $("#selectRole").change(function () {
            var selValue = this.value;

            if(selValue == "Admin"){
            	$('#ownerBrokerMapping').hide();
            	$('#configureAssociate').show();
            	$('#adminOwnerMapping').show();
            	
            }else if(selValue == "Owner"){
            	$('#adminOwnerMapping').hide();
            	$('#configureAssociate').show();
            	$('#ownerBrokerMapping').show();
            }else{
            	$('#configureAssociate').hide();
            	$('#adminOwnerMapping').hide();
            	$('#ownerBrokerMapping').hide();
            }
        });
        
        
        $('#submitForm').click(function(){        	
        	var owner='';
        	var vehicles = '';
        	
        	if($("#selectRole").val() == "Admin"){
        	 $("#adminVehicles > li").each(function(i) {
        			    if($(this).attr("class") == "list-group-item active"){
        			    	 var value = $(this).text();  
        			    	    if ( value != '' ) {
        			    	        if(i === length-1) {  //The last one
        			    	        	vehicles += ' ' + value;
        			    	        } else {
        			    	        	vehicles += ' ' + value + ',';
        			    	        } 
        			    	    }   
        		        }        		   
        	    });
        	 $("#ownerVehicles > li").each(function() {
        		 if($(this).attr("class") == "list-group-item active"){
 		        	owner = $(this).text();
 		        }
    	    });
        	 
        	 if(owner == '' || vehicles == ''){
        		 alert('SOmething empty');
        		 return false;
        	 }else{
        		
                 var search = {
                	      "vehicle" : vehicles,
                	      "owner" : owner
                	   }

            	var dataString = JSON.stringify(search);
                 
            	$.ajax({
    	        type: "POST",
    	        url: "associateVehicleAndOwner",
    	        data: dataString,
                dataType: "json",
                contentType:'application/json',
                //if received a response from the server
                success: function( data, textStatus, jqXHR) {
                    //our country code was correct so we have some information to display
                     if(data.success){
                    	 document.location.href='/DevTracker/configureAssociation';	                
                     } else{
                    		                	 
                     }
                }    		   
                }); 
        		 
        	 }
        	} 
        	
        	 
        	 return false;
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
