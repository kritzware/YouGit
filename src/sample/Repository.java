package sample;

import org.eclipse.jgit.api.*;

import java.io.File;

public class Repository {

    private Git git;
    private String directory;
    private boolean is_remote;

    Repository(String directory, boolean is_remote) {
        this.directory = directory;
        this.is_remote = is_remote;

//        File file = new File("test.txt");
//        file.createNewFile();
//        Git git = Git.init().setDirectory(file.getParentFile()).call();
    }

    public void add(File file) {

    }

    public void commit(String message) {

    }

    public void push() {

    }

    public String toString() {
        return directory + ", " + is_remote;
    }

}