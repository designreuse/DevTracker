<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places">
 <script type="text/javascript">
google.maps.event.addDomListener(window, 'load', initialize);
function initialize() {
var autocomplete = new google.maps.places.Autocomplete(document.getElementById('pac-input'));
alert();
google.maps.event.addListener(autocomplete, 'place_changed', function () {
// Get the place details from the autocomplete object.
var place = autocomplete.getPlace();
var location = "<b>Address</b>: " + place.formatted_address + "<br/>";
location += "<b>Latitude</b>: " + place.geometry.location.A + "<br/>";
location += "<b>Longitude</b>: " + place.geometry.location.F;
alert(location);
});
}
</script>



<script src="https://maps.googleapis.com/maps/api/js??sensor=false"></script>

 <style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
      .controls {
        margin-top: 16px;
        border: 1px solid transparent;
        border-radius: 2px 0 0 2px;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        height: 32px;
        outline: none;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
      }

      #pac-input {
        background-color: #fff;
        padding: 0 11px 0 13px;
        width: 400px;
        font-family: Roboto;
        font-size: 15px;
        font-weight: 300;
        text-overflow: ellipsis;
      }

      #pac-input:focus {
        border-color: #4d90fe;
        margin-left: -1px;
        padding-left: 14px;  /* Regular padding-left + 1. */
        width: 401px;
      }

      .pac-container {
        font-family: Roboto;
      }

      #type-selector {
        color: #fff;
        background-color: #4d90fe;
        padding: 5px 11px 0px 11px;
      }

      #type-selector label {
        font-family: Roboto;
        font-size: 13px;
        font-weight: 300;
      }
}

    </style>
    <title>Places search box</title>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
    <script>
// This example adds a search box to a map, using the Google Place Autocomplete
// feature. People can enter geographical searches. The search box will return a
// pick list containing a mix of places and predicted search terms.

function initialize() {

  var markers = [];
  var map = new google.maps.Map(document.getElementById('map-canvas'), {
    mapTypeId: google.maps.MapTypeId.ROADMAP
  });

  var defaultBounds = new google.maps.LatLngBounds(
      new google.maps.LatLng(17.440728216202633, 77.39813439062493),
      new google.maps.LatLng(8.326660811104384, 77.52997032812493));
  map.fitBounds(defaultBounds);

  // Create the search box and link it to the UI element.
  var input = document.getElementById('pac-input');
  
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

  var searchBox = new google.maps.places.SearchBox(input);
  
  // Listen for the event fired when the user selects an item from the
  // pick list. Retrieve the matching places for that item.
  google.maps.event.addListener(searchBox, 'places_changed', function() {
    var place = searchBox.getPlaces()[0];
    var autocomplete = new google.maps.places.Autocomplete(document.getElementById('pac-input'));
	 google.maps.event.addListener(autocomplete, 'place_changed', function () {
	 // Get the place details from the autocomplete object.
	 var place = autocomplete.getPlace();

	 });
     if (!place.geometry) return;

    // If the place has a geometry, then present it on a map.
    if (place.geometry.viewport) {
      map.fitBounds(place.geometry.viewport);
    } else {
      map.setCenter(place.geometry.location);
      map.setZoom(17);
    } 
    
    
    
    var image = 'img/marker.png';
	var myLatlng = new google.maps.LatLng(place.geometry.location.lat(),place.geometry.location.lng());
	
	
    var latlngbounds = new google.maps.LatLngBounds();
    
    
    
      
	 var marker = new google.maps.Marker({
	      position: myLatlng,
	      map:map,
	      icon:image,
	      draggable: true,
          animation: google.maps.Animation.DROP
	      });
	 
	 
	  
		  
	 (function (marker, data) {
               
              google.maps.event.addListener(marker, "dragend", function (e) {                  
                
              });
          })(marker, data);
	      latlngbounds.extend(marker.position);
	      map.setCenter(marker.getPosition());
	 
	      
	      
	      var infoWindow = new google.maps.InfoWindow();
	      var geocoder = geocoder = new google.maps.Geocoder();
	    
	      google.maps.event.addListener(marker, 'click', function() {
	    	  var lat,lng,address; 
	    	  geocoder.geocode({ 'latLng': marker.getPosition() }, function (results, status) {
                  if (status == google.maps.GeocoderStatus.OK) {                	  
                      lat = marker.getPosition().lat();
                      lng = marker.getPosition().lng();
                      address = results[0].formatted_address;
                      var infoWindowContent = "<h3 class='popover-title' style='background-color: #FFFFFF; padding: 0px 0px; ; color: #555555'><b></b></h3><div style='overflow: auto;' class='popover-content'><h6 style='color: #555555'><b>"+
     	    		  address+"<br><br>Latitude : "+lat+"<br><br>Longitude :"+lng+" <b><h6><input type='button' name='Save Location' value='Save Location' onclick='save_marker("+lat+","+ lng+");'></div>";					   
     	    		  infoWindow.setContent(infoWindowContent);
     	              infoWindow.open(map,marker);
                  }
              });
		});
  });

  // Bias the SearchBox results towards places that are within the bounds of the
  // current map's viewport.
  
}

google.maps.event.addDomListener(window, 'load', initialize);

function save_marker(lat, lng){
    //Save new marker using jQuery Ajax
    var godownName = $("#godownName").val();
    //alert(godownName);
    if(godownName != ''){
    dataString = "latlng=" +lat+"#"+lng+"#"+godownName+"#"+${handler_id};
   // alert(dataString);
       
    $.ajax({
      type: "POST",
      url: "saveDeliveryAddress",
      data: dataString,
      dataType: "json",
      success:function(data){
    	  if(data.success){
    		  alert("Location Saved");
              document.location.href='/DevTracker/deliveryAddress?id='+${handler_id};
    	  }else{
    		  alert("Godown Name already exists!");
    	  }
        },
        error:function (xhr, ajaxOptions, thrownError){
            alert("Try Again"); //throw any errors
        }
    }); 
}else{
	alert("Godown Name is Empty");
}
}

    </script>
    <style>
      #target {
        width: 345px;
      }
    </style> 
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
    <!-- <ul class="breadcrumb">
        <li>
            <a href="home">Home</a>
        </li>
        <li>
            <a href="vehicleDetails">Delivery Address Details</a>
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
                <h2><i class="glyphicon"></i> Delivery Address Details</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon"></i></a>
                </div>
            </div>
           
                <div class="box-content">
                    <div class="bs-example">
    
    
        
         <div class="form-group">
            <label for="godownName" class="control-label col-xs-2"> Godown Name </label>
            <div class="col-xs-4">
                <input  type="godownName" class="form-control" id="godownName" placeholder="Enter Godown Name"/>
            </div>
            <br>
        </div>
        
        
      
        </br>
      <table class="table table-striped table-bordered responsive">
                        <thead>
                        <tr>
                            <th>Address Name</th>
                            <th>Address</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>

						<c:forEach var="trackCustomerDetails" items="${trackCustomerDetails}">
                        <tr>
                            <td>${trackCustomerDetails.godown_name}</td>
                            <td class="center">${trackCustomerDetails.address}</td>
                            <td class="center">
                                <span class="label-success label label-default">${trackCustomerDetails.status}</span>
                            </td>
                            <td class="center">                                
                                <a class="btn btn-danger" href="#">
                                    <i class="glyphicon glyphicon-trash icon-white"></i>
                                    Delete
                                </a>
                            </td>
                        </tr>
                        </c:forEach>                        
                        </tbody>
                    </table>
      
    </div>
                </div>
            
            
            
            
            
        </div>
    </div>
    
    <!--/span-->
    
    
    
   
    
</div><!--/row-->


<div class="row">
   
    <!--/span-->

    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-user"></i> Locate Address</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                </div>
            </div>
             
    <input id="pac-input" class="controls" type="text" placeholder="Search Box">
    <div id="map-canvas"></div>
 
        </div>
    </div>
</div>


  
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
