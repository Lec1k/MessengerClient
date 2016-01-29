package messenger.View;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

public class MainFormView {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MainFormView.class);

    public static boolean showMainForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainFormView.class.getResource("/fxml/MainForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("NC Messenger - Client");
            stage.setScene(new Scene(root1));
            stage.show();
            return true;
        } catch (Exception e) {
            log.error("In showMainForm()-", e);
            return false;
        }
    }
}
