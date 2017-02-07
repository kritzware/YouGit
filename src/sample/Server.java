package sample;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Created by Ross on 07/02/2017.
 */
public class Server extends NetworkConnection{

    private int port;

    public Server(Consumer<Serializable> onRecieveCallback)
    {
        super(onRecieveCallback);
        this.port = port;
    }

    @Override
    protected boolean isServer()
    {
        return true;
    }

    @Override
    protected String getIP()
    {
        return null;
    }

    @Override
    protected int getPort()
    {
        return port;
    }
}
