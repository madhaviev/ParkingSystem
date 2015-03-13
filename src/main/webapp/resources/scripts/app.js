'use strict';

var app = angular
			.module("parkingApp", [
			        'ngRoute',
			        'ngResource',
			        'angular.filter',
			        'parkingServices',
			        'GarageInfoControllers',
			        'QueryBookingControllers',
			        'VehicleBookingControllers'
			]);

app.config(function ($routeProvider, $locationProvider) {
	
	//$locationProvider.html5Mode(true);
	
	$routeProvider
		.when("/DefaultMain", {
			templateUrl: "resources/templates/DefaultMain.html"
		})
		.when("/ManageGarageInfo", {
			templateUrl: "resources/templates/ManageGarageInfo.html",
			controller: "EditGarageController"
		})
		.when("/BookVehicleSlot", {
			templateUrl: "resources/templates/BookVehicleSlot.html",
			controller: "VehicleBookingController"
		})
		.when("/QueryBooking", {
			templateUrl: "resources/templates/QueryBooking.html",
			controller: "QueryBookingController"
		})
		.otherwise({
			templateUrl: "resources/templates/DefaultMain.html"
		});
		

});


