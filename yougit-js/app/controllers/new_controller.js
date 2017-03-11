const {dialog} = require('electron').remote
const newConfig = require('../src/config')

angular.module('app.new_controller', []).controller('newCtrl', function($scope, $state, utils, repository) {

  $scope.create = false
  $scope.clone = false
  $scope.chosenCloneFolder = null
  $scope.loadingClone = false

  $scope.newClone = {
    name: null,
    url: null,
  }

  $scope.cloneRepo = () => {
    $scope.loadingClone = true

    return repository.new({
      is_new: true,
      name: $scope.newClone.name,
      url: $scope.newClone.url,
      alreadyExists: false,
      dest: $scope.chosenCloneFolder
    })
    .then(() => {
      newConfig.set({
        repo: repository.git.name,
        dest: repository.git.dest ? repository.git.dest : 'default',
        url: repository.git.url
      })
      .then(() => {
        $scope.loadingClone = false
        $scope.$apply()
        $state.go('repository')
      })
    })
    .catch(err => console.error(err))
  }

  $scope.chooseSaveLocation = () => {
    const location = dialog.showOpenDialog({properties: ['openDirectory']})
    if(location) {
      if(location.length > 0) {
        $scope.chosenCloneFolder = location[0]
      }
    }
  }
})