package sample;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

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

    public String toString() {
        return "wasd";
    }

}