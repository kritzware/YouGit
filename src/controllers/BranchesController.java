package controllers;

import main.Repository;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;

import java.util.List;

public class BranchesController {

    public static void init(Repository repo) {
        System.out.println("BranchesController::init");
        System.out.println(repo.toString());
    }

    public static List<Ref> load(Repository repo) throws GitAPIException {
        return repo.getBranches();
    }

}
