const template_base = 'partials'
const controllersDir = 'controllers'

angular.module('app', [
  'ui.router',
  'ngSanitize',
  'highcharts-ng',
  'app.services',
  'app.main_controller',
  'app.history_controller',
  'app.commit_controller',
  'app.new_controller',
  'app.changes_controller',
  'app.branches_controller',
  'app.chat_controller'
])
.config(($stateProvider, $urlRouterProvider) => {

  $stateProvider
    .state('repository', {
      url: '/repository',
      templateUrl: template_base + '/main.html',
      controller: 'mainCtrl'
    })
    .state('repository.changes', {
      url: '/repo/changes',
      controller: 'changesCtrl',
      templateUrl: template_base + '/changes.html'
    })
    .state('repository.branches', {
      url: '/repo/brances',
      controller: 'branchesCtrl',
      templateUrl: template_base + '/branches.html'
    })
    .state('repository.history', {
      url: '/repo/history',
      controller: 'historyCtrl',
      templateUrl: template_base + '/history.html'
    })
    .state('repository.commit', {
      url: '/repo/commit/:id',
      controller: 'commitCtrl',
      templateUrl: template_base + '/commit.html'
    })
    .state('repository.chat', {
      url: '/repo/chat',
      controller: 'chatCtrl',
      templateUrl: template_base + '/chat.html'
    })
    .state('new', {
      url: '/new',
      templateUrl: template_base + '/new.html',
      controller: 'newCtrl'
    })

    $urlRouterProvider.otherwise('repository')
})