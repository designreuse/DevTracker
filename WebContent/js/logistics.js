function initialize() {
	 alert("----->");
	  
	    
	    }
	 

function getUserInputValue1(){
	  
	 
	  $("#mapPanel").show();
	  initialize();
}

function getUserInputValue(e) {
	
	
		
		$("#intro").hide();
		
	
	var mapCanvas = document.getElementById('map_canvas');
    var mapOptions = {
      center: new google.maps.LatLng(12.07111, 78),
      zoom: 12,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    var map = new google.maps.Map(mapCanvas, mapOptions);
    
    $("#mapPanel").show();
	
	
	 var vehicleNumber = $("#userInputValue").val();
	 var latitude;
	 var longitude;
	 var speed;
	 var date;
	 var address;
	 dataString = "vehicleNumber=" + vehicleNumber;
	 $.ajax({
         type: "POST",
         url: "fetch",
         data: dataString,
         dataType: "json",
         
         //if received a response from the server
         success: function( data, textStatus, jqXHR) {
             //our country code was correct so we have some information to display
              if(data.success){
             	
             	 latitude = data.coordinateInfo.latitude;
             	 longitude = data.coordinateInfo.longitude;
             	 speed = data.coordinateInfo.speed;
             	 date = data.coordinateInfo.date;
             	 address = data. coordinateInfo.address;
             	  
				    var image = 'img/marker.png';
					var myLatlng = new google.maps.LatLng(latitude,longitude);
					
					
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
					 
					
					 var infoWindowContent = "<h3 class='popover-title' style='background-color: #FFFFFF; padding: 0px 0px; ; color: #555555'><b>"+vehicleNumber +"</b></h3><div style='overflow: auto;' class='popover-content'><h6 style='color: #555555'><b>Travelling from CHENNAI to COIMBATORE<br><br>Current Location of Truck :"+address+" <b><h6></div>";					   
					  infowindow.setContent(infoWindowContent);
					  infowindow.open(map,marker);                 
              } 
              //display error message
              else {
                  $("#ajaxResponse").html("<div><b>Invalid Vehicle Number!</b></div>");
              }
         }

     });  
}

function getUserAddress() {
	
	
	 var autocomplete = new google.maps.places.Autocomplete(document.getElementById('pac-input'));
	 google.maps.event.addListener(autocomplete, 'place_changed', function () {
	 // Get the place details from the autocomplete object.
	 var place = autocomplete.getPlace();
	 var location = "<b>Address</b>: " + place.formatted_address + "<br/>";
	 location += "<b>Latitude</b>: " + place.geometry.location.A + "<br/>";
	 location += "<b>Longitude</b>: " + place.geometry.location.F;
	 });
 }

function alphanumeric(myfield, e, dec) {
	var key; 
	var keychar; 
	if (window.event) 
	key = window.event.keyCode; 
	else if (e) 
	key = e.which; 
	else 
	return true; 
	keychar = String.fromCharCode(key); 
	// control keys 
	if ((key==null) || (key==0) || (key==8) || (key==9) || (key==13) || (key==27) ) 
	return true; 
	// numbers 
	else if ((("0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz").indexOf(keychar) > -1)) 
	return true; 
	// decimal point jump 
	else if (dec && (keychar == ".")) { 
	myfield.form.elements[dec].focus(); 
	return false; 
	} 
	else 
	return false; 
	}

function numerical(myfield, e, dec) {
	var key; 
	var keychar; 
	if (window.event) 
	key = window.event.keyCode; 
	else if (e) 
	key = e.which; 
	else 
	return true; 
	keychar = String.fromCharCode(key); 
	// control keys 
	if ((key==null) || (key==0) || (key==8) || (key==9) || (key==13) || (key==27) ) 
	return true; 
	// numbers 
	else if ((("0123456789").indexOf(keychar) > -1)) 
	return true; 
	// decimal point jump 
	else if (dec && (keychar == ".")) { 
	myfield.form.elements[dec].focus(); 
	return false; 
	} 
	else 
	return false; 
	}