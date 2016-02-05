package messenger.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import messenger.Model.ClientChat;
import messenger.Model.ClientSetting;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

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
    @FXML
    private TextField nickTextField;
    @FXML
    private Accordion connectionWindow;

    private ClientChat clientNickNameObject;
    private Thread nickName;
    private ObservableList observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClientSetting.disconnectButton = disconnectButton;
        ClientSetting.sendButton = sendButton;
        ClientSetting.clearButton = clearButton;
        ClientSetting.chatTextArea = chatTextArea;
        ClientSetting.onlineUsersTextArea = onlineUsersTextArea;
        ClientSetting.MessageTextArea = MessageTextArea;
        ClientSetting.observableList = observableList;
        ClientSetting.nickTextField = nickTextField;

    }

    @FXML
    private void sendNickName(ActionEvent event) {

        String ipAddress = "127.0.0.1";
        String nick = nickTextField.getText().trim();
        if ((nick.length() == 0) && (nick.equals(""))) {
            nick = "User" + (char) Math.random() * 100;
        } else {
            System.err.println("IP Address is: " + ipAddress + "\n" + "Username is: " + nick);
        }
        connectionWindow.setVisible(false);
        clientNickNameObject = new ClientChat(ipAddress, nick);
        nickName = new Thread(clientNickNameObject);
        nickName.start();

    }

    @FXML
    private void clearMessage(ActionEvent event) {
        ClientSetting.chatTextArea.clear();
    }

    @FXML
    private void onDisconnectButtonClick(ActionEvent event) {
    }

    @FXML
    private void onClearButtonClick(ActionEvent event) {
    }
}