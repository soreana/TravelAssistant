package controller;

import helper.Messages;
import helper.TelegramMessages;
import user.UserManager;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */

public class DefaultController extends Controller {

    @Override
    public void incomingMessage(String message) {
        UserManager.getUserById(TelegramMessages.getUserId(message)).resetState();
        TelegramMessages.sendMessageToUser(TelegramMessages.getChatId(message),
                Messages.getWelcomeMessage());
        TelegramMessages.travelOrCompeteKeyboardToChat(TelegramMessages.getChatId(message));
    }
}
