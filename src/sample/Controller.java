package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Repository repo;
    private HashMap<String, Object> config;

    @FXML
    private Button bt1;
    @FXML
    private Button bt2;
    @FXML
    private Button bt3;
    @FXML
    private Button bt4;

    Stage stage;
    Parent root;

    public void setConfig(HashMap<String, Object> config) {
        this.config = config;
    }

    public void init() {
        if(config == null) {
            /* Create new repository modal (option to clone/create new) */

        } else {
            /* Load existing repository */
            repo = Repository.loadExisitingRepository((String)config.get("repo"));
            System.out.println("Loaded existing repo -> " + repo.getRepoName());
        }
    }

    @FXML
    public void ClickedAction(ActionEvent event) throws IOException, GitAPIException {

        if (event.getSource() == bt1) {
            stage = (Stage) bt1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//            MainViewController.render(stage, repo);

        } else if (event.getSource() == bt2) {
            stage = (Stage) bt2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Branches.fxml"));

            System.out.println(repo);

            /* Load repository branches (remote/local) */
//            List<Ref> branches = BranchesController.load(repo);
//            System.out.println(branches);

        } else if (event.getSource() == bt3) {
            stage = (Stage) bt3.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
            System.out.println("Success Timeline");
        } else {
            stage = (Stage) bt4.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Browse.fxml"));
//
//            repo = BrowseController.newRepository();
//            Config.createConfig();
//            Config.addKey("repo", repo.getRepoName());
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void CutButton() {

        System.out.println("Create Cut");

    }

    public void CopyButton() {

        System.out.println("Copy...");

    }

    public void PasteButton() {

        System.out.println("Paste function");

    }

    public void ExitButton() {

    }

    /* init method that loads when controller is started (changing fxml views) */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}


