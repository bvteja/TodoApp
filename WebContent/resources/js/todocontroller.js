app.controller('TodoCtrl', [ "$scope", "$http", function($scope, $http) {

	$scope.title = "";
	
	$scope.todos = [];

	$scope.addTodo = function() {

		if ($scope.title != "") {
			
			$http.get("http://localhost:8080/TodoApp/api/todo/createTodo/"+$scope.title)
			.success(function(response) {

				$scope.todos=response;
				

			});

		}
	}
	
	$scope.editView= function(itemId) {
		$("#content-"+itemId).hide();
		$("#edit-"+itemId).show();

	}
	
	
	$scope.editItem= function(title,itemId) {
		$http.get("http://localhost:8080/TodoApp/api/todo/editTodo/"+itemId+"/"+title)
		.success(function(response) {
			$scope.todos=response;

		});

	}
	
	$scope.removeList = function(id) {
		$http.get("http://localhost:8080/TodoApp/api/todo/removeTodo/"+id)
		.success(function(response) {
			$scope.todos=response;
		
		});
	
}
	
	$scope.loadTodo = function() {
			$http.get("http://localhost:8080/TodoApp/api/todo/todo")
			.success(function(response) {
				$scope.todos=response;
			
			});
		
	}

	
	$scope.loadTodo();
	
} ]);
