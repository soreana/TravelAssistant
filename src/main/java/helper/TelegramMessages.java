package helper;

import botrestapi.Token;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import other.Destination;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */
public class TelegramMessages {
    private static String getUrlForMethod(String methodName) {
        return "https://api.telegram.org/bot" + Token.getToken() + "/" + methodName;
    }

    public static void travelOrCompeteKeyboardToChat(String chatId) {
        JSONObject jsonObject = getJsonKeyboardOfBotFunctions(chatId);
        httpsPostRequestSendMessage(jsonObject);
    }

    private static JSONObject getJsonKeyboardOfBotFunctions(String chatId){
        JSONObject jsonObject = createRequestJsonObject(chatId,Messages.getMenuMessage());
        JSONArray mainArray = new JSONArray();
        JSONArray innerArray = new JSONArray();

        innerArray.put(new JSONObject("{\"text\":\"مسابقه\"}"));
        innerArray.put(new JSONObject("{\"text\":\"سفر\"}"));

        mainArray.put(innerArray);

        JSONObject replyMarkup = new JSONObject();
        replyMarkup.put("keyboard", mainArray);
        replyMarkup.put("resize_keyboard",true);

        jsonObject.put("reply_markup",replyMarkup);

        return jsonObject;
    }

    public static void destinationListKeyboardToChat(String chatId, ArrayList<Destination> destinations) {
        JSONObject jsonObject = getJsonKeyboardOfDestination(chatId,destinations);
        httpsPostRequestSendMessage(jsonObject);
    }

    private static JSONObject getJsonKeyboardOfDestination(String chatId,ArrayList<Destination> destinations){
        JSONObject jsonObject = createRequestJsonObject(chatId, Messages.getChooseDestinationMessage());

        // TODO handle all destinations

        JSONArray mainArray = new JSONArray();

        mainArray.put(createArrayOfButton("بیش‌تر..."));

        for (int i = 0; i < 5; i++) {
            mainArray.put(createArrayOfButton(destinations.get(i).toString()));
        }

        JSONObject inlineKeyboard = new JSONObject();
        inlineKeyboard.put("inline_keyboard",mainArray);

        jsonObject.put("reply_markup", inlineKeyboard);

        return jsonObject;
    }


    private static JSONArray createArrayOfButton(String text){
        JSONArray temp = new JSONArray();
        temp.put(createButton(text));
        return temp;
    }

    private static JSONObject createButton(String text) {
        JSONObject temp = new JSONObject();
        temp.put("text", text);
        temp.put("callback_data", text);
        return temp;
    }

    public static JSONObject createRequestJsonObject(String chatId, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("chat_id", chatId);
        jsonObject.put("text", message);
        return jsonObject;
    }

    public static void httpsPostRequestSendMessage(JSONObject jsonObject) {
        try {
            URL url = new URL(getUrlForMethod("sendMessage"));
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setDoOutput(true);
            // no idea what is happening in this line :)
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("POST");
            con.connect();

            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");

            osw.write(jsonObject.toString());
            osw.flush();
            osw.close();

            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageToUser(String chatId, String message) {
        httpsPostRequestSendMessage(createRequestJsonObject(chatId, message));
    }

    public static String getChatId(String message) {
        JSONObject jsonObject = new JSONObject(message);
        jsonObject = jsonObject.getJSONObject("message");
        jsonObject = jsonObject.getJSONObject("chat");
        return String.valueOf(jsonObject.getInt("id"));
    }

    public static String getUserId(String message){
        return new JSONObject(message)
                .getJSONObject("message")
                .getJSONObject("from")
                .getString("id");
    }

    public static void travelModeKeyboardToChat(String chatId){
        JSONObject jsonObject = getJsonKeyboardOfTravelMode(chatId);
        httpsPostRequestSendMessage(jsonObject);

    }

    private static JSONObject getJsonKeyboardOfTravelMode(String chatId) {
        JSONObject jsonObject = createRequestJsonObject(chatId,Messages.getTravelModeMessage());
        JSONArray mainArray = new JSONArray();
        JSONArray innerArray = new JSONArray();

        innerArray.put(new JSONObject("{\"text\":\"هواپیما\"}"));
        innerArray.put(new JSONObject("{\"text\":\"قطار\"}"));
        innerArray.put(new JSONObject("{\"text\":\"اتوبوس\"}"));

        mainArray.put(innerArray);

        JSONObject replyMarkup = new JSONObject();
        replyMarkup.put("keyboard", mainArray);
        replyMarkup.put("resize_keyboard",true);

        jsonObject.put("reply_markup",replyMarkup);

        return jsonObject;
    }

    public static void main(String[] args) {
        sendMessageToUser(String.valueOf(82662030), "سلام");
        ArrayList<Destination> destinations = new ArrayList<>();
        destinations.add(new Destination("قزوین بی بازگشت"));
        destinations.add(new Destination("قزوین با بازگشت"));
        destinations.add(new Destination("اصفهان"));
        destinations.add(new Destination("مشهد"));
        destinations.add(new Destination("تهران"));
        destinations.add(new Destination("اهواز"));
        destinationListKeyboardToChat(String.valueOf(82662030),destinations);

        travelOrCompeteKeyboardToChat(String.valueOf(82662030));
    }
}
