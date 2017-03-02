package main;

import controllers.BranchesController;
import controllers.BrowseController;
import controllers.MainController;
import controllers.TimelineController;
import javafx.application.Platform;
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

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class CloneController implements Initializable {
    @FXML
    private MenuItem menu;

    Stage stage;
    Parent root;

    @FXML
    private Button bt2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void Clone(ActionEvent event) throws IOException, GitAPIException {

        Platform.exit();
        System.exit(0);

        //URL menuBarUrl = getClass().getResource("../fxml/CLONE.fxml");
        //MenuBar bar = FXMLLoader.load( menuBarUrl );


        if(event.getSource() == bt2) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Branches.fxml"));
            root = fxmlLoader.load();
            Controller controller = fxmlLoader.<Controller>getController();
            stage = (Stage) bt2.getScene().getWindow();

        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

}


