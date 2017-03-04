package other;

/**
 * Created by sinakashipazha on 3/3/2017 AD.
 */
public class Travel {
    String origin ;
    String destination;
    private String year;

    public Travel(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setTravelYear(String travelYear) {
        this.year = travelYear;
    }
}
