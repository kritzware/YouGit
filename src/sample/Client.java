package sample;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Created by Ross on 07/02/2017.
 */
public class Client extends NetworkConnection {

    private String ip;
    private int port;

    public Client(Consumer<Serializable> onRecieveCallback)
    {
        super(onRecieveCallback);
        this.ip = ip;
        this.port = port;
    }

    @Override
    protected boolean isServer()
    {
        return false;
    }

    @Override
    protected String getIP()
    {
        return ip;
    }

    @Override
    protected int getPort()
    {
        return port;
    }
}
