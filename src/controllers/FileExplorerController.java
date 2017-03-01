package controllers;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

    public class FileExplorerController {

        private Button btn1;

        private ListView listview;


        public void Button1Action(ActionEvent event) {
            FileChooser fc = new FileChooser();
            File selectedFile = fc.showOpenDialog(null);

            if (selectedFile != null) {
                listview.getItems().add(selectedFile.getName());
            } else {
                System.out.println("file is not valid");
            }
        }


    }



