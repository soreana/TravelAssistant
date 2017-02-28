package botrestapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import controller.ControllerController;
import org.json.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;

/**
 * Created by sinakashipazha on 2/27/2017 AD.
 */

public class TelegramGate {

    private static int portNumber ;
    private static String url = "/" + Token.getToken();

    public static void main (String [] args) {
        portNumber = Integer.parseInt(args[0]);

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(portNumber), 0);
            server.createContext(url, new MyHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException f) {
            f.printStackTrace();
        }
    }

    static class MyHandler implements HttpHandler{
        private ControllerController controllerController = new ControllerController();

        @Override
        public void handle(HttpExchange t) throws IOException {

            String response = "hello world!";
            InputStream temp = t.getRequestBody();
            BufferedReader in= new BufferedReader(new InputStreamReader(temp));
            String input = "",temp2 = "";
            while ((temp2 = in.readLine() ) != null ){
                input += temp2;
            }

            t.sendResponseHeaders(200, response.length());
            System.out.println(response);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();

            try {
                JSONObject jsonObject = new JSONObject(input);
                jsonObject = jsonObject.getJSONObject("message");
                String command = jsonObject.getString("text");
                System.out.println(command);
                controllerController.controllerFactory(command,input);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
