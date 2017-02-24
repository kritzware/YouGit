package controllers;

import javafx.stage.Stage;
import main.Repository;

public class MainViewController {

    public static void render(Stage stage, Repository repo) {
        System.out.println("MainViewController:render()");
        System.out.println(repo);
    }

}
