import java.io.*;
import java.net.Socket;

/**
 * this class is a client for a network
 * * @author Alireza Khosrojerdi & Mehrab Safdel

 */
public class ClientManager {
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    /**
     * the constructor of class
     */
    public ClientManager() {
        try {
            //s=new Socket("10.10.0.3,10");
            s = new Socket("192.168.47.1",100);
            System.out.println(s);
            objectInputStream = new ObjectInputStream(s.getInputStream());
            objectOutputStream = new ObjectOutputStream(s.getOutputStream());
            din= new DataInputStream(s.getInputStream());
            dout= new DataOutputStream(s.getOutputStream());
            ClientChat();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    public void ClientChat() throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        String s1;
        do
        {
            s1=br.readLine();
            dout.writeUTF(s1);
            dout.flush();
            //System.out.println("Server Message:"+din.readUTF());
        }
        while(!s1.equals("stop"));
    }
}