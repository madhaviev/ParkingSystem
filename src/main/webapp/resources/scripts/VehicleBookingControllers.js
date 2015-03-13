var vehicleBookingCntrlr = angular.module('VehicleBookingControllers', []);

vehicleBookingCntrlr.controller("VehicleBookingController", ['$scope', 'freeLevelFactory', 'bookSlotFactory' , '$route', '$filter',
                                                   
              function($scope, freeLevelFactory, bookSlotFactory, $route, $filter) {
					
				
				 $scope.renderSlots  = function() {
					 $scope.freeSlotsListInSelectedLevel = $filter('filter')($scope.freeSlotsList, {levelNumber: $scope.selectedLevel}) ;
					 
					 var rowSlots = [];
					 
					 for(var i=0; i< $scope.freeSlotsListInSelectedLevel.length ; i++) {
						 if(i%3 == 0) rowSlots.push([]);
						 rowSlots[rowSlots.length-1].push($scope.freeSlotsListInSelectedLevel[i]);
					 }
					$scope.rowSlotsList = rowSlots; 
				 };
				 
				 $scope.getFreeSlots = function(vehicleType) {
					 freeLevelFactory.getFreeLevels({"vehicleType":vehicleType}).$promise
					 .then(function(result) {
						 console.log("inside result" + result);
						 $scope.freeSlotsList = result;
						 if($scope.freeSlotsList.length > 0 ) {
							 $scope.selectedLevel = $scope.freeSlotsList[0].levelNumber;
						 }
					 	 $scope.renderSlots();
					 }, function(reason) {
					 });
					 
				 };
				 
				 $scope.getFreeSlots('Car');
				 
				 $scope.bookSlot = function() {
					 
					 $scope.emptyVehicleId = false;
					 $scope.emptySlotId = false;
					 
					 if(!$scope.vehicleId ) {
						 $scope.emptyVehicleId = true;
						 return;
					 }
					 if(!$scope.selectedSlot) {
						 $scope.emptySlotId = true;
						 return
					 }
					 
					 var bookingInfo = {
							 			  "slotObj" : {"slotId" :$scope.selectedSlot},
							 			  "vehicleId" : $scope.vehicleId
							 
					 					};
					 
					 
					 bookSlotFactory.bookSlot(bookingInfo).$promise.then(function(result) {
						 
						 $scope.updatedBookingSlot = result;
						 
						 if(result.bookingId == 0 && result.errorMsg.length != 0) {
							 $scope.bookingAlreadyExists = true;
						 }
						 else {
							 
							 $scope.bookingAlreadyExists = false;
							 alert("Booking created");
							 $route.reload();
						 }
					 }, function(error) {
					 	
					 });
					 
				 };
				 
				
				 
}]);
