package user;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sinakashipazha on 3/1/2017 AD.
 */
public class UserManager {
    private static UserManager myInstance = new UserManager();
    private static Map<String,User> map = new HashMap<>();

    private UserManager(){

    }

    public static UserManager getMyInstance(){
        return myInstance;
    }

    private static void putUser(User user){
        map.put(user.getUserTelegramID(),user);
    }


    public static User getUserById(String id){
        return map.get(id);
    }

    public static UserState getUserState(String id){
        return map.get(id).getState();
    }
}
