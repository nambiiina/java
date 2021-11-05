import ws.BankService;

import javax.xml.ws.Endpoint;

public class ServerJaxWS {
    public static void main(String[] args) {
        String url = "http://0.0.0.0:8383/";
        /**
         * create jaxWS server
         */
        Endpoint.publish(url, new BankService());
        System.out.println("Server JaxWS deployed at " + url);
    }
}
