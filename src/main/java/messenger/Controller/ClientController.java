package messenger.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import messenger.Model.ClientChat;
import messenger.Model.ClientSetting;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ClientController.class);
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
    private TextArea messageTextArea;
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
        ClientSetting.messageTextArea = messageTextArea;
        ClientSetting.observableList = observableList;
        ClientSetting.nickTextField = nickTextField;
        ClientSetting.messageTextArea.setWrapText(true);
    }

    @FXML
    private void sendNickName(ActionEvent event) {

        String ipAddress = "127.0.0.1";
        String nick = nickTextField.getText().trim();
        if ((nick.length() == 0) && (nick.equals(""))) {
            nick = "User" + (float) Math.random() * 5;
        } else {
            LOG.info("IP Address is: " + ipAddress + "\n" + "Username is: " + nick);
        }
        connectionWindow.setVisible(false);
        clientNickNameObject = new ClientChat(ipAddress, nick);
        nickName = new Thread(clientNickNameObject);
        nickName.start();

    }

    @FXML
    private void clearMessage(ActionEvent event) {
        ClientSetting.chatTextArea.clear();
        LOG.info("ChatTextAera is clean");
    }

    @FXML
    private void onDisconnectButtonClick(ActionEvent event) {
        if (nickName!=null) {
            nickName.interrupt();
            nickName = null;
            clientNickNameObject.disconnect();
            ClientSetting.messageTextArea.appendText("Disconnected");
            ClientSetting.messageTextArea.setEditable(false);
            LOG.info("Disconnected by user");
        }


    }

}