function initialize() {
	 alert("----->");
	  
	    
	    }
	 

/*function getUserInputValue1(){
	  
	 
	  $("#mapPanel").show();
	  initialize();
}*/

function getUserInputValue(e) {
	
	//if (e.keyCode == 13) {
		
		//$("#intro").hide();
		
	
	var mapCanvas = document.getElementById('map_canvas');
    var mapOptions = {
      center: new google.maps.LatLng(12.07111, 78),
      zoom: 12,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    var map = new google.maps.Map(mapCanvas, mapOptions);
    
   $("#mapPanel").show();
	
	
	var address = $("#userInputValue").val();
	 alert("----> "+address);
	       var geocoder = new google.maps.Geocoder();
		  
		   // var address = document.getElementById("my-address").value;
		    geocoder.geocode( { 'address': address}, function(results, status) {
		      if (status == google.maps.GeocoderStatus.OK) {
		    	 
		    	  var lat = results[0].geometry.location.lat();
		    	  var lon = results[0].geometry.location.lng();
		    	  
		    	  alert(lat+" -----> "+lon);
				     var image = 'images/marker.png';
					var myLatlng = new google.maps.LatLng(lat,lon);
					

					 var marker = new google.maps.Marker({
					      position: myLatlng,
					      map:map,
					    	icon:image  
					      });
					 map.setCenter(marker.getPosition());
					 
					 
					 var infowindow = new google.maps.InfoWindow();
					 google.maps.event.addListener(marker, 'click', function() {
						   infowindow.open(map,marker);
						});
					 var infoWindowContent = "<h3 class='popover-title' style='background-color: #FFFFFF; padding: 0px 0px; ; color: #555555'><b>KA - 6225 (Drove By : Shankar's Logisitics )</b></h3><div style='overflow: auto;' class='popover-content'><h6 style='color: #555555'><b>Travelling from CHENNAI to COIMBATORE<br><br>Current Location of Truck : "+address.toUpperCase()+"<b><h6></div>";					   
					  infowindow.setContent(infoWindowContent);
					  infowindow.open(map,marker); 
		      } 

		      else {
		        alert("Geocode was not successful for the following reason: " + status);
		      } 
   
    }); 
	//}
}



