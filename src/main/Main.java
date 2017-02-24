package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.util.HashMap;


public class Main extends Application {
    Stage window;
    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Main.fxml"));

        /* Load conf.yougit into a hashmap for easy value access */
        HashMap<String, Object> config = null;
        try {
            config = Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = (Parent)fxmlLoader.load();
        Controller controller = fxmlLoader.<Controller>getController();
        controller.setConfig(config);

        primaryStage.setTitle("YouGit");

        window = primaryStage;
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
      
        Scene scene = new Scene(root);

        window.setScene(scene);
        window.show();
        controller.init();
    }


    private void closeProgram(){
//       Boolean answer = ConfirmBox.display("Title", "Sure you want to exit?");
//       if(answer)
//           window.close();
        window.close();
    }

}