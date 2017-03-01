package controller;

import helper.Messages;
import helper.TelegramMessages;
import helper.TicketAPI;
import other.Destination;
import user.UserManager;
import user.UserState;

import java.util.ArrayList;

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
                break;
            case CHOSEN_ORIGIN:
                break;
            case CHOSEN_DESTINATION:
                break;
            case CHOSEN_YEAR:
                break;
            case CHOSEN_MONTH:
                break;
            case CHOSEN_DAY:
                break;
            case CHOSEN_DURATION:
                break;
            case CHOSEN_TRAVEL_TYPE:
                break;
        }

        // todo read origin
        String origin = "تهران" ;

        // TODO send origin to REZA and get a list of destinations.
        ArrayList<Destination> destinations = TicketAPI.getDestinationForThisOrigin(origin);
        TelegramMessages.destinationListKeyboardToChat(chatId , destinations);

        // todo send message to choose date


        TelegramMessages.sendMessageToUser(chatId , Messages.getDurationMessage());

        TelegramMessages.travelModeKeyboardToChat(chatId);

        // TODO get the traveltype that user wants, from REZA's API :\

        // todo show list : should be a sendmessage method inorder to make result text forwarding possible
        // todo send link
    }

    public void sendMessage(String message){

    }
}
