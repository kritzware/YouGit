package tests;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import sample.Repository;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * Created by Louis on 06/02/2017.
 */

public class RepositoryTest {

    @Test
    public void initRepositoryNew() {
        Repository repository = new Repository("unitTest");

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
}
