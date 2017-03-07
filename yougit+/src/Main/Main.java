package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;

import Controller.MainController;

public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{

        /* Load conf.yougit into a hashmap for easy value access */
        HashMap<String, String> config = null;
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = FXMLLoader.load(getClass().getResource("../FXML/MainView.fxml"));
        primaryStage.setTitle("YouGit");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        if(config == null) {
            System.out.println("Could not load config");
            Stage modalStage = new Stage();
            root = FXMLLoader.load(getClass().getResource("../FXML/NewModal.fxml"));
            modalStage.setScene(new Scene(root));
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initOwner(primaryStage.getScene().getWindow());
            modalStage.showAndWait();
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("../FXML/MainView.fxml"));
        MainController controller = (MainController) fxmlLoader.getController();
        controller.init(config);
    }

    private void closeProgram() {
        window.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
