package controllers;

import main.Repository;

public class MainController {

    public static void init(Repository repo) {
        System.out.println("MainController::init");
        System.out.println(repo.toString());
    }

}
