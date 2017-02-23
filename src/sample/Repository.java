package sample;

 import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
 import org.eclipse.jgit.lib.Ref;
 import javax.swing.filechooser.FileSystemView;

import java.io.File;
 import java.util.ArrayList;
 import java.util.List;

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
//            System.out.println("Branch: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
        }
        call = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
        for(Ref ref : call) {
            branches.add(ref);
//            System.out.println("Branch: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
        }
        return branches;
    }

    public String getBranchName(Ref branch) {
        return branch.getName();
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