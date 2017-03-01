package controller;

import helper.Messages;
import helper.TelegramMessages;
import helper.TicketAPI;
import other.Destination;
import user.State;

import java.util.ArrayList;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */
public class TravelController extends Controller {

    @Override
    public void incomingMessage(String message) {
        // todo read state of user
        State state = State.CHOOOSE_ORIGIN;
        String chatId = TelegramMessages.getChatId(message);

        switch (state){
            case CHOOOSE_ORIGIN:
                TelegramMessages.sendMessageToUser(chatId , Messages.getOriginMessage());
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
