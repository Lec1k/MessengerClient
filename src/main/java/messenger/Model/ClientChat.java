package messenger.Model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientChat implements Runnable {

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
                                System.err.println(usersx[a]);
                                ClientSetting.observableList.add(usersx[a]);
                            }

                        } else {
                            ClientSetting.chatTextArea.appendText(reply1 + "\n");
                            System.out.println("###" + reply1);
                        }
                    }

                    ClientSetting.sendButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String message = ClientSetting.MessageTextArea.getText().trim();
                            ClientSetting.MessageTextArea.setText("");
                            try {
                                dos.writeUTF(message);

                            } catch (IOException ex) {
                                Logger.getLogger(ClientChat.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });

                }
            } catch (Exception ex) {
                ClientSetting.chatTextArea.appendText("The server has been switched off.Please reopen this application.");
                ClientSetting.sendButton.setDisable(true);
            }

        } catch (Exception ex) {
            ClientSetting.chatTextArea.appendText("Server Connection error\n");

        }

    }

    @Override
    public void run() {

        try {
            socket = new Socket(ip, 8881);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            ClientSetting.chatTextArea.appendText("Server Authentication Failed Please Try to login again\n");
        }
        ClientChatMain();
    }
}
