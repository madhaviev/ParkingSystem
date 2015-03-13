var services = angular.module('parkingServices', ['ngResource']);


//Factory to get the list of all Levels and create a new Level
services.factory("listLevelFactory", function($resource) {

	return $resource("http://localhost:8090/parking/garage/level/", {},
    {	
		create  : {method: 'POST'},
		getList : {method: 'GET', isArray:true },
	});
});

//Factory to get the list of all Free Levels
services.factory("freeLevelFactory", function($resource) {
	
	return $resource("http://localhost:8090/parking/garage/freeLevels", {},
	{		
		getFreeLevels 	: {method: 'GET', params:{vehicleType: 'Car'}, isArray: true}
	});
});


//Factory to list all slots of given Level number
services.factory("levelSlotFactory", function($resource) {
	
	return $resource("http://localhost:8090/parking/garage/level/slots/:levelNumber", {}, 
	{
		getList : {method: 'GET', isArray: true}
		
	});
});

services.factory("slotFactory" , function($resource) {
	
	return $resource("http://localhost:8090/parking/garage/slot/:slotId", {},
	{
		deleteSlot : {method: 'DELETE', params:{slotId: '@slotId'}}
	});
});

services.factory("bookSlotFactory", function($resource) {
	
	return $resource("http://localhost:8090/parking/bookingSlot/:id", {},
	{
		bookSlot         : {method: 'POST'},
	    search			 : {method: 'GET', params:{vehicleId:'xxx'}, isArray: true},
		releaseBooking   : {method: 'DELETE', params:{id: '@id'}}
	});
});

services.factory("currentBookingSlotFactory", function($resource) {
	
	return $resource("http://localhost:8090/parking/bookingSlot/all", {},
	{
		getAll 			 : {method: 'GET', isArray: true}
	});
});