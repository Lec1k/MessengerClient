package messenger.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import messenger.Model.ClientSetting;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MainFormController.class);

    @FXML
    private Button disconnectButton;
    @FXML
    private Button sendButton;
    @FXML
    private Button clearButton;
    @FXML
    private TextArea chatTextArea;
    @FXML
    private TextArea onlineUsersTextArea;
    @FXML
    private TextArea MessageTextArea;


    private ObservableList xlist = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ClientSetting.chatTextArea = chatTextArea;
        ClientSetting.onlineUsersTextArea = onlineUsersTextArea;
        ClientSetting.disconnectButton = disconnectButton;
        ClientSetting.sendButton = sendButton;
        ClientSetting.clearButton = clearButton;
        ClientSetting.MessageTextArea = MessageTextArea;
        ClientSetting.xlist = xlist;

    }

    private void initialize() {

    }

    @FXML
    private void leftTextArea(MouseEvent event) {
        populateCombo();
    }

    @FXML
    private void rightTextArea(MouseEvent event) {
        populateCombo();
    }

    @FXML
    private void textfieldup(MouseEvent event) {
        populateCombo();
    }

    public void populateCombo() {
        onlineUsersTextArea.appendText("USERS UPDATE");

//        xlist.removeAll(xlist);
//        onlineUsersTextArea.clear();
//        String nm = onlineUsersTextArea.getText().toString();
//        String usersx[] = nm.split("\n");
//        for (int a = 0; a < usersx.length; a++) {
//            System.err.println(usersx[a]);
//            xlist.addAll(usersx[a]);
//        }
//        String username = hiddenusername.getText().trim();
//        xlist.remove(username);
//        ClientSetting.OnlineCombo.getItems().addAll(xlist);

    }

    @FXML
    private void onDisconnectButtonClick() {

    }

    @FXML
    private void onClearButtonClick() {
        chatTextArea.clear();
    }

    @FXML
    private void sendClick() {

    }


}
