angular.module('app.commit_controller', []).controller('commitCtrl', function($scope, $location, utils, repository, $routeParams) {

  $scope.loading = false
  $scope.changes = []
  $scope.repoName = null
  $scope.commit = null
  $scope.commitId = $routeParams.id

  $scope.go = function(loc) {
    utils.go(loc)
  }

  init()

  function init() {
    $scope.loading = true
    repository.git.getCommit($scope.commitId)
    .then(commit => {
      $scope.commit = commit

      repository.git.getDiff($scope.commitId)

      $scope.loading = false
      $scope.$apply()
    })
  }

})