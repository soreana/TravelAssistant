package controller;

import helper.TelegramMessages;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */

public class DefaultController extends Controller {

    @Override
    public void incomingMessage(String message) {
        // todo reset user state
        TelegramMessages.sendMessageToUser(TelegramMessages.getChatId(message),
                "چه کاری از دستم براتون بر میاد؟");
        // todo set keyboard
    }
}
