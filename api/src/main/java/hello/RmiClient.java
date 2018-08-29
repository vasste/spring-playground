package hello;

import com.devexperts.rmi.RMIClient;
import com.devexperts.rmi.RMIEndpoint;

public class RmiClient {
    public static void main(String[] args) {
        RMIEndpoint endpoint = RMIEndpoint.createEndpoint(RMIEndpoint.Side.CLIENT);
        endpoint.connect("localhost:11555");
        RMIClient rmiClient = endpoint.getClient();
        //rmiClient.getProxy()
    }
}
