package Controller;

import Main.Config;
import Repository.Repository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class NewRepository {

    Parent root;
    Stage stage;

    /* Buttons */
    @FXML private Button cloneBtn, createBtn;

    /* TextFields */
    @FXML private TextField repoURL, repoName;

    @FXML
    public void CloneModalView(ActionEvent event) throws IOException {
        loadNewView("../FXML/NewRepositoryClone.fxml", cloneBtn);
    }

    @FXML
    public void CloneRepository(ActionEvent event) throws IOException {
        String url = repoURL.getText();
        String name = repoName.getText();
        Repository repo = new Repository(name, url);

        HashMap<String, String> config = null;
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(config == null) {
            Config.createConfig();
            Config.addKey("repo", name);
        } else {
            /* TODO: Replace repo key with updated repo name */
        }
        stage = (Stage) repoName.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void CreateModalView(ActionEvent event) throws IOException {
        System.out.println("CREATE");
        loadNewView("../FXML/NewRepositoryCreate.fxml", createBtn);
    }

    public void loadNewView(String fxml, Button btn) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        root = fxmlLoader.load();
        stage = (Stage) btn.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
