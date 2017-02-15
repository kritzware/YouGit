package tests;

import org.junit.*;
import sample.Repository;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Created by Louis on 06/02/2017.
 */

public class RepositoryTest {

    private Repository testRepo;

   // @Test(expected = Error.class)
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

 //   @Test(expected = Error.class)
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

   //     assertTrue("it should clone a remote repository", newRepoBase.exists());
     //   assertTrue("it should contain a .git folder", newRepoGit.exists());

        try {
            Repository dupe = new Repository("kritzbot", "https://github.com/kritzware/kritzbot.git");
       //     Assert.fail("it should throw an error if the repository already exists");
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

<<<<<<< HEAD
    @Test(expected = Error.class)
    public void getRepositoryBranches() {
        Repository repo = new Repository("kritzbot", "https://github.com/kritzware/kritzbot.git");
        System.out.println(repo.getBranches());
    }

    @BeforeClass
=======
   // @BeforeClass
>>>>>>> af1e54cdba1633648beedf4599dda514fc9b6708
    public static void preTest() {
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

   // @AfterClass
    public static void cleanup() {
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
