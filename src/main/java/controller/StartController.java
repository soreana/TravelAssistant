package controller;

import helper.Messages;
import helper.TelegramMessages;
import org.json.JSONObject;
import user.UserManager;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */
public class StartController extends Controller {

    private JSONObject getFromPartOfMessage(String message){
        return new JSONObject(message)
                .getJSONObject("message")
                .getJSONObject("from");
    }

    @Override
    public void incomingMessage(String message) {
        UserManager.putUser(getFromPartOfMessage(message));
        TelegramMessages.sendMessageToUser(TelegramMessages.getChatId(message),
                Messages.getWelcomeMessage());
        TelegramMessages.travelOrCompeteKeyboardToChat(TelegramMessages.getChatId(message));
    }
}
