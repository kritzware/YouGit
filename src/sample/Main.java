package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {

    private boolean isServer = true;
    TextArea messages = new TextArea();
    private NetworkConnection connection = isServer ? createServer() : createClient();

    /*private Parent createContent()
    {
        messages.setPrefHeight(550);
        TextField input = new TextField();
        VBox root = new VBox(20, messages,input);
        root.setPrefSize(600,600);
        return root;
    }*/

    @Override
    public void init() throws Exception
    {
        connection.startConnection();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("YouGit");
        primaryStage.setScene(new Scene(root, 1366, 768));
        messages.setPrefHeight(550);
        TextField input = new TextField();
        input.setOnAction(event ->
        {
           String message = isServer ? "Server: " : "Client: ";
           message += input.getText();
           input.clear();

           messages.appendText(message + "\n");

           try
           {
               connection.send(message);
           }
           catch(Exception e)
            {
                messages.appendText("Failed to send\n");
            }
        });
        primaryStage.show();

    }

    public void stop() throws Exception
    {
        connection.closeConnection();
    }

    private Server createServer()
    {
        return new Server(55555, data ->
        {
            Platform.runLater(() ->
            {
                messages.appendText(data.toString() + "\n");
            });
        });
    }

    private Client createClient()
    {
        return new Client("127.0.0.1", 55555, data ->
        {
            Platform.runLater(() ->
            {
                messages.appendText(data.toString() + "\n");
            });
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
