angular.module('app.commit_controller', []).controller('commitCtrl', function($scope, $state, repository, $sce) {

  $scope.loading = false
  $scope.changes = []
  $scope.diffs = []
  $scope.repoName = null
  $scope.commit = null
  $scope.commitId = $state.params.id

  init()

  function init() {
    $scope.loading = true
    repository.git.getCommit($scope.commitId)
    .then(commit => {
      $scope.commit = commit
      commit.size = bytesToSize(parseInt(commit.rawsize))
      repository.git.getDiff($scope.commitId)
      .then((diffs) => {
        diffs.forEach((diff, index) => {
          if(diff.substring(0, 1) === '+') {
            diffs[index] = '<span style="background-color:#eaffea">' + diff + '</span>'
            // diffs[index] = '<span >' + diff + '</span>'
          }
          if(diff.substring(0, 1) === '-') {
            diffs[index] = '<span style="background-color:#ffecec">' + diff + '</span>'
            // diffs[index] = '<span >' + diff + '</span>'
          }
        })

        diffs = diffs.join('')
        diffs = diffs.split('diff')
        diffs.shift()

        diffs.forEach(diff => {
          diff = "<pre><code>" + diff + "</code></pre>"
          $scope.diffs.push($sce.trustAsHtml(diff))
        })

        $scope.loading = false
        $scope.$apply()
        $(document).ready(function() {
          $('#code-block').each(function(i, block) {
            hljs.highlightBlock(block);
          });
        });
      })
    })
    .catch(err => console.error(err))
  }

  function bytesToSize(bytes) {
    var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB']
    if (bytes == 0) return '0 Byte'
    var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)))
    return Math.round(bytes / Math.pow(1024, i), 2) + ' ' + sizes[i]
  }

})