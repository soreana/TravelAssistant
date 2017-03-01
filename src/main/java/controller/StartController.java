package controller;

import helper.Messages;
import helper.TelegramMessages;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */
public class StartController extends Controller {

    @Override
    public void incomingMessage(String message) {
        // todo save new user account
        TelegramMessages.sendMessageToUser(TelegramMessages.getChatId(message),
                Messages.getWelcomeMessage());
        TelegramMessages.travelOrCompeteKeyboardToChat(TelegramMessages.getChatId(message));
    }
}
