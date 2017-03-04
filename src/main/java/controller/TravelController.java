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
        User currentUser = UserManager.getUserById(userId);
        UserState state = currentUser.getState();
        String chatId = TelegramMessages.getChatId(message);

        String origin = null, destination = null, year = null, month = null, day = null, duration = null, travelType = null;

        switch (state) {
            case NOTHING:
                TelegramMessages.sendOriginsListToUser(chatId);
                break;
            case SENT_ORIGIN:
                origin = TelegramMessages.getTextPartOfMessage(message).substring(7);
                currentUser.instantiateNewTravel(origin);
                break;
            case SENT_DESTINATION:
                destination = TelegramMessages.getTextPartOfMessage(message).substring(7);
                currentUser.setTravelDestination(destination);
                break;
            case SENT_YEAR_OPTIONS:
                year = TelegramMessages.getTextPartOfMessage(message).substring(7);
                currentUser.setTravelYear(year);
                break;
            case SENT_MONTH_OPTIONS:
                month = TelegramMessages.getTextPartOfMessage(message).substring(7);
                currentUser.setTravelMonth(month);
                break;
            case SENT_DAY_OPTIONS:
                day = TelegramMessages.getTextPartOfMessage(message).substring(7);
                currentUser.setTravelDay(day);
                break;
            case SENT_DURATION_OPTIONS:
                duration = TelegramMessages.getTextPartOfMessage(message).substring(7);
                currentUser.setDurationDay(duration);
                break;
            case SENT_TRAVEL_TYPE_OPTIONS:
                travelType = TelegramMessages.getTextPartOfMessage(message).substring(7);
                currentUser.setTravelType(travelType);
                break;
        }

        state = currentUser.changeStateForward().getState();

        switch (state) {
            case CHOSEN_ORIGIN:
                TelegramMessages.sendDestinationsListToUser(chatId,
                        TicketAPI.getDestinationForThisOrigin(origin));
                UserManager.getUserById(userId)
                        .changeStateForward();
                break;
            case CHOSEN_DESTINATION:
                TelegramMessages.sendYearOptionsToUser(chatId);
                UserManager.getUserById(userId).changeStateForward();
                break;
            case CHOSEN_YEAR:
                TelegramMessages.sendMonthOptions(chatId, year);
                UserManager.getUserById(userId).changeStateForward();
                break;
            case CHOSEN_MONTH:
                TelegramMessages.sendDayOptions(chatId, month, UserManager.getUserById(userId).getTravelYear());
                UserManager.getUserById(userId).changeStateForward();
                break;
            case CHOSEN_DAY:
                TelegramMessages.sendDurationOptions(chatId);
                UserManager.getUserById(userId).changeStateForward();
                break;
            case CHOSEN_DURATION:
                TelegramMessages.sendTravelOptions(chatId);
                UserManager.getUserById(userId).changeStateForward();
                break;
            case CHOSEN_TRAVEL_TYPE:
                // todo get travel from Reza api
                break;
        }
    }
}
