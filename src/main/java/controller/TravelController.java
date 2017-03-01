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
        UserState state = UserManager.getUserState(TelegramMessages.getUserId(message)) ;
        String chatId = TelegramMessages.getChatId(message);

        switch (state){
            case NOTHING:
                // todo ask for origin
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
