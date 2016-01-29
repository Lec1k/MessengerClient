package messenger.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import messenger.View.MainFormView;
import org.slf4j.LoggerFactory;


public class ConnectController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ConnectController.class);

    @FXML
    private Button connectButton;
    @FXML
    protected TextField userNameTextField;

    @FXML
    private void onConnectButoonClick(){
        log.info("Opening main form");
        Stage stage = (Stage) connectButton.getScene().getWindow();
        stage.close();
        log.info("Connect stage closed");
        log.info("Showing main form");
        MainFormView.showMainForm();

    }

}
