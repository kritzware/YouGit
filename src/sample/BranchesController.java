package sample;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;

import java.util.List;

public class BranchesController {

    public static List<Ref> load(Repository repo) throws GitAPIException {
        return repo.getBranches();
    }

}
