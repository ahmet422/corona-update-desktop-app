package Controller;

import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CountryTableViewController {

    @FXML
    private TableView<Country> tblView;

    @FXML
    GridPane gridPane;

    private ObservableList<Country> data;

    public CountryTableViewController(List<Country> data) {
        this.data = FXCollections.observableList(data);
    }

    @FXML
    private void initialize() {
        tblView.setItems(this.data);
    }

    public void btnBack(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) gridPane.getScene().getWindow();

        URI uri = Path.of(System.getProperty("user.dir") + "/src/main/java/View/MainGUI.fxml").toUri();
        FXMLLoader loader = new FXMLLoader(uri.toURL());

        loader.setControllerFactory(t -> Main.getMainController());

        primaryStage.setTitle("CoVID-19");


        Parent root = loader.load();

        Scene scene = new Scene(root, Main.x, Main.y);
        scene.getStylesheets().add(Main.getStyles());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
