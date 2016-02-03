package messenger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import messenger.Controller.MainFormController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        log.info("Starting application");
        String fxmlFile = "/fxml/Connect.fxml";
        log.debug("Loading FXML for connect view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 400, 200);
        stage.setTitle("NC Messenger Client - Connect");
        stage.setScene(scene);
        stage.show();
    }
}
