package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;


public class Main extends Application {
    Stage window;
    Button button;


    public static void main(String[] args) {
        launch(args);

    }
    @Override

    public void start(Stage primaryStage) throws Exception, IOException, GitAPIException {


        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        primaryStage.setTitle("YouGit");

        window = primaryStage;
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });


        Scene scene = new Scene(root, 1366, 768);

        window.setScene(scene);
        window.show();

    }


    private void closeProgram(){
       Boolean answer = ConfirmBox.display("Title", "Sure you want to exit?");
       if(answer)
           window.close();

    }

}