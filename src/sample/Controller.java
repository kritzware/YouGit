package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import javafx.scene.Parent;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class Controller implements Initializable{

    @FXML
    private Button bt1;

    @FXML
    private Button bt2;

    @FXML
    private Button bt3;


    @FXML
    private Button bt4;



    Stage stage;

    Parent root;

    @FXML
    public void ClickedAction(ActionEvent event) throws IOException{

        if(event.getSource() == bt1){
            stage = (Stage) bt1.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            System.out.println("Success sample");

        }else if(event.getSource() == bt2){
            stage = (Stage) bt2.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("Branches.fxml"));
            System.out.println("Success on branches");
        }else if(event.getSource() == bt3){
            stage = (Stage) bt3.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
            System.out.println("Success Timeline");
        }else {
            stage = (Stage) bt4.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("Browse.fxml"));
            System.out.println("Success on browse");
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }


    public void CutButton(){

        System.out.println("Create Cut");

    }

    public void CopyButton(){

        System.out.println("Copy...");

    }

    public void PasteButton(){

        System.out.println("Paste function");

    }

    public void ExitButton(){

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML
    private TextField repository_to_clone;

    @FXML
    public void cloneRepository(ActionEvent event) throws IOException, GitAPIException {

        final String REMOTE = repository_to_clone.getText();

        File local_path = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "/YouGitRepos/");
//        if(!local_path.delete()) {
//            throw new IOException("Could not delete temp file " + local_path);
//        }

        System.out.println(local_path.getParent());

        Git repo = Git.cloneRepository()
                .setURI(REMOTE)
                .setDirectory(local_path)
                .call();

        System.out.println("Repository cloned -> " + repo.getRepository().getDirectory());
    }

}
