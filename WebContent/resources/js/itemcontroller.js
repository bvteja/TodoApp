app.controller('ItemCtrl', [ "$scope", "$http","$stateParams", function($scope, $http,$stateParams) {

	$scope.id =  $stateParams.id;
	$scope.todo = [];
	$scope.title = "";

	$scope.loadItems= function() {
		$http.get("http://localhost:8080/TodoApp/api/todo/loadItems/"+$scope.id)
		.success(function(response) {
			$scope.todo=response;

		});

	}
	
	$scope.removeItem= function(itemId) {
		$http.get("http://localhost:8080/TodoApp/api/todo/removeItem/"+$scope.id+"/"+itemId)
		.success(function(response) {
			$scope.todo=response;

		});

	}
	$scope.editView= function(itemId) {
		$("#content-"+itemId).hide();
		$("#edit-"+itemId).show();

	}
	
	
	$scope.editItem= function(title,itemId) {
		$http.get("http://localhost:8080/TodoApp/api/todo/editItem/"+$scope.id+"/"+itemId+"/"+title)
		.success(function(response) {
			$scope.todo=response;

		});

	}
	
	
	
	$scope.addItem= function() {

		if ($scope.title != "") {
			
			$http.get("http://localhost:8080/TodoApp/api/todo/createItem/"+$scope.title+"/"+$scope.id)
			.success(function(response) {

				$scope.todo=response;
				

			});

		}
	}

	$scope.loadItems();

} ]);
