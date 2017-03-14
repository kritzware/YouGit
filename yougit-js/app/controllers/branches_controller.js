const Promise = require('bluebird')

angular.module('app.branches_controller', []).controller('branchesCtrl', function($scope, $state, utils, repository, $rootScope) {

  $rootScope.navActive = 'branches'
  $scope.loading = false
  $scope.branches = []
  $scope.commits = []

  const COMMIT_INTERVAL = 10
  const BRANCH_SERIES = []
  const BRANCH_COLOR_REL = {
    'refs/heads/master': 'black'
  }

  $scope.branchesChart = {
    chart: {},
    navigator: {
      enabled: true,
      series: {
        data: []
      }
    },
    plotOptions: {
      spline: {
        marker: {
          enabled: true
        }
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
      yAxis: {
        labels: {
          enabled: false
        },
        title: {
          text: ''
        }
      }
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

  init()
  .then(() => {
    $scope.branches.forEach(branch => {
      BRANCH_SERIES.push({
        name: branch._name,
        id: branch._name,
        data: [],
        marker: {
          enabled: true
        },
        color: BRANCH_COLOR_REL[branch._name]
      })
    })

    return Promise.resolve($scope.branches).map(branch => {
      if(branch._name != 'master') {
        return repository.git.getBranchCommits(branch.target())
        .then(commits => {
          branch.commits = commits
          return Promise.resolve()
        })
      }
    })
    .then(() => {

      console.log(BRANCH_SERIES)

      $scope.commits.forEach((commit, index) => {
        _.find(BRANCH_SERIES, {name: 'master'}).data.push({
          x: moment(commit.date()).valueOf(),
          y: 100
        })
      })

      $scope.branches.forEach((branch, index) => {
        if(branch._name != 'master' && branch.commits) {
          _.find(BRANCH_SERIES, {name: branch._name}).data.push({
            x: moment(branch.commits.date()).subtract(1, 'days').valueOf(),
            y: 100
          })
          _.find(BRANCH_SERIES, {name: branch._name}).data.push({
            x: moment(branch.commits.date()).valueOf(),
            y: 100 - (index * 0.25)
          })
          const rand_date = Math.random() * 50
          _.find(BRANCH_SERIES, {name: branch._name}).data.push({
            x: moment(branch.commits.date()).add(rand_date, 'days').valueOf(),
            y: 100 - (index * 0.25)
          })
          _.find(BRANCH_SERIES, {name: branch._name}).data.push({
            x: moment(branch.commits.date()).add(rand_date, 'days').add(1, 'days').valueOf(),
            y: 100
          })
        }
      })

      $scope.loading = false
      $scope.$apply()
    })
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
      _.remove($scope.branches, (b) => {
        return b.name() === 'refs/remotes/origin/master'
      })
      $scope.branches.forEach(branch => {
        console.log(branch.name())
        const split_name = branch.name().split('/')
        branch._name = split_name[split_name.length - 1]
      })
    })
  }

})