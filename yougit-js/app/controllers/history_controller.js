angular.module('app.history_controller', []).controller('historyCtrl', function($scope, utils, repository) {

  $scope.go = function(loc) {
    utils.go(loc)
  }

  $scope.commits = []
  $scope.loading = false

  const commits_to_show = 200

  init()

  function init() {
    $scope.loading = true
    repository.git.getCommits(commits_to_show)
    .then((commits) => {
      $scope.commits = commits
      commits.forEach(commit => {
        commit.date = moment(commit.date()).fromNow()
        commit.msg = commit.message()
      })
      $scope.loading = false
      $scope.$apply()
    })
  }

})