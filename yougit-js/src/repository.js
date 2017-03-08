const remote = require('electron').remote;
const app = remote.app;
const Git = require('nodegit')
const userDocuments = require('os').homedir()

function Repository(name, opt) {
  this.name = name
  this.alreadyExists = opt.alreadyExists
  this.url = opt.url
  this.git = null
}

Repository.prototype = {

  init() {
    return new Promise((resolve, reject) => {
      if(this.alreadyExists) {
        this.openExisting(this.name)
        .then(repo => {
          this.git = repo
          resolve()
        })
      } else {
        this.clone(this.name, this.url)
        .then(repo => {
          this.git = repo
          resolve()
        })
        .catch(err => reject(err))
      }
    })
  },

  clone(name, url) {
    return Git.Clone(url, userDocuments + "/YouGitRepos/" + name + "/")
    .then(repo => {
      return repo
    })
  },

  openExisting(name) {
    return Git.Repository.open(userDocuments + "/YouGitRepos/" + name)
    .then(repo => {
      return repo
    })
  },

  getCommits(count) {
    const revwalk = Git.Revwalk.create(this.git)
    revwalk.pushHead()
    return revwalk.getCommits(count)
  },

  getCommit(id) {
    return this.git.getCommit(id)
    .then(commit => {
      console.log(commit)
      return commit.getEntry("src/bot.js")
      .then(entry => {
        const _entry = entry
        return entry.getBlob()
        .then(blob => {
          return {
            name: _entry.name(),
            sha: _entry.sha(),
            rawsize: blob.rawsize() + "b",
            firstTenLines: blob.toString().split('\n').slice(0, 10).join('\n')
          }
        })
      })
    })
  },

  getDiff(id) {
    return this.git.getCommit(id)
    .then(commit => {
        return commit.getDiff();
    })
    .done((diffList) => {
      diffList.forEach(diff => {
        diff.patches().then(function(patches) {
          patches.forEach(function(patch) {
            patch.hunks().then(function(hunks) {
            hunks.forEach(function(hunk) {
              hunk.lines().then(function(lines) {
                console.log("diff", patch.oldFile().path(),
                  patch.newFile().path());
                console.log(hunk.header().trim());
                lines.forEach(function(line) {
                  console.log(String.fromCharCode(line.origin()) +
                    line.content().trim());
                });
              });
            });
          })
        })
        })
      })
    })
  },

  getStatus() {
    return this.git.getStatus()
  },

  statusToText(file) {
    var words = [];
    try {
    if (file.isNew()) { words.push("NEW")}
    if (file.isModified()) { words.push("MODIFIED")}
    if (file.isTypechange()) { words.push("TYPECHANGE")}
    if (file.isRenamed()) { words.push("RENAMED")}
    if (file.isIgnored()) { words.push("IGNORED")}
    if (file.isDeleted()) { words.push("DELETED")}
    } catch(err) {
      console.log(err)
    }
    return words.join(" ");
  }

}

module.exports.Repository = Repository