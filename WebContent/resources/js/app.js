var app = angular.module('app', [ 'ui.router' ])

.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider.state('todo', {
		url : '/todo',
		templateUrl : 'todo.html',
		controller : 'TodoCtrl'
	})
	
	.state('items', {
		url : '/items/:id',
		templateUrl : 'item.html',
		controller : 'ItemCtrl'
	})
	
	;

	$urlRouterProvider.otherwise('/todo');

});
