const Repository = require('../src/repository').Repository

angular.module('app.services', [])
.factory('utils', function ($location, $state) {
    return { 
      go: function(url) {
        // $location.path('/' + url) 
        $state.go(url)
      }
    }
})
.factory('repository', function() {
  return {

    git: null,

    new(args) {
      if(args.is_new) {
        const Repo = new Repository(args.name, {
          url: args.url,
          alreadyExists: args.alreadyExists,
          dest: args.dest
        })
        return Repo.init()
        .then(() => {
          this.setGit(Repo)
          return Promise.resolve()
        })
      }
      const ExistingRepo = new Repository(args.name, {
        url: args.url,
        alreadyExists: args.alreadyExists,
        dest: args.dest
      })
      return ExistingRepo.init()
      .then(() => {
        this.setGit(ExistingRepo)
        return Promise.resolve()
      })
      .catch(err => console.error(err))
    },

    setGit(g) { this.git = g }

  }
})