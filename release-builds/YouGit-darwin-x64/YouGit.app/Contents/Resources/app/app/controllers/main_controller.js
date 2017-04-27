const Config = require('../src/config')

angular.module('app.main_controller', []).controller('mainCtrl', function($scope, $state, utils, repository, $rootScope) {

  Config.getConfig()
  .then(conf => {
    if(conf) {
      /* Config found, loading open repository */
        repository.new({
          name: conf.repo,
          is_new: false,
          url: conf.url,
          alreadyExists: true,
          dest: conf.dest
        })
        .then(() => {
          console.log('loaded open repo')
          $state.go('repository.changes')
        })
        .catch(err => {
          console.error(err)
        })
    } else {
      $state.go('new')
    }
  })

  $rootScope.$on('$stateChangeStart', (event, toState, toParams, fromState, fromParams, options) => { 
    // console.log(event)
  })

  // $scope.loading = false
  // $scope.changes = []
  // $scope.repoName = null

  // const status_colour_rel = {
  //   'NEW': 'positive',
  //   'MODIFIED': 'warning',
  //   'DELETED': 'error'
  // }

  // init()
  // .then((repo) => {
  //   if(!repo) {
  //     return 'new_repo'
  //   } else {
  //     console.log(repo)
  //     return repository.new(repo)
  //   }
  // })
  // .then(new_repo => {
  //   if(new_repo) {
  //     utils.go('new')
  //     return $q.resolve()
  //   }
    
  //   $scope.repoName = repository.git.name

  //   repository.git.getStatus()
  //   .then((status) => {
  //     status.forEach(file => {
  //       const file_status = repository.git.statusToText(file)
  //       $scope.changes.push({
  //         file: file.path(),
  //         status: file_status,
  //         colour: status_colour_rel[file_status]
  //       })
  //     })
  //     $scope.$apply()
  //   })

  //   $scope.loading = false
  //   $scope.$apply()
  // })
  // .catch(err => console.error(err))

  // function init() {
  //   $scope.loading = true
  //   return new Promise((resolve, reject) => {
  //     Config.getConfig()
  //     .then(conf => {
  //       if(!conf) {
  //         console.log('no config found')
  //         resolve(null)
  //       } else {
  //         const repoDir = conf.repo
  //         resolve(repoDir)
  //       }
  //     })
  //   })
  // }

})