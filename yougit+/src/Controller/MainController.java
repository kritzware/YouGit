package Controller;

import Repository.Repository;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;
import java.util.*;

public class MainController {

    private static Repository repo;
    private boolean tabSelection = false;

    @FXML ListView<String> commitsList;

    public static void init(HashMap<String,String> config) throws GitAPIException {
        repo = Repository.loadExistingRepository(config.get("repo"));
    }

    @FXML
    public void tabViewHistory(Event e) throws IOException, GitAPIException {
        if(checkTab()) {
            LinkedHashMap<String, String> commitsMap = repo.getCommits();
            List<String> commits = new ArrayList<>();
            for (Map.Entry<String, String> entry : commitsMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                commits.add(value);
            }

            ListProperty<String> listProperty = new SimpleListProperty<>();
            commitsList.itemsProperty().bind(listProperty);
            listProperty.set(FXCollections.observableArrayList(commits));

        }
    }

    @FXML
    public void tabViewBranches(Event e) {
        if(checkTab()) {
            System.out.println("BRANCHES");
        }
    }

    @FXML
    public void tabViewChanges(Event e) {
        if(checkTab()) {
            System.out.println("CHANGES");
        }
    }

    @FXML
    public void ClickedAction(ActionEvent event) {
        System.out.println("Controller");
    }

    private boolean checkTab() {
        tabSelection = !tabSelection;
        return tabSelection;
    }

}
