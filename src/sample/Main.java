package sample;

import javafx.application.Application;
//entry point for javaFx Application
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
//container for all the contents
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//Label used for displaying text so it can fit within specific space
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//user interaction


public class Main extends Application implements EventHandler<ActionEvent> {
//gets the functionality from java fx

    Button Register;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        primaryStage.setTitle("YouGit");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);//horizontal
        grid.setVgap(10);//vertical
        grid.setAlignment(Pos.CENTER);

        Label user = new Label("Username");
        GridPane.setConstraints(user,0,0);
        TextField userinput = new TextField();
        userinput.setPromptText("Username");
        GridPane.setConstraints(userinput,1,0);


        Label userpass = new Label("Password");
        GridPane.setConstraints(userpass,0,1);
        TextField userinput2 = new TextField();
        userinput2.setPromptText("Password");
        GridPane.setConstraints(userinput2,1,1);

        Button log = new Button("Log in");
        GridPane.setConstraints(log,1,2);

        Register = new Button("Register");
        GridPane.setConstraints(Register,1,4);
        Register.setOnAction(this);

        grid.getChildren().addAll(user,userinput,userpass,userinput2,log,Register);

        Scene scene = new Scene(grid, 1366 ,768);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void handle(ActionEvent event){
        if(event.getSource() == Register){


        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
