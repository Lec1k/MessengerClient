package messenger.Model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.slf4j.LoggerFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientConnect implements Runnable {


    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ClientConnect.class);
    Socket st = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;
    private String nickname = null;
    private String ip = "127.0.0.1";

    public ClientConnect(String nickname) {
        this.nickname = nickname;


    }


    public void ClientNickNameMain() {
        try {

            boolean accepted = false;
            do {
                accepted = false;
                if (nickname != null) {
                    dos.writeUTF(nickname);
                }
                // reading back from server
                String reply = dis.readUTF();


                accepted = true;

            } while (accepted == false);

            try {
                while (true) {
                    String reply = dis.readUTF();
                    if (reply.isEmpty()) {

                        ClientSetting.chatTextArea.appendText("Client  is empty boss" + reply + "\n");
                    } else {
                        if (reply.startsWith("pase2347")) {

                            String usersx[] = reply.replace("pase2347", "").split(",");
                            for (int a = 0; a < usersx.length; a++) {
                                System.err.println(usersx[a]);
                                ClientSetting.onlineUsersTextArea.appendText(usersx[a] + "\n");
                                ClientSetting.xlist.add(usersx[a]);
                            }


                        } else {
                            ClientSetting.chatTextArea.appendText(reply + "\n");
                            System.out.println("........................." + reply);
                        }
                    }


                    ClientSetting.sendButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {


                            String fullmessage = ClientSetting.MessageTextArea.getText().trim();

                            ClientSetting.MessageTextArea.setText("");

                            try {

                                dos.writeUTF(fullmessage);

                            } catch (IOException ex) {
                                Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });


                }
            } catch (Exception ex) {
                ClientSetting.chatTextArea.appendText(""
                        + "          WARNING :\n"
                        + "***********************************\n"
                        + "**The server has been switched off*\n "
                        + "** Please close the application ***\n"
                        + "**   and open it again.************\n"
                        + "***********************************");

                ClientSetting.sendButton.setDisable(true);

            }


        } catch (Exception ex) {
            ClientSetting.chatTextArea.appendText("Server Connection error\n");

        }

    }


    @Override
    public void run() {

        try {

            st = new Socket(ip, 8881);
            dis = new DataInputStream(st.getInputStream());
            dos = new DataOutputStream(st.getOutputStream());
        } catch (Exception e) {
            ClientSetting.chatTextArea.appendText("Server Authetication Failed Please Try to login again\n");

        }
        ClientNickNameMain();
    }
}




