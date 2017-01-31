package sample;

import java.io.Serializable;
import java.net.ServerSocket;
import java.util.function.Consumer;

/**
 * Created by Ross on 26/01/2017.
 */
public abstract class NetworkConnection {

        private Consumer<Serializable> onRecieveCallback;

        public NetworkConnection(Consumer<Serializable> onRecieveCallback)
        {
            this.onRecieveCallback = onRecieveCallback;
        }

        public void startConnection() throws Exception
        {

        }

        public void send() throws Exception
        {

        }

        public void closeConnection() throws Exception
        {

        }

        protected abstract boolean isServer();
        protected abstract String getIP();
        protected abstract int getPort();

        private class ConnectionThread extends Thread
        {
            @Override
            public void run()
            {
                try(ServerSocket server = isServer() ? new ServerSocket(getPort()) : null)
            }
        }
}
