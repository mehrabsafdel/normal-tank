/**
 * this network class is for set the server and client
 */
public class Network {
    public ServerManager serverManager;
    public ClientManager clientManager;
    public boolean isServer;

    /**
     * @param isServer show that this player is server or not
     */
    public Network(boolean isServer){
        this.isServer = isServer;
        if (isServer)
            serverManager = new ServerManager();
        else
            clientManager = new ClientManager();
    }

}
