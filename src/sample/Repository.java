package sample;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.File;
import java.io.IOException;

public class Repository {

    private Git repo;
    private String directory;
    private boolean is_remote;

    Repository(String directory, boolean is_remote) {
        this.directory = directory;
        this.is_remote = is_remote;

//        File file = new File("test.txt");
//        file.createNewFile();
//        Git git = Git.init().setDirectory(file.getParentFile()).call();
    }

    public void add(File file) throws GitAPIException {
        this.repo.add().addFilepattern("test_file").call();
    }

    public void commit(String message) throws GitAPIException {
        this.repo.commit().setMessage("commit message").call();
    }

    public void getCommits() throws IOException, GitAPIException {
        Iterable<RevCommit> commits = this.repo.log().all().call();
        System.out.println(commits.toString());
    }

    public void push() {

    }

    public String toString() {
        return directory + ", " + is_remote;
    }

}