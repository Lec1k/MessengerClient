package messenger.Controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.slf4j.LoggerFactory;

public class MainFormController {

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

}
