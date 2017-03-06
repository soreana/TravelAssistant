package controller;

import helper.Messages;
import helper.TelegramMessages;
import helper.TicketAPI;
import user.CannotRestoreException;
import user.User;
import user.UserManager;
import user.UserState;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */
public class TravelController extends Controller {
    public TravelController(){}

    public TravelController(String message) {
        String userId = TelegramMessages.getUserId(message);
        User currentUser = UserManager.getUserById(userId);
        currentUser.resetState();
    }


    @Override
    public void incomingMessage(String message) {
        String userId = TelegramMessages.getUserId(message);
        User currentUser = UserManager.getUserById(userId);
        String chatId = TelegramMessages.getChatId(message);
        String messageText = TelegramMessages.getTextPartOfMessage(message);
        String messageType = TelegramMessages.getTypeOfMessage(messageText);
        UserState messageState = UserState.getStateOfThisType(messageType);

        try {
            currentUser.restoreUserStateToThisState(messageState);
        } catch (CannotRestoreException e) {
            TelegramMessages.sendMessageToUser(chatId, Messages.getUserRestorationFailure());
        }

        UserState state = currentUser.getState();
        String messageBody = TelegramMessages.getMessageBody(messageText);

        String origin = null, destination = null, year = null, month = null, day = null, duration = null, travelType = null;

        System.out.println(state + " " + currentUser);

        switch (state) {
            case NOTHING:
                TelegramMessages.sendOriginsListToUser(chatId);
                break;
            case SENT_ORIGIN:
                origin = messageBody;
                currentUser.instantiateNewTravel(origin);
                System.out.println(origin);
                break;
            case SENT_DESTINATION:
                destination = messageBody;
                currentUser.setTravelDestination(destination);
                break;
            case SENT_YEAR_OPTIONS:
                year = messageBody;
                currentUser.setTravelYear(year);
                break;
            case SENT_MONTH_OPTIONS:
                month = messageBody;
                currentUser.setTravelMonth(month);
                break;
            case SENT_DAY_OPTIONS:
                day = messageBody;
                currentUser.setTravelDay(day);
                break;
            case SENT_DURATION_OPTIONS:
                duration = messageBody;
                currentUser.setDurationDay(duration);
                break;
            case SENT_TRAVEL_VEHICLE_OPTIONS:
                travelType = messageBody;
                currentUser.setTravelType(travelType);
                break;
        }

        state = currentUser.changeStateForward().getState();
        System.out.println(state);

        switch (state) {
            case CHOSEN_ORIGIN:
                TelegramMessages.sendDestinationsListToUser(chatId,
                        TicketAPI.getDestinationForThisOrigin(origin));
                break;
            case CHOSEN_DESTINATION:
                TelegramMessages.sendYearOptionsToUser(chatId);
                break;
            case CHOSEN_YEAR:
                TelegramMessages.sendMonthOptions(chatId, year);
                break;
            case CHOSEN_MONTH:
                TelegramMessages.sendDayOptions(chatId, month, currentUser.getTravelYear());
                break;
            case CHOSEN_DAY:
                TelegramMessages.sendDurationOptions(chatId);
                break;
            case CHOSEN_DURATION:
                TelegramMessages.sendTravelOptions(chatId);
                break;
            case CHOSEN_TRAVEL_VEHICLE:
                // todo get travel from Reza api
                break;
        }

        if (state != UserState.SENT_ORIGIN)
            currentUser.changeStateForward();

        if (currentUser.getTravel() != null)
            TelegramMessages.sendMessageToUser(chatId, currentUser.getTravel().toString());
    }
}
