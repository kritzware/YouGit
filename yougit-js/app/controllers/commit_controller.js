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
        diffs = "<pre><code>" + diffs + "</code></pre>"
        $scope.diffs = $sce.trustAsHtml(diffs)
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

})