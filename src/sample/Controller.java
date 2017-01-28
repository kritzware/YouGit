package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

public class Controller {

    @FXML
    private TextField repository_to_clone;

    @FXML
    public void cloneRepository(ActionEvent event) throws IOException, GitAPIException {

        final String REMOTE = repository_to_clone.getText();

        File local_path = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "/YouGitRepos/");
//        if(!local_path.delete()) {
//            throw new IOException("Could not delete temp file " + local_path);
//        }

        System.out.println(local_path.getParent());

        Git repo = Git.cloneRepository()
                .setURI(REMOTE)
                .setDirectory(local_path)
                .call();

        System.out.println("Repository cloned -> " + repo.getRepository().getDirectory());
    }

}
