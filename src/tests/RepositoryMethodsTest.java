package tests;

import main.Repository;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.junit.*;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class RepositoryMethodsTest {

    @Test
    public void getRepositoryBranches() throws GitAPIException {
        Repository repo = new Repository("kritzbot", "https://github.com/kritzware/kritzbot.git");
        List<Ref> branches = repo.getBranches();

        assertEquals(repo.getBranchName(branches.get(0)), "refs/heads/master");
        assertEquals(repo.getBranchName(branches.get(3)), "refs/remotes/origin/dev");
        assertEquals(repo.getBranchName(branches.get(4)), "refs/remotes/origin/master");
    }

    @Test
    public void getRepositoryCommits() throws GitAPIException, IOException {
        Repository repo = new Repository("kritzbot2", "https://github.com/kritzware/kritzbot.git");
        HashMap<String,String> commits = repo.getCommits();

        String commit1 = (new ArrayList<String>(commits.values())).get(0);
        String commit2 = (new ArrayList<String>(commits.values())).get(1);

        assertEquals(commit1, "Merge branch 'new' of https://github.com/kritzware/kritzbot into new\n");
        assertEquals(commit2, "Check if song requests enabled.\n");
    }

    @Before
    public void preTest() {
        System.out.println("Deleting test files");
        File cloneTest = new File(FileSystemView
                .getFileSystemView()
                .getDefaultDirectory()
                .getPath()
                + "/YouGitRepos/kritzbot");
        deleteFolder(cloneTest);

        /* If some files weren't deleted, try again */
        if(cloneTest.exists()) {
            deleteFolder(cloneTest);
        }
    }

    @After
    public void cleanup() {
        System.out.println("Deleting test files");
        File cloneTest = new File(FileSystemView
                .getFileSystemView()
                .getDefaultDirectory()
                .getPath()
                + "/YouGitRepos/kritzbot");
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
