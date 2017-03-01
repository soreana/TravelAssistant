package user;

/**
 * Created by sinakashipazha on 2/27/2017 AD.
 */
public class User {
    private String userTelegramID;
    private int score;
    private String userToken;
    private UserState userState;

    public UserState getState() {
        return userState;
    }

    public String getUserTelegramID() {
        return userTelegramID;
    }
}
