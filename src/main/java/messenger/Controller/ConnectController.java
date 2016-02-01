package messenger.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
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

    protected static String userName;

    protected static ObservableList<String> users = FXCollections.observableArrayList();

    public void userAdd(String data)
    {
        users.add(data);
    }

    @FXML
    private void onConnectButoonClick(){
        log.info("Opening main form");
        Stage stage = (Stage) connectButton.getScene().getWindow();
        stage.close();
        log.info("Connect stage closed");
        log.info("Showing main form");

        userAdd(userNameTextField.getText());
        userName=userNameTextField.getText();

        MainFormView.showMainForm();

    }

}
