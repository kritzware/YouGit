package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class Main extends Application {
    Stage window;
    Button button;


    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("YouGit");

        window = primaryStage;
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });


        Scene scene = new Scene(root, 800, 550);

        window.setScene(scene);
        window.show();

    }


    private void closeProgram(){
       Boolean answer = ConfirmBox.display("Title", "Sure you want to exit?");
       if(answer)
           window.close();

    }

}