angular.module('app.history_controller', []).controller('historyCtrl', function($scope, utils, repository) {

  $scope.go = function(loc) {
    utils.go(loc)
  }

  $scope.commits = []
  $scope.commits_to_show = 50
  $scope.loading = false

  init()

  function init() {
    $scope.loading = true
    repository.git.getCommits($scope.commits_to_show)
    .then((commits) => {
      $scope.commits = commits
      commits.forEach(commit => {
        commit.date = moment(commit.date()).fromNow()
      })
      $scope.loading = false
      $scope.$apply()
    })
  }

})