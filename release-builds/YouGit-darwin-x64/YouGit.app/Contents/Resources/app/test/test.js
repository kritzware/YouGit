const expect = require('chai').expect
const rimraf = require('rimraf')
const fs = require('fs')

const Repository = require('../src/repository').Repository
const user_documents = require('os').homedir()
const TEST_NAME = 'kritzbot'
const TEST_URL  = 'https://github.com/kritzware/kritzbot.git'

describe('Repository Setup', () => {

    beforeEach((done) => {
        rimraf(user_documents + '/YouGitRepos/' + TEST_NAME, () => {
            done()
        })
    })

    describe('constructor', () => {
        it('should create a new repository object', () => {
            const git = new Repository(TEST_NAME, {
                url: TEST_URL,
                alreadyExists: false,
                dest: null
            })
            expect(git).to.have.property('name').that.equals(TEST_NAME)
            expect(git).to.have.property('alreadyExists').that.equals(false)
            expect(git).to.have.property('url').that.equals(TEST_URL)
            expect(git).to.have.property('dest').that.equals(null)
            expect(git).to.have.property('git').that.equals(null)
        })
    })

    describe('clone', () => {
        before(done => {
            rimraf(user_documents + '/Desktop/' + TEST_NAME, () => done())
        })
        it('should clone a repository at the default location', (done) => {
            const git = new Repository(TEST_NAME, {
                url: TEST_URL,
                alreadyExists: false,
                dest: null
            })
            git.clone(git.name, git.url, git.dest)
            .then((cloned_repo) => {
                expect(cloned_repo).to.not.be.null
                expect(fs.existsSync(user_documents + '/YouGitRepos/' + TEST_NAME))
                expect(fs.existsSync(user_documents + '/YouGitRepos/' + TEST_NAME + '/.git'))
                expect(fs.existsSync(user_documents + '/YouGitRepos/' + TEST_NAME + '/src'))
                done()
            })
        })
        it('should clone a repository at a custom location', (done) => {
            const git = new Repository(TEST_NAME, {
                url: TEST_URL,
                alreadyExists: false,
                dest: user_documents + '/Desktop'
            })
            git.clone(git.name, git.url, git.dest)
            .then(cloned_repo => {
                expect(cloned_repo).to.not.be.null
                expect(fs.existsSync(user_documents + '/Desktop/' + TEST_NAME))
                expect(fs.existsSync(user_documents + '/Desktop/' + TEST_NAME + '/.git'))
                expect(fs.existsSync(user_documents + '/Desktop/' + TEST_NAME + '/src'))
                done()
            })
        })
    })

    describe('openExisting', () => {
        it('should open an existing repository', (done) => {
            const git = new Repository(TEST_NAME, {
                url: TEST_URL,
                alreadyExists: true,
                dest: user_documents + '/Desktop'
            })
            git.openExisting(git.name, git.dest)
            .then(existing_repo => {
                expect(existing_repo).to.not.be.null
                expect(fs.existsSync(user_documents + '/Desktop/' + TEST_NAME))
                expect(fs.existsSync(user_documents + '/Desktop/' + TEST_NAME + '/.git'))
                expect(fs.existsSync(user_documents + '/Desktop/' + TEST_NAME + '/src'))
                done()
            })
        })
        after(done => {
            rimraf(user_documents + '/Desktop/' + TEST_NAME, () => done())
        })
    })
})

describe('Repository Actions', () => {

    let git = null
    let latest_commit_id = null
    let branch_test = null

    before(done => {
        git = new Repository(TEST_NAME, {
            url: TEST_URL,
            alreadyExists: false,
            dest: null
        })
        git.init().then(() => done())
    })

    describe('getCommits', () => {
        it('should get a chosen number of commits from the current branch', (done) => {
            git.getCommits(100)
            .then(commits => {
                expect(commits).to.not.be.null
                expect(commits.length).to.equal(100)
                expect(commits[0]).to.have.any.keys('repo')
                latest_commit_id = commits[0].id()
                expect(latest_commit_id).to.not.be.null
                done()
            })
        })
    })

    describe('getCommit', () => {
        it('should get a single commit by Oid', (done) => {
            git.getCommit(latest_commit_id)
            .then(commit => {
                expect(commit).to.not.be.null
                expect(commit).to.have.any.keys('name', 'sha', 'rawsize', 'firstTenLines', 'author')
                done()
            })
        })
    })

    describe('getBranchCommit', () => {
        it('should get the latest commit from a specific branch', (done) => {
            git.getBranchCommit('master')
            .then(branch_commit => {
                expect(branch_commit).to.not.be.null
                done()
            })
        })
    })

    describe('getDiff', () => {
        it('should return the file changes from a specific commit by Oid', (done) => {
            git.getDiff(latest_commit_id)
            .then(diffs => {
                expect(diffs).to.not.be.null
                expect(diffs).to.be.a('array')
                expect(diffs.length).to.be.least(4)
                done()
            })
        })
    })

    describe('getStatus', () => {
        it('should get the current git status of the repository', (done) => {
            git.getStatus()
            .then(status => {
                expect(status).to.not.be.null
                expect(status).to.be.a('array')
                expect(status.length).to.equal(0)
                done()
            })
        })
    })

    describe('getBanches', () => {
        it('should return a list of all branches in the repository', (done) => {
            git.getBranches()
            .then(branches => {
                expect(branches).to.not.be.null
                expect(branches).to.be.a('array')
                branch_test = branches[0].target()
                done()
            })
        })
    })

    describe('getBranchCommits', () => {
        it('should return a list of all commits for a specific branch', (done) => {
            git.getBranchCommits(branch_test)
            .then(commits => {
                expect(commits).to.not.be.null
                expect(commits).to.have.any.keys('repo')
                done()
            })
        })
    })

    after(done => {
        rimraf(user_documents + '/YouGitRepos/' + TEST_NAME, () => done())
    })

})