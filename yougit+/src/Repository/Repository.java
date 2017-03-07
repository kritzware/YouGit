package Repository;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import javax.swing.filechooser.FileSystemView;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Repository {

    private Git git;
    private String repoName, repoURL;
    private boolean isRemote;

    public Repository(String repoName) {
        this.repoName = repoName;
        this.repoURL = null;
        this.isRemote = false;
        try {
            File localPath = new File(FileSystemView
                    .getFileSystemView()
                    .getDefaultDirectory()
                    .getPath()
                    + "/YouGitRepos/" + repoName + "/");
            if(localPath.exists()) {
                System.out.println("Stopping.. Repository already exists");
                throw new java.lang.Error("repo_already_exists");
            }
            git = Git.init().setDirectory(localPath).call();
            System.out.println("Repository created -> " + git.getRepository().getDirectory());
        } catch (Exception err) {
            System.out.println("Error creating repository");
            System.out.println(err.getMessage());
        }
    }

    public Repository(String repoName, String repoURL) {
        this.repoName = repoName;
        this.repoURL = repoURL;
        this.isRemote = true;
        try {
            File localPath = new File(FileSystemView
                    .getFileSystemView()
                    .getDefaultDirectory()
                    .getPath()
                    + "/YouGitRepos/" + repoName + "/");
            if(localPath.exists()) {
                System.out.println("Stopping.. Repository already exists");
                throw new java.lang.Error("repo_already_exists");
            }
            git = Git.cloneRepository()
                    .setURI(repoURL)
                    .setDirectory(localPath)
                    .call();
            System.out.println("Repository cloned -> " + git.getRepository().getDirectory());
        } catch (Exception err) {
            System.out.println("Error creating repository");
            System.out.println(err.getMessage());
        }
    }

    public Repository(String repoName, Git existingRepo) {
        this.repoName = repoName;
        git = existingRepo;
    }

    public static Repository loadExistingRepository(String name) {
        Repository existing = null;
        File repoDir = new File(FileSystemView
                .getFileSystemView()
                .getDefaultDirectory()
                .getPath()
                + "/YouGitRepos/" + name + "/.git/");
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        try (org.eclipse.jgit.lib.Repository repository = builder.setGitDir(repoDir)
                .readEnvironment()
                .findGitDir()
                .build()) {
            existing = new Repository(name, new Git(repository));
        } catch(Exception e) {
            e.getCause().getStackTrace();
        }
        return existing;
    }

    public void add(File file) {

    }

    public void commit(String message) {

    }

    public void push() {

    }

    public List<Ref> getBranches() throws GitAPIException {
        List<Ref> branches = new ArrayList<>();
        List<Ref> call = this.git.branchList().call();
        for(Ref ref : call) {
            branches.add(ref);
            System.out.println("Branch: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
        }
        call = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
        for(Ref ref : call) {
            branches.add(ref);
            System.out.println("Branch: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
        }
        return branches;
    }

    public String getBranchName(Ref branch) {
        return branch.getName();
    }

    public LinkedHashMap<String,String> getCommits() throws IOException, GitAPIException {
        LinkedHashMap<String,String> commitsMap = new LinkedHashMap<>();
        Iterable<RevCommit> commits = this.git.log().all().call();
        for(Iterator<RevCommit> iterator = commits.iterator(); iterator.hasNext();) {
            RevCommit commit = iterator.next();
            commitsMap.put(commit.getName(), commit.getFullMessage());
        }
        return commitsMap;
    }

    public void getUncommitedChanges() throws GitAPIException {
        Status status = this.git.status().call();

        Set<String> conflicting = status.getConflicting();
        for(String conflict : conflicting) {
            System.out.println(conflict);
        }

        Set<String> added = status.getAdded();
        for(String add : added) {
            System.out.println(add);
        }

        Set<String> changed = status.getChanged();
        for(String change : changed) {
            System.out.println(change);
        }

        Set<String> modified = status.getModified();
        for(String modify : modified) {
            System.out.println(modify);
        }
    }

    public void status() throws GitAPIException {
        Status status = this.git.status().call();
        System.out.println("Added: " + status.getAdded());
        System.out.println("Changed: " + status.getChanged());
        System.out.println("Conflicting: " + status.getConflicting());
        System.out.println("ConflictingStageState: " + status.getConflictingStageState());
        System.out.println("IgnoredNotInIndex: " + status.getIgnoredNotInIndex());
        System.out.println("Missing: " + status.getMissing());
        System.out.println("Modified: " + status.getModified());
        System.out.println("Removed: " + status.getRemoved());
        System.out.println("Untracked: " + status.getUntracked());
        System.out.println("UntrackedFolders: " + status.getUntrackedFolders());
    }

    public void displayFiles() {
        File repoFiles = new File(FileSystemView
                .getFileSystemView()
                .getDefaultDirectory()
                .getPath()
                + "/YouGitRepos/" + this.repoName + "/");
        System.out.println(repoFiles);
    }

    public String getRepoName() {
        return repoName;
    }

    public String getRepoURL() {
        return repoURL;
    }

    public String toString() {
        return "repoName: " + repoName + ", repoURL: " + repoURL;
    }

}