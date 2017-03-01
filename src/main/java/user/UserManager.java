package user;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sinakashipazha on 3/1/2017 AD.
 */
public class UserManager {
    private static UserManager myInstance = new UserManager();

    private UserManager(){

    }

    public static UserManager getMyInstance(){
        return myInstance;
    }

    private static Map<String,User> map = new HashMap<>();

}
