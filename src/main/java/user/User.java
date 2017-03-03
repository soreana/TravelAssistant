package user;

import org.json.JSONObject;
import other.Travel;

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
        userState = UserState.NOTHING;

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
}
