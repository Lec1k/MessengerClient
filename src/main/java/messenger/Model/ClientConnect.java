package messenger.Model;

import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by LeC1K on 01.02.2016.
 */
public class ClientConnect implements Runnable {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ClientConnect.class);
    Socket st = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;
    private String usrname = null;
    private String ip = null;

    public ClientConnect(String usr){
        this.usrname = usr;
        ip = "127.0.0.1";
    }

    public void connect(){
        try {
            boolean accepted = false;
            do{
                accepted = false;
                dos.writeUTF(usrname);
                //String reply = dis.readUTF();
                accepted = true;
            }
            while (accepted == false);
        }
        catch (Exception e){
            LOG.warn("",e);
        }
    }



    @Override
    public void run() {
        try {


            st = new Socket(ip, 8881);
            dis = new DataInputStream(st.getInputStream());
            dos = new DataOutputStream(st.getOutputStream());
        }
        catch (Exception e){
            LOG.warn("",e);
        }
        connect();
    }
}
