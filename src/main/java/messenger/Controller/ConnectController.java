package messenger.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import messenger.Model.ClientConnect;
import messenger.View.MainFormView;
import org.slf4j.LoggerFactory;


public class ConnectController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ConnectController.class);

    @FXML
    private Button connectButton;
    @FXML
    protected TextField userNameTextField;

    ClientConnect cc;
    Thread cct;

    protected static String userName = "";


    @FXML
    private void onConnectButoonClick() {
        if (userNameTextField.getText().trim().length() == 0 || userNameTextField.getText().trim().equals("")) {
            userName = "User " + (float) Math.random() * 100;

        } else {
            userName = userNameTextField.getText().trim();

        }
        cc = new ClientConnect(userName);
        cct = new Thread(cc);
        cct.start();

        log.info("Opening main form");
        Stage stage = (Stage) connectButton.getScene().getWindow();
        stage.close();
        log.info("Connect stage closed");
        log.info("Showing main form");


        userName = userNameTextField.getText();

        MainFormView.showMainForm();

    }


}
