const Promise = require('bluebird')

angular.module('app.branches_controller', []).controller('branchesCtrl', function($scope, $state, utils, repository, $rootScope) {

  $rootScope.navActive = 'branches'
  $scope.loading = false
  $scope.branches = []
  $scope.commits = []

  const COMMIT_INTERVAL = 10
  const BRANCH_SERIES = []

  init()
  .then(() => {
  
    // $scope.commits.forEach((commit, index) => {
    //   addLine(holder, index)      
    // })

    // repository.git.getBranchCommit($scope.branches[1].name())
    // .then(commit => {
    //   console.log(commit)
    //   console.log(commit.message())
    // })

    $scope.branchesChart = {
      chart: {},
      navigator: {
        enabled: true,
        series: {
          data: []
        }
      },
      options: {
        spline: {
            marker: {
                enabled: true
            }
        },
        zoom: {
          enabled: true
        },
        chart: {
            type: 'spline',
            zoomType: 'x'
        },
        xAxis: {
          type: 'datetime',
          crosshair: true
        },
      },
      exporting: false,
      title: {
        text: ''
      },
      credits: {
        enabled: false
      },
      series: BRANCH_SERIES,
    }

    $scope.branches.forEach(branch => {
      BRANCH_SERIES.push({
        name: branch.name(),
        id: branch.name(),
        data: [],
      })
    })

    // BRANCH_SERIES.push({
    //   name: 'master',
    //   id: 'master',
    //   data: [],
    //   color: '#000000'
    // })

    // $scope.branches.forEach(branch => {
    //   if(branch.name != 'refs/head/master') {
    //     repository.git.test(branch.target())
    //     .then((commits) => {
    //       console.log(commits)
    //     })
    //   }
    // })

    repository.git.test($scope.branches[1].target())
    .then(commits => {
      setTimeout(function() {
      console.log(commits)
      commits.forEach(c => {
        console.log(c.message())
      })
      }, 3000)
    })

    $scope.commits.forEach((commit, index) => {
      BRANCH_SERIES[0].data.push({
        x: moment(commit.date()).valueOf(),
        y: 100
      })
    })

    $scope.branches.forEach((branch, index) => {
      if(branch.name != 'refs/head/master') {
        _.find(BRANCH_SERIES, {name: branch.name()}).data.push({
          x: moment('2017-01-11').valueOf(),
          y: 100 - (index * 0.25)
        })
      }
    })

    // $scope.branches.forEach((branch, index) => {
    //   BRANCH_SERIES.push({
    //     name: branch.name(),
    //     data: [50 + (index * 0.25), 50 + (index * 0.25), 50 + (index * 0.25), 50 + (index * 0.25)]
    //   })
    // })
  
    $scope.loading = false
    $scope.$apply()
  })


  function init() {
    $scope.loading = true
    
    return Promise.all([
      repository.git.getBranches(),
      repository.git.getCommits(200)
    ])
    .spread((branches, commits) => {
      $scope.branches = branches
      $scope.commits = commits
    })
    
    // return repository.git.getBranches()
    // .then(branches => {
    //   $scope.branches = branches
    //   branches.forEach(branch => {
    //     console.log(branch.name())
    //   })
    // })
  }

})