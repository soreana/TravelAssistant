package controller;

import helper.TelegramMessages;
import helper.TicketAPI;
import user.User;
import user.UserManager;
import user.UserState;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */
public class TravelController extends Controller {

    @Override
    public void incomingMessage(String message) {
        String userId = TelegramMessages.getUserId(message);
        UserState state = UserManager.getUserState(userId);
        String chatId = TelegramMessages.getChatId(message);

        String origin = null;

        switch (state) {
            case SENT_ORIGIN:
                origin = TelegramMessages.getTextPartOfMessage(message).substring(7);
                UserManager.getUserById(userId)
                        .instantiateNewTravel(origin)
                        .changeStateForward();
                break;
            case SENT_DESTINATION:
                break;
            case SENT_YEAR_OPTIONS:
                break;
            case SENT_MONTH_OPTIONS:
                break;
            case SENT_DAY_OPTIONS:
                break;
            case SENT_DURATION_OPTIONS:
                break;
            case SENT_TRAVEL_TYPE_OPTIONS:
                break;
        }

        state = UserManager.getUserState(userId);

        switch (state) {
            case NOTHING:
                TelegramMessages.sendOriginsListToUser(chatId);
                UserManager.getUserById(userId).changeStateForward();
                break;
            case CHOSEN_ORIGIN:
                TelegramMessages.sendDestinationsListToUser(chatId,
                        TicketAPI.getDestinationForThisOrigin(origin));
                UserManager.getUserById(userId)
                        .changeStateForward();
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
