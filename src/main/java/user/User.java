package user;

import org.json.JSONObject;
import other.Travel;

import static user.UserState.NOTHING;

/**
 * Created by sinakashipazha on 2/27/2017 AD.
 */
public class User {
    private String telegramID;
    private String firstName;
    private String lastName;
    private String userName;

    private int score;
    private UserState userState;

    private String userToken;

    private Travel travel;

    public User(JSONObject jsonObject){
        telegramID = String.valueOf(jsonObject.getInt("id"));
        firstName = jsonObject.getString("first_name");
        lastName = jsonObject.getString("last_name");
        userName = jsonObject.getString("username");

        score = 0;
        userState = NOTHING;

        // todo generate user token
    }

    public UserState getState() {
        return userState;
    }

    public String getTelegramID() {
        return telegramID;
    }

    public User changeStateForward() {
        userState = userState.forward();
        return this;
    }

    public User instantiateNewTravel(String origin) {
        travel = new Travel(origin);
        return this;
    }

    public void resetState() {
        userState = NOTHING;
        travel = null;
    }

    public User setTravelDestination(String travelDestination) {
        travel.setDestination(travelDestination);
        return this;
    }

    public User setTravelYear(String travelYear) {
        travel.setTravelYear(travelYear);
        return this;
    }

    public User setTravelMonth(String travelMonth) {
        this.travel.setMonth(travelMonth);
        return this;
    }

    public User setTravelDay(String day) {
        this.travel.setDay(day);
        return this;
    }

    public String getTravelYear() {
        return travel.getYear();
    }

    public User setDurationDay(String durationDay) {
        this.travel.setDuration(durationDay);
        return this;
    }

    public User setTravelType(String travelType) {
        this.travel.setTravelType( travelType);
        return this;
    }

    public Travel getTravel() {
        return travel;
    }
}
