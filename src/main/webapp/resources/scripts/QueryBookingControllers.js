var queryBookingCntrlr = angular.module('QueryBookingControllers', []);

queryBookingCntrlr.controller("QueryBookingController", ['$scope', 'bookSlotFactory', 'currentBookingSlotFactory', 'listLevelFactory',
                function($scope, bookSlotFactory, currentBookingSlotFactory, listLevelFactory) {
							
					$scope.currentPage = 0;
					$scope.pageSize = 5;
					$scope.numberOfPages = function() {
						return Math.ceil($scope.displayBookingInfo.length/$scope.pageSize);
					};
					$scope.orderByField = 'vehicleId';
					$scope.reverseSort = false;
					$scope.displayBookingInfo = {};
					$scope.filterCriteria = {"levelNumberFilter" : [],
											 "vehicleTypeFilter" : []
											};
					$scope.startRecord = 0;
					$scope.endRecord = 0;
					
					//Function to update the page details count , startpage and endpage
					$scope.updatePageCountDetails = function(clickPage) {
						if(clickPage == "Previous") {
							$scope.currentPage = $scope.currentPage - 1;
						}
						else if(clickPage == "Next") {
							$scope.currentPage = $scope.currentPage + 1;
						}
						
						$scope.startRecord = $scope.currentPage*$scope.pageSize + 1;
						
						var endRecordVal = $scope.currentPage*$scope.pageSize + $scope.pageSize;
						$scope.endRecord = ($scope.displayBookingInfo.length < endRecordVal)?($scope.displayBookingInfo.length):(endRecordVal); 
					};
					
										
					//Function to release booking of the vehicle
					$scope.releaseBooking = function(bookingInfo) {
						bookSlotFactory.releaseBooking({'id':bookingInfo.bookingId}).$promise
						.then ( function (result ) {
							console.log(bookingInfo);
							$scope.displayBookingInfo = $scope.currentBookingInfo.filter(function ( element) {
								return element.bookingId != bookingInfo.bookingId; 
							});
						} , function ( error ){
							console.log("release booking " + error);
						});
					};
					
									
					//Function to display all the current booking details
					$scope.displayAllCurrentBookings = function() {
						$scope.queryBookingVehicleId = "";
						$scope.emptyQueryResult = false;
						currentBookingSlotFactory.getAll().$promise
						.then(function(result) {
							$scope.currentBookingInfo = result;
							$scope.displayBookingInfo = $scope.currentBookingInfo;
							if(result.length == 0) {
								$scope.emptyQueryResult = true;
							}
						}, function(error) {
						});
						resetFilters();
					};
					
					//Utility function to get all total levels
					getTotalLevels = function() {
						listLevelFactory.getList().$promise
		   				.then(function(result) {
		   					$scope.totalLevels = _.sortBy(_.pluck(result,"levelNumber"));
		   				}, 
		   				function(error) {
		   					console.log("Failed in getTotalLevels");
		   				});
					};
					
					//Utility function to reset all the filters 
					resetFilters = function() {
						_.each($scope.filterCriteria, function(key) {
							key = [];
						});
						_.each(angular.element(document.querySelectorAll(".filterOption")), function(el) {
							el.checked = false;
						});
					};
					
					//Initializing the view
					$scope.displayAllCurrentBookings();
					getTotalLevels();
					$scope.$watch(function(scope) { return scope.displayBookingInfo.length;},
						function(newValue, oldValue) {
							$scope.updatePageCountDetails();
					});
					
					//Final render of bookingInfo
					renderDisplayBookingInfo = function(filteredBookingInfo) {
						$scope.displayBookingInfo = filteredBookingInfo;
					};
					
					//Extract Filtered data to be displayed
					computeDisplayBookingInfo = function() {
						
						var filteredBookingInfo = 
							
							_.chain($scope.currentBookingInfo)
							 .filter(function(bookingInfo) {
										if(_.isEmpty($scope.filterCriteria["vehicleTypeFilter"]) || _.contains($scope.filterCriteria["vehicleTypeFilter"], bookingInfo.slotObj.vehicleType)) {
											return true;
										}
									})
							 //.tap(alert)
							 .filter(function(bookingInfo) {
								 		if(_.isEmpty($scope.filterCriteria["levelNumberFilter"]) || _.contains($scope.filterCriteria["levelNumberFilter"], bookingInfo.slotObj.levelNumber)) {
								 			return true;
								 		}
							 })
							 .value();
						
					   renderDisplayBookingInfo(filteredBookingInfo);
					
					};
						
					//Register the filters on user clicking the appr filter
					$scope.registerFilter = function(filterName, filterType) {
						
						var index = _.indexOf($scope.filterCriteria[filterType], filterName);
						
						if(index == -1) {
							$scope.filterCriteria[filterType].push(filterName);
						} else {
							$scope.filterCriteria[filterType].splice(index,1);
						}
					
						computeDisplayBookingInfo();
					};
					
					
}]);