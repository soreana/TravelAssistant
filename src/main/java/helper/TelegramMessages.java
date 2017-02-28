package helper;

import org.json.JSONObject;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */
public class TelegramMessages {
    public static void sendMessageToUser(String chatId, String message) {

    }

    public static String getChatId(String message) {
        JSONObject jsonObject = new JSONObject(message);
        jsonObject = jsonObject.getJSONObject("message");
        jsonObject = jsonObject.getJSONObject("chat");
        return jsonObject.getString("id");
    }
}
