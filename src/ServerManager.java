import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * this class is for the server
 * of the player that set to be the server of the game
 * in network moood
 */
public class ServerManager {
    ServerSocket ss;
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public ServerManager() {
        try
        {
            System.out.println("Server Started");
            ss=new ServerSocket(100);
            s=ss.accept();
            System.out.println(s);
            System.out.println("CLIENT CONNECTED");
            objectInputStream = new ObjectInputStream(s.getInputStream());
            objectOutputStream = new ObjectOutputStream(s.getOutputStream());
            dis= new DataInputStream(s.getInputStream());
            dos= new DataOutputStream(s.getOutputStream());
            ServerChat();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    /**
     * the constructor
     * @throws IOException
     */
    public void ServerChat() throws IOException {
        String str, s1;
        do
        {
            str=dis.readUTF();
            System.out.println("Client Message:"+str);
            BufferedReader br=new BufferedReader(new   InputStreamReader(System.in));
            s1=br.readLine();
            dos.writeUTF(s1);
            dos.flush();
        }
        while(!s1.equals("bye"));
    }

}