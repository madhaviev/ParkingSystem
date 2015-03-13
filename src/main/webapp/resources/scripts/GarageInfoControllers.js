var garageInfoCntrlr = angular.module('GarageInfoControllers', []);

garageInfoCntrlr.controller("EditGarageController", ['$scope', 
                                                  
         function($scope) {
			$scope.levelDetails = [];
			$scope.createdLevel = {};
			$scope.formLevelNumber = '';
			
}]);

garageInfoCntrlr.controller("createLevelController", ['$scope', '$rootScope', 'listLevelFactory', '$filter',
                                                  
         function($scope, $rootScope, listLevelFactory, $filter) {
	
			var clearLevelForm = function() {
				$scope.levelNumber = "";
				$scope.carSlotsCount = "";
				$scope.bikeSlotsCount = "";
			}
			

			$scope.submit = function() {
				
				console.log("inside submit function");
				
				$scope.levelCreatedSuccesfully = false;
				$scope.levelCreationFailed = false;
				
				var confirmCreate = false;
				
				for(var i=0; i< $scope.$parent.levelDetails.length; i++) {
					if($scope.$parent.levelDetails[i].levelNumber == $scope.levelNumber) {
						confirmCreate = window.confirm("Level with LevelNumber "+ $scope.levelNumber + " already exists. Do you wish to expand the slot count?");
						if(!confirmCreate) {
							clearLevelForm();
							return;
						}
						else {
							break;
						}
					}
				}
				
				$scope.$parent.formLevelNumber = $scope.levelNumber;
				var levelDetails = {"levelNumber": $scope.levelNumber, "carSlotsCount": $scope.carSlotsCount, "bikeSlotsCount": $scope.bikeSlotsCount};
				listLevelFactory.create(levelDetails).$promise.
				then(function(result) {
					$scope.levelCreatedSuccesfully = true;
					$rootScope.$broadcast('updateLevelObjects');
					

					
				}, function(reason) {
					$scope.levelCreationFailed = true;
				});
				
				clearLevelForm();
			};
}]);

garageInfoCntrlr.controller("levelSummaryController", ['$scope', 'listLevelFactory', '$location', '$filter',
                                                    
          function($scope, listLevelFactory, $location, $filter) {
			
			$scope.updateLevelDetailsList =	function() {
				 listLevelFactory.getList().$promise
	   				.then(function(result) {
	   					$scope.$parent.levelDetails = result; 
	   					$scope.$parent.createdLevel = $filter('filter')($scope.$parent.levelDetails, {levelNumber : $scope.$parent.formLevelNumber})[0];
	   				}, function(reason) {
	   					
	   				});
			};
			  

			$scope.updateLevelDetailsList();
			
   			$scope.$on('updateLevelObjects', function() {
   				$scope.updateLevelDetailsList();
   			});
   			
 }]);

garageInfoCntrlr.controller("slotSummaryController", ['$scope', 'listLevelFactory', 'levelSlotFactory', '$location', 'slotFactory','$route','$rootScope',
                                                    
          function($scope, listLevelFactory, levelSlotFactory, $location, slotFactory, $route, $rootScope) {
			
			$scope.updateSlots = function() {
				if($scope.lno == null) {
					return;
				}
				$scope.slotDetails = levelSlotFactory.getList({"levelNumber": $scope.lno});
			};
   		 			
			$scope.deleteSlot = function(slotId) {
				var deleteSlotConfirm = confirm("Deleting the slot will loose the booked vehicle history of this slot. Do you wish to proceed?");
				if(deleteSlotConfirm == true) {
					slotFactory.deleteSlot({"slotId":slotId}).$promise
					.then(function(result) {
						$scope.slotDetails = $scope.slotDetails.filter(function(element) {
							return element.slotId != slotId;
						});
						$rootScope.$broadcast('updateLevelObjects');
					}, function(reason) {
						
					});
				}
			};
			
			$scope.updateSlotName = function(data,slotId) {
				
				console.log("updateSlotName");
			};
    		   	
   			
   			
 }]);
