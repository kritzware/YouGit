const Repository = require('../src/repository').Repository

const repo = new Repository("https://github.com/kritzware/kritzbot.git", "YouGitRepos/")

repo.clone()