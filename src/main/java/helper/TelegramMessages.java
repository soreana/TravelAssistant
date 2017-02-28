package helper;

import botrestapi.Token;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */
public class TelegramMessages {
    private static String getUrlForMethod(String methodName){
        return "https://api.telegram.org/bot"+ Token.getToken() +"/" + methodName;
    }

    public static void sendMessageToUser(String chatId, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("chat_id",chatId);
        jsonObject.put("text",message);

        try {
            URL url = new URL(getUrlForMethod("sendMessage"));
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty( "Content-Type", "application/json" );
            con.setRequestMethod("POST");
            con.connect();

            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");

            osw.write(jsonObject.toString());
            osw.flush();
            osw.close();

            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader( con.getInputStream(),"utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getChatId(String message) {
        JSONObject jsonObject = new JSONObject(message);
        jsonObject = jsonObject.getJSONObject("message");
        jsonObject = jsonObject.getJSONObject("chat");
        return jsonObject.getString("id");
    }

    public static void main(String[] args){
        sendMessageToUser(String.valueOf(82662030),"سلام");
    }
}
