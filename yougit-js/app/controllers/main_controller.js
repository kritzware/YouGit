const Config = require('../src/config')

angular.module('app.main_controller', []).controller('mainCtrl', function($scope, $location, utils, repository) {

  $scope.loading = false
  $scope.changes = []
  $scope.repoName = null

  const status_colour_rel = {
    'NEW': 'positive',
    'MODIFIED': 'warning',
    'DELETED': 'error'
  }

  init()
  .then((repo) => {
    if(!repo) {
      return repository.new(repo)
      .then(() => {
        return Config.set({repo: repository.git.name})
        // close setup and init main view
      })
    } else {
      return repository.new(repo)
      .then(() => {
        // init main view
      })
    }
  })
  .then(() => {
    console.log(repository)
    $scope.repoName = repository.git.name

    repository.git.getStatus()
    .then((status) => {
      status.forEach(file => {
        const file_status = repository.git.statusToText(file)
        $scope.changes.push({
          file: file.path(),
          status: file_status,
          colour: status_colour_rel[file_status]
        })
      })
      $scope.$apply()
    })

    $scope.loading = false
    $scope.$apply()
  })
  .catch(err => console.error(err))

  $scope.go = function(loc) {
    utils.go(loc)
  }

  function init() {
    $scope.loading = true
    return new Promise((resolve, reject) => {
      Config.getConfig()
      .then(conf => {
        if(!conf) {
          console.log('no config found')
          resolve(null)
        } else {
          const repoDir = conf.repo
          resolve(repoDir)
        }
      })
    })
  }

})