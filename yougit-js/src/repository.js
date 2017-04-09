// const remote = require('electron').remote;
// const app = remote.app;
const Git = require('nodegit')
const userDocuments = require('os').homedir() 
const Promise = require('bluebird')
const fs = require('fs')

function Repository(name, opt) {
  this.name = name
  this.alreadyExists = opt.alreadyExists
  this.url = opt.url
  this.dest = opt.dest
  this.git = null
}

Repository.prototype = {

  init() {
    return new Promise((resolve, reject) => {
      if(this.alreadyExists) {
        this.openExisting(this.name, this.dest)
        .then(repo => {
          this.git = repo
          resolve()
        })
        .catch(err => reject(err))
      } else {
        this.clone(this.name, this.url, this.dest)
        .then(repo => {
          this.git = repo
          resolve()
        })
        .catch(err => reject(err))
      }
    })
  },

  clone(name, url, customDir) {
    let location = userDocuments + "/YouGitRepos/" + name + "/"
    if(customDir) {
      location = customDir + "/" + name
      fs.mkdirSync(location)
    }
    return Git.Clone(url, location)
    .then(repo => {
      return repo
    })
  },

  openExisting(name, customDir) {
    let location = userDocuments + "/YouGitRepos/" + name + "/"
    if(customDir != 'default') {
      location = customDir + "/" + name
    }
    return Git.Repository.open(location)
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
      return commit.getEntry("src/bot.js")
      .then(entry => {
        const _entry = entry
        return entry.getBlob()
        .then(blob => {
          return {
            name: _entry.name(),
            sha: _entry.sha(),
            rawsize: blob.rawsize() + "b",
            firstTenLines: blob.toString().split('\n').slice(0, 10).join('\n'),
            message: commit.message(),
            author: commit.author()
          }
        })
      })
    })
  },

  getBranchCommit(branch_name) {
    return this.git.getBranchCommit(branch_name)
  },

  // getBranchCommits(first_commit) {
    
  // },

  getDiff(id) {
    let commitDiffLines = []
    return this.git.getCommit(id)
    .then(commit => {
        return commit.getDiff();
    })
    .then((diffList) => {
      return Promise.resolve(diffList).map(diff => {
        return diff.patches()
        .then(patches => {
          return Promise.resolve(patches).map(patch => {
            return patch.hunks()
            .then(hunks => {
              return Promise.resolve(hunks).map(hunk => {
                return hunk.lines()
                .then(lines => {
                  commitDiffLines.push("diff", patch.oldFile().path(), patch.newFile().path())
                  commitDiffLines.push(hunk.header());
                  lines.forEach(line => {
                    commitDiffLines.push(String.fromCharCode(line.origin()) + line.content());
                  })
                })
              })
            })
          })
        })
      })
    })
    .then(() => {
      return Promise.resolve(commitDiffLines)
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
  },

  getBranches() {
    return this.git.getReferences(Git.Reference.TYPE.LISTALL)
  },

  getBranchCommits(oid) {
    const revwalk = this.git.createRevWalk()
    return new Promise((resolve, reject) => {
      revwalk.push(oid)
      this.walk2(revwalk, oid)
      .then(commits => {
        resolve(commits)
      })
    })
  },

  walk2(revwalk, oid) {
    return new Promise((resolve, reject) => {
      revwalk.next()
      .then(oid => {
        if(!oid) { return }
        this.git.getCommit(oid)
        .then(commit => {
          resolve(commit)
        })
      })
    })
  },

  test(oid) {
    return new Promise((resolve, reject) => {
      let revwalk = this.git.createRevWalk()
      // revwalk.sorting(Git.Revwalk.SORT.TOPOLOGICAL, Git.Revwalk.SORT.REVERSE)
      revwalk.push(oid)
      const branch_commits = []
      this.walk(revwalk, oid, branch_commits)
      .then(() => {
        console.log(1)
      })
      resolve(branch_commits)
    })
  },

  walk(rev, oid, commits) {
    return rev.next()
    .then(oid => {
      if(!oid) {
        return
      }
      return this.git.getCommit(oid)
      .then(commit => {
        commits.push(commit)
        return walk(rev, oid, commits)
      })
    })
  }

}

module.exports.Repository = Repository