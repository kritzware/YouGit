package controllers;

import main.Repository;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainController {

    public static void init(Repository repo) throws IOException, GitAPIException {
        System.out.println("MainController::init");
        System.out.println(repo.toString());

        HashMap<String,String> commits = repo.getCommits();

        for(Map.Entry<String,String> commit : commits.entrySet()) {
            String commitId = commit.getKey();
            String commitMessage = commit.getValue();
            System.out.println(commitMessage);
        }
    }

}
