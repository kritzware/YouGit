package sample;

/**
 * Created by Louis on 05/02/2017.
 */

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class RepositoryModal {

    static boolean answer;

    public static boolean display() {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create New Repository");
        window.setMinWidth(250);

        Button createRepository = new Button("Create New Repository");

        Button cloneRepository = new Button("Clone Existing Repository");
        Label remoteRepository = new Label("Repository URL:");
        TextField remoteInput = new TextField();

        createRepository.setOnAction(e -> {
            answer = true;
            window.close();
        });
        cloneRepository.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);

        layout.getChildren().addAll(remoteRepository, remoteInput, createRepository, cloneRepository);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

}
