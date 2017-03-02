package controller;

import helper.TelegramMessages;
import user.UserManager;
import user.UserState;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */
public class TravelController extends Controller {

    @Override
    public void incomingMessage(String message) {
        String userId = TelegramMessages.getUserId(message);
        UserState state = UserManager.getUserState(userId) ;
        String chatId = TelegramMessages.getChatId(message);

        switch (state){
            case NOTHING:
                TelegramMessages.sendOriginListToUser(chatId);
                UserManager.getUserById(userId).changeStateForward();
                break;
            case CHOSEN_ORIGIN:
                // todo ask for destination
                break;
            case CHOSEN_DESTINATION:
                // todo ask for year
                break;
            case CHOSEN_YEAR:
                // todo ask for month
                break;
            case CHOSEN_MONTH:
                // todo ask for day
                break;
            case CHOSEN_DAY:
                // todo ask for duration
                break;
            case CHOSEN_DURATION:
                // todo ask for travel type
                break;
            case CHOSEN_TRAVEL_TYPE:
                break;
        }
    }
}
