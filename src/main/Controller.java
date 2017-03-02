package main;

import controllers.BranchesController;
import controllers.BrowseController;
import controllers.MainController;
import controllers.TimelineController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
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
    @FXML
    private Button cloneandcreate;
    @FXML
    private Button clone;

    @FXML
    private Button create;




    @FXML
    TabPane tabPane;

    Stage stage;
    Parent root;

    public void setConfig(HashMap<String, Object> config) {
        this.config = config;
    }

    public void setRepository(Repository repo) {
        this.repo = repo;
    }

    public void init() {
        if(config == null) {
            /* Create new repository modal (option to clone/create new) */

        } else {
            /* Load existing repository */
            repo = Repository.loadExistingRepository((String)config.get("repo"));
            System.out.println("Loaded existing repo -> " + repo.getRepoName());
        }
    }

    public void controllerInit() {
        System.out.println("Controller::controllerInit");
    }

    @FXML
    public void ClickedAction(ActionEvent event) throws IOException, GitAPIException {
        String loadController = null;

        if(event.getSource() == bt1) {
           // stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Main.fxml"));
            root = fxmlLoader.load();
            Controller controller = fxmlLoader.<Controller>getController();
            controller.setConfig(config);
            controller.setRepository(repo);
            stage = (Stage) bt1.getScene().getWindow();
            loadController = "main";

        }else if(event.getSource() == bt2) {
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Branches.fxml"));
            root = fxmlLoader.load();
            Controller controller = fxmlLoader.<Controller>getController();
            controller.setConfig(config);
            controller.setRepository(repo);
            stage = (Stage) bt1.getScene().getWindow();
            loadController = "branches";

        }else if(event.getSource() == bt3) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/TimeLine.fxml"));
            root = fxmlLoader.load();
            Controller controller = fxmlLoader.<Controller>getController();
            controller.setConfig(config);
            controller.setRepository(repo);
            stage = (Stage) bt1.getScene().getWindow();
            loadController = "timeline";

        }else if(event.getSource() == bt4) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Browse.fxml"));
            root = fxmlLoader.load();
            Controller controller = fxmlLoader.<Controller>getController();
            controller.setConfig(config);
            controller.setRepository(repo);
            stage = (Stage) bt1.getScene().getWindow();
            loadController = "browse";

        }else if(event.getSource() == cloneandcreate) {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("../fxml/CloneAndCreate.fxml"));
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(cloneandcreate.getScene().getWindow());
            stage.showAndWait();
        }else if(event.getSource() == clone){
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("../fxml/Clone.fxml"));
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(clone.getScene().getWindow());
            stage.showAndWait();
        }else if(event.getSource() == create) {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("../fxml/CREATE.fxml"));
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(create.getScene().getWindow());
            stage.showAndWait();
        }else {
            stage = (Stage) clone.getScene().getWindow();
            stage.close();
        }


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        switch(loadController) {
            case "main":
                MainController.init(repo);
                break;
            case "branches":
                BranchesController.init(repo);
                break;
            case "timeline":
                TimelineController.init();
                break;
            case "browse":
                BrowseController.init();
                break;
            default:
                System.out.println("Route Controller --> default");
        }
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


