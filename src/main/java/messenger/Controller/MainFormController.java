package messenger.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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


    private String address = "localhost";

    private int port = 2222;
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;


    @FXML
    private void initialize() {

        initData();
        startReading();
        writeUsers();

    }

    private void initData() {
        try {
            socket = new Socket(address, port);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(streamReader);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println(ConnectController.userName + ":has connected.:Connect");
            printWriter.flush();

        } catch (Exception ex) {
            chatTextArea.appendText("Cannot Connect! Try Again. \n");
        }

    }


    private void startReading() {
        log.info("startScheduledExecutorService started");
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(new Runnable() {
            int counter = 0;

            public void run() {
                counter++;
                if (counter <= 100000) {
                    Platform.runLater(() -> {

                        String[] data;
                        String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";
                        try
                        {
                            while ((stream = bufferedReader.readLine()) != null)
                            {
                                data = stream.split(":");

                                if (data[2].equals(chat))
                                {
                                    chatTextArea.appendText(data[0] + ": " + data[1] + "\n");
                                    chatTextArea.positionCaret(chatTextArea.getLength());
                                }

                                else if (data[2].equals(disconnect))
                                {
                                    userRemove(data[0]);
                                }
                                else if (data[2].equals(done))
                                {
                                    //users.setText("");
                                    writeUsers();
                                    ConnectController.users.clear();
                                }
                            }
                        }catch(Exception ex) { }
                    });

                }
            }

        }, 0, 2, TimeUnit.SECONDS);
    }


    public void userRemove(String data) {
        chatTextArea.appendText(data + " is now offline.\n");
    }

    public void writeUsers() {
        String[] tempList = new String[(ConnectController.users.size())];
        ConnectController.users.toArray(tempList);
        for (String token : tempList) {
            onlineUsersTextArea.appendText(token + "\n");
        }
    }

    public void sendDisconnect() {
        String bye = (ConnectController.userName + ": :Disconnect");
        try {
            printWriter.println(bye);
            printWriter.flush();
        } catch (Exception e) {
            chatTextArea.appendText("Could not send Disconnect message.\n");
        }
    }

    public void disconnect() {
        try {

            socket.close();
            chatTextArea.appendText("Disconnected.\n");
        } catch (Exception ex) {
            chatTextArea.appendText("Failed to disconnect. \n");
        }


    }


    @FXML
    private void onSendButtonClick() {
        String nothing = "";
        if ((MessageTextArea.getText()).equals(nothing)) {
            MessageTextArea.setText("");
            MessageTextArea.requestFocus();
        } else {
            try {
                printWriter.println(ConnectController.userName + ":" + MessageTextArea.getText() + ":" + "Chat");
                printWriter.flush(); // flushes the buffer
            } catch (Exception ex) {
                chatTextArea.appendText("Message was not sent. \n");
            }
            MessageTextArea.setText("");
            MessageTextArea.requestFocus();
        }

        MessageTextArea.setText("");
        MessageTextArea.requestFocus();
    }

    @FXML
    private void onDisconnectButtonClick() {
        disconnect();
        sendDisconnect();
        userRemove(ConnectController.userName);
    }

    @FXML
    private void onClearButtonClick() {
        chatTextArea.clear();
    }

}
