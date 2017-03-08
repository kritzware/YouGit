const template_base = 'partials'
const controllersDir = 'controllers'

angular.module('app', [
  'ngRoute',
  'app.services',
  'app.main_controller',
  'app.history_controller',
  'app.commit_controller'
])
.config(['$routeProvider', function($routeProvider) {
  
  $routeProvider
  .when('/', {
    templateUrl: template_base + '/main.html',
    controller: 'mainCtrl'
  })
  .when('/history', {
    templateUrl: template_base + '/history.html',
    controller: 'historyCtrl'
  })
  .when('/commit/:id', {
    templateUrl: template_base + '/commit.html',
    controller: 'commitCtrl'
  })

  $routeProvider.otherwise({redirectTo: '/'})

}])