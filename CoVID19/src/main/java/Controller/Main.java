package Controller;

import Model.CoronaDataProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    private final static String styles = "stylesheet.css";
    public final static double x = 1200;
    public final static double y = 800;

    private static MainController mainController;

    @Override
    public void start(Stage primaryStage) throws Exception{

        File file = new File(System.getProperty("user.dir") + "/src/main/java/View/MainGUI.fxml");
        FXMLLoader loader = new FXMLLoader(file.toURI().toURL());

        mainController = new MainController(new CoronaDataProvider());
        loader.setControllerFactory(t -> mainController);

        primaryStage.setTitle("CoVID-19");


        Parent root = loader.load();

        Scene scene = new Scene(root, x, y);
        scene.getStylesheets().add(styles);

        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }

    public static MainController getMainController() {
        return mainController;
    }

    public static String getStyles() {
        return styles;
    }
}
