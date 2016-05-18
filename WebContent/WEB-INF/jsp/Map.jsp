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
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script type="text/javascript">
       var markers = ${map.coordinates};
        window.onload = function () { 
           if(markers.length != 0){
        	 $("#map_section").show();	
        	   var mapOptions = {
                       center: new google.maps.LatLng(markers[0].latitude, markers[0].longitude),
                       zoom: 8,
                       mapTypeId: google.maps.MapTypeId.ROADMAP
                   };
                   var map = new google.maps.Map(document.getElementById("dvMap"), mapOptions);
                   var infoWindow = new google.maps.InfoWindow(); 
                   var lat_lng = new Array();
                   var latlngbounds = new google.maps.LatLngBounds();
                  
                 
                   
                   
                   for (i = 0; i < markers.length; i++) {
                	
                       var data = markers[i];
                       
                       var myLatlng = new google.maps.LatLng(data.latitude, data.longitude);
                       lat_lng.push(myLatlng);
                       var image ='';
                       var marker;
                       
                       
                       if(i == 0 || i == (markers.length -1)){
                    	   if(i == 0 ){
                    		   image = 'img/start.png';
                    	   }else{
                    		   image = 'img/destination.png';
                    	   }
                           
                           marker = new google.maps.Marker({
                               position: myLatlng,
                               map: map,
                               icon:image,
                               title: data.address
                           }); 
                       }/* else if(){
                    	   image = 'img/destination.png';
                    	   marker = new google.maps.Marker({
                               position: myLatlng,
                               map: map,
                               icon:image,
                               title: data.address
                           });
                       }  else{
                    	  // image = 'img/dot.png';
                    	  //alert(markers.length);
                    	  if(markers.length > 71){
                    		  if(i%10 == 0){
                    			  marker = new google.maps.Marker({
                           		   icon:{
                           		       // use whatever icon you want for the "dots"
                           		       url:"http://localhost:8080/DevTracker/img/dot.png",
                           		       size: new google.maps.Size(7,7),
                           		       anchor: new google.maps.Point(4,4)
                           		     },
                                      position: myLatlng,
                                      map: map,
                                      title: data.address
                                  });
                    		  }
                    	  } else if(markers.length > 50 && markers.length < 70){
                    		  if(i%5 == 0){
                    			  marker = new google.maps.Marker({
                           		   icon:{
                           		       // use whatever icon you want for the "dots"
                           		       url:"http://localhost:8080/DevTracker/img/dot.png",
                           		       size: new google.maps.Size(7,7),
                           		       anchor: new google.maps.Point(4,4)
                           		     },
                                      position: myLatlng,
                                      map: map,
                                      title: data.address
                                  });
                    		  }
                    	  }else if(markers.length > 30 && markers.length < 49){
                    		  if(i%3 == 0){
                    			  marker = new google.maps.Marker({
                           		   icon:{
                           		       // use whatever icon you want for the "dots"
                           		       url:"http://localhost:8080/DevTracker/img/dot.png",
                           		       size: new google.maps.Size(7,7),
                           		       anchor: new google.maps.Point(4,4)
                           		     },
                                      position: myLatlng,
                                      map: map,
                                      title: data.address
                                  });
                    		  }
                    	  } else if(i < (markers.length - 5))  {
                    		  if(i%5 == 0){
                    		  marker = new google.maps.Marker({
                          		   icon:{
                          		       // use whatever icon you want for the "dots"
                          		       url:"http://localhost:8080/DevTracker/img/dot.png",
                          		       size: new google.maps.Size(7,7),
                          		       anchor: new google.maps.Point(4,4)
                          		     },
                                     position: myLatlng,
                                     map: map,
                                     title: data.address
                                 });
                    	  }
                    	  } */else{
                    		  
                    		 
                    		  marker = new google.maps.Marker({
                         		   icon:  {
                         		       // use whatever icon you want for the "dots"
                         		       url:"img/dot.png",
                         		       size: new google.maps.Size(7,7),
                         		       anchor: new google.maps.Point(4,4)
                         		     },
                                    position: myLatlng,
                                    map: map,
                                    title: data.address
                                }); 
                    	
                    	
                       }
                       /* if(markers.length > 30){
                        if(i%5 == 0){
                    	 
                         marker = new google.maps.Marker({
                           position: myLatlng,
                           map: map,
                           icon:image,
                           title: data.address
                       });  
                       } 
                       } else{
                    	   marker = new google.maps.Marker({
                               position: myLatlng,
                               map: map,
                               icon:image,
                               title: data.address
                           });  
                       }  */                    
                       latlngbounds.extend(myLatlng); 
                        (function (marker, data) {
                           google.maps.event.addListener(marker, "click", function (e) {
                        	   var infoWindowContent = "<h3 class='popover-title' style='background-color: #FFFFFF; padding: 0px 0px; ; color: #555555'><b>"
									+ data.vehicle_number
									+ "</b></h3><div style='overflow: auto;' class='popover-content'><h6 style='color: #555555'><b>"
									+ "Date:"+data.datet+"<br><br>"
									+ "Time:"+data.time+"<br><br>"
									+ "Speed:"+data.speed+"<br><br>"											
									+ "Address :"+ data.address + " <b><h6></div>";
							infoWindow.setContent(infoWindowContent);
                               infoWindow.open(map, marker);
                           });
                       })(marker, data); 
                   }
                   
			map.setCenter(latlngbounds.getCenter());
			map.fitBounds(latlngbounds);
			

			//***********ROUTING****************//

            //Intialize the Path Array
             var path = new google.maps.MVCArray();

            //Intialize the Direction Service
            var service = new google.maps.DirectionsService();

            //Set the Path Stroke Color
            var poly = new google.maps.Polyline({strokeWeight:6, map: map, strokeColor: '#4986E7' });

            //Loop and Draw Path Route between the Points on MAP
            for (var i = 0; i < lat_lng.length; i++) {
            	
                //if ((i + 1) < lat_lng.length) {
                	//alert(lat_lng[i]);
                    var src = lat_lng[i];
                    //var des = lat_lng[i + 1];
                    path.push(src);
                    poly.setPath(path);
                   
              //  }
            } 
			
			
			
			
			
		}

	}


</script>
</head>

<body >
    <!-- topbar starts -->
    <div class="navbar-default" >

        <div class="navbar-inner">           
            <a class="navbar-brand" href="index.jsp" style="width:30%"> 
                <span>C M T</span></a>
                
                <div class="btn-group pull-right">
             <a class="btn btn-primary" href="/DevTracker/startup">Home</a>                
            </div>
        </div>
    </div>
    <!-- topbar ends -->
       <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>
   
     <div id="dvMap" style="width: auto; height: 600px"></div>
<div>
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
