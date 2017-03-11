angular.module('app.changes_controller', []).controller('changesCtrl', function($scope, $state, utils, repository, $rootScope) {

  $rootScope.navActive = 'changes'
  $scope.loading = false
  $scope.changes = []
  $scope.repoName = repository.git.name

  const status_colour_rel = {
    'NEW': 'positive',
    'MODIFIED': 'warning',
    'DELETED': 'error'
  }

  init()
  .then(() => {
    $scope.loading = false
    $scope.$apply()
  })
  .catch(err => console.error(err))

  function init() {
    $scope.loading = true
    return repository.git.getStatus()
    .then((status) => {
      status.forEach(file => {
        const file_status = repository.git.statusToText(file)
        $scope.changes.push({
          file: file.path(),
          status: file_status,
          colour: status_colour_rel[file_status]
        })
      })
    })
  }

})