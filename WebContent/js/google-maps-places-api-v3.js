  var searchBox = new google.maps.places.SearchBox(document.getElementById('searchinput'));
  //alert("====> "+searchBox.getPlaces()[0]);
  
  google.maps.event.addListener(searchBox, 'places_changed', function() {
    var place = searchBox.getPlaces()[0];
    alert(place);
    
    if (!place.geometry) return;

    if (place.geometry.viewport) {
      map.fitBounds(place.geometry.viewport);
    } else {
      map.setCenter(place.geometry.location);
      map.setZoom(16);
    }
  });
  
  google.maps.event.addListener(map, 'bounds_changed', function() {
	    var bounds = map.getBounds();
	    searchBox.setBounds(bounds);
	    alert("---> "+bounds);
	  });