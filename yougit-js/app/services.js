const Repository = require('../src/repository').Repository

angular.module('app.services', [])
.factory('utils', function ($location) {
    return { 
      go: function(url) {
        $location.path('/' + url)
      }
    }
})
.factory('repository', function() {
  return {

    git: null,

    new(repo) {
      if(!repo) {
        let Repo = new Repository('kritzbot', {
          url: 'https://github.com/kritzware/kritzbot.git',
          alreadyExists: false
        })
        return Repo.init()
        .then(() => {
          this.setGit(Repo)
          return Promise.resolve()
        })
      }
      let Repo = new Repository(repo, {
        url: null,
        alreadyExists: true
      })
      return Repo.init()
      .then(() => {
        this.setGit(Repo)
        return Promise.resolve()
      })
    },

    setGit(g) { this.git = g }

  }
})