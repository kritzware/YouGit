package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;

import org.junit.rules.TemporaryFolder;
import static org.eclipse.jgit.api.Git.cloneRepository;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception, IOException, GitAPIException {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("YouGit");
        primaryStage.setScene(new Scene(root, 1366, 768));
        primaryStage.show();

        File file = new File("test.txt");
        file.createNewFile();
        Git git = Git.init().setDirectory(file.getParentFile()).call();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
