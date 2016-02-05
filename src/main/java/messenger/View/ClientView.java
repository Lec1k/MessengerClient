package messenger.View;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientView extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/chatForm.fxml"));
        stage.setTitle("NC Messenger - Client");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(we -> {
            Platform.exit();
            System.exit(0);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}