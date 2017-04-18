var mainAppModule = angular.module('mainApp', ['ngRoute']).config(['$routeProvider', function ($routeProvider) {

	appRoutes.init($routeProvider);
}]);

angular.module('modules', []);

console.info('adserver started............');

mainAppModule.controller("adServerCtl", ["$route","$scope", "$window", "$location", "$timeout",   function ($route,$scope, $window,$location, $timeout) {

	$scope.user = {
			partner_id: "",
			duration: "",
			ad_content: ""
	};

	$scope.goBack = function () {

		$window.history.back();
	}

	$scope.saveCompaigns = function () {

		var _args = $scope.user;

		api.execute('createCamppaigns', _args, function (result) {

			if (result.typ == "error") {

				alert(result);
			}
			else {

				alert(result);
				$window.location.reload();
			}
		}, function (err) {
			var data = {};
			data.typ = "error";
			data.txt = err.message || "Application failed to process the request, please contact support";
			alert(data.txt);

		});
	}

	$scope.getCompaigns = function () {

		var _args = $scope.user.partner_id;

		api.execute('getCompaigns', _args, function (result) {

			if (result.typ == "error") {

				alert(result);
			}
			else {

			alert(JSON.stringify(result));
				$window.location.reload();
			}
		}, function (err) {
			var data = {};
			data.typ = "error";
			data.txt = err.message || "Application failed to process the request, please contact support";
			alert(data.txt);

		});
	}
}]);
