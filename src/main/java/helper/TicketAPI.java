package helper;

import other.Destination;
import java.util.ArrayList;

/**
 * Created by sinakashipazha on 3/1/2017 AD.
 */
public class TicketAPI {

    public static ArrayList<Destination> getDestinationForThisOrigin(String origin){
        // TODO call to right api

        ArrayList<other.Destination> destinations = new ArrayList<>();
        destinations.add(new other.Destination("قزوین بی بازگشت"));
        destinations.add(new other.Destination("قزوین با بازگشت"));
        destinations.add(new other.Destination("اصفهان"));
        destinations.add(new other.Destination("مشهد"));
        destinations.add(new other.Destination("تهران"));
        destinations.add(new other.Destination("اهواز"));

        return destinations;
    }
}
