package user;

import org.json.JSONObject;
import other.Travel;

import static user.UserState.*;

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
        switch (userState) {
            case NOTHING:
                userState =  SENT_ORIGIN;
            case SENT_ORIGIN:
                userState = CHOSEN_ORIGIN;
            case CHOSEN_ORIGIN:
                userState = SENT_DESTINATION;
            case SENT_DESTINATION:
                userState = CHOSEN_DESTINATION;
            case CHOSEN_DESTINATION:
                userState = SENT_YEAR_OPTIONS;
            case SENT_YEAR_OPTIONS:
                userState = CHOSEN_YEAR;
            case CHOSEN_YEAR:
                userState = SENT_MONTH_OPTIONS;
            case SENT_MONTH_OPTIONS:
                userState = CHOSEN_MONTH;
            case CHOSEN_MONTH:
                userState = SENT_DAY_OPTIONS;
            case SENT_DAY_OPTIONS:
                userState = CHOSEN_DAY;
            case CHOSEN_DAY:
                userState = SENT_DURATION_OPTIONS;
            case SENT_DURATION_OPTIONS:
                userState = CHOSEN_DURATION;
            case CHOSEN_DURATION:
                userState = SENT_TRAVEL_TYPE_OPTIONS;
            case SENT_TRAVEL_TYPE_OPTIONS:
                userState = CHOSEN_TRAVEL_TYPE;
            case CHOSEN_TRAVEL_TYPE:
                userState = NOTHING;
            default:
                userState = NOTHING;
        }

        return this;
    }

    public User instantiateNewTravel(String origin) {
        travel = new Travel(origin);
        return this;
    }
}
