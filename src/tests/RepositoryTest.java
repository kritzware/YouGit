package tests;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.junit.*;
import main.Repository;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RepositoryTest {

    private Repository testRepo;

    @Test(expected = Error.class)
    public void initRepositoryNew() {
        testRepo = new Repository("unitTest");
        File newRepoBase = new File(FileSystemView
                .getFileSystemView()
                .getDefaultDirectory()
                .getPath()
                + "/YouGitRepos/unitTest/");
        File newRepoGit = new File(FileSystemView
                .getFileSystemView()
                .getDefaultDirectory()
                .getPath()
                + "/YouGitRepos/unitTest/.git/");

        assertTrue("it should create a local repository", newRepoBase.exists());
        assertTrue("it should contain a .git folder", newRepoGit.exists());

        try {
            Repository dupe = new Repository("unitTest");
            Assert.fail("it should throw an error if the repository already exists");
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    @Test(expected = Error.class)
    public void initRepositoryClone() {
        Repository repository = new Repository("kritzbot", "https://github.com/kritzware/kritzbot.git");
        File newRepoBase = new File(FileSystemView
                .getFileSystemView()
                .getDefaultDirectory()
                .getPath()
                + "/YouGitRepos/kritzbot/");
        File newRepoGit = new File(FileSystemView
                .getFileSystemView()
                .getDefaultDirectory()
                .getPath()
                + "/YouGitRepos/kritzbot/.git/");

        assertTrue("it should clone a remote repository", newRepoBase.exists());
        assertTrue("it should contain a .git folder", newRepoGit.exists());

        try {
            Repository dupe = new Repository("kritzbot", "https://github.com/kritzware/kritzbot.git");
            Assert.fail("it should throw an error if the repository already exists");
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

   @Before
    public void preTest() {
        System.out.println("Deleting test files");
        File repoTest = new File(FileSystemView
                .getFileSystemView()
                .getDefaultDirectory()
                .getPath()
                + "/YouGitRepos/unitTest");
        File cloneTest = new File(FileSystemView
                .getFileSystemView()
                .getDefaultDirectory()
                .getPath()
                + "/YouGitRepos/kritzbot");
        deleteFolder(repoTest);
        deleteFolder(cloneTest);

        /* If some files weren't deleted, try again */
        if(cloneTest.exists()) {
            deleteFolder(cloneTest);
        }
    }

    @After
    public void cleanup() {
        System.out.println("Deleting test files");
        File repoTest = new File(FileSystemView
                .getFileSystemView()
                .getDefaultDirectory()
                .getPath()
                + "/YouGitRepos/unitTest");
        File cloneTest = new File(FileSystemView
                .getFileSystemView()
                .getDefaultDirectory()
                .getPath()
                + "/YouGitRepos/kritzbot");
        deleteFolder(repoTest);
        deleteFolder(cloneTest);

        /* If some files weren't deleted, try again */
        if(cloneTest.exists()) {
            deleteFolder(cloneTest);
        }
    }

    public static void deleteFolder(File dir) {
        File[] contents = dir.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteFolder(f);
            }
        }
        dir.delete();
    }
}
