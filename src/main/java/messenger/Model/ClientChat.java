package messenger.Model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientChat implements Runnable {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ClientChat.class);
    Socket socket = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;
    private String nickName = null;
    private String ip = null;

    public ClientChat(String ip, String nickname) {
        this.nickName = nickname;
        this.ip = ip;
    }

    public void ClientChatMain() {
        try {
            dos.writeUTF(nickName);
            String reply = dis.readUTF();
            ClientSetting.chatTextArea.appendText(reply + "\n");

            try {
                while (true) {
                    String reply1 = dis.readUTF();
                    if (reply1.isEmpty()) {
                        ClientSetting.chatTextArea.appendText("Client  is empty" + reply1 + "\n");
                    } else {
                        if (reply1.startsWith("pase2347")) {

                            String usersx[] = reply1.replace("pase2347", "").split(",");
                            for (int a = 0; a < usersx.length; a++) {
                                LOG.info(usersx[a]);
                                ClientSetting.observableList.add(usersx[a]);
                            }

                        } else {
                            ClientSetting.chatTextArea.appendText(reply1 + "\n");
                            LOG.info("###" + reply1);

                        }
                    }

                    ClientSetting.sendButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String message = ClientSetting.messageTextArea.getText().trim();
                            ClientSetting.messageTextArea.setText("");
                            try {
                                dos.writeUTF(message);

                            } catch (IOException ex) {
                                LOG.warn("In on SendButton click-" + ex);
                            }
                        }
                    });

                }
            } catch (Exception ex) {
                ClientSetting.chatTextArea.appendText("Please reopen this application.");
                ClientSetting.sendButton.setDisable(true);
                LOG.info("Please reopen this application.");
            }

        } catch (Exception ex) {
            ClientSetting.chatTextArea.appendText("Server Connection error\n");
            LOG.info("Server Connection error.");

        }

    }

    @Override
    public void run() {

        try {
            socket = new Socket(ip, 8881);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            ClientSetting.chatTextArea.appendText("Server authentication failed, please reopen this application\n");
        }
        ClientChatMain();
    }

    public void disconnect() {
        try {
            socket.close();
            dis.close();
            dos.close();
        } catch (IOException e) {
            LOG.warn("In disconnect()- " + e);
        }

    }
}
