var queryBookingCntrlr = angular.module('QueryBookingControllers', []);

queryBookingCntrlr.controller("QueryBookingController", ['$scope', 'bookSlotFactory', 'currentBookingSlotFactory',
                function($scope, bookSlotFactory, currentBookingSlotFactory) {
							
					$scope.searchBooking = function() {
						
						$scope.emptyQueryResult = false;
						
						if(!$scope.queryBookingVehicleId) {
							$scope.emptySearchVehicleId = true;
							return;
						}
						
						bookSlotFactory.search({"vehicleId":$scope.queryBookingVehicleId}).$promise
						.then(function(result) {
							console.log("inside result " + result);
							$scope.emptySearchVehicleId = false;
							$scope.queriedBookingInfo = result;
							if(result.length == 0) {
								$scope.emptyQueryResult = true;
							}
						}, function(error) {
						});
					};
					
					$scope.releaseBooking = function(bookingInfo) {
						bookSlotFactory.releaseBooking({'id':bookingInfo.bookingId}).$promise
						.then ( function (result ) {
							console.log(bookingInfo);
							$scope.queriedBookingInfo = $scope.queriedBookingInfo.filter(function ( element) {
								return element.bookingId != bookingInfo.bookingId; 
							});
						} , function ( error ){
							console.log("release booking " + error);
						});
					};
					
					$scope.displayAllCurrentBookings = function() {
						
						$scope.emptyQueryResult = false;
						currentBookingSlotFactory.getAll().$promise
						.then(function(result) {
							$scope.queriedBookingInfo = result;
							if(result.length == 0) {
								$scope.emptyQueryResult = true;
							}
						}, function(error) {
						});
					}
		
	
}]);