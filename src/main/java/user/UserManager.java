package user;

import org.json.JSONObject;

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

    public static void putUser(JSONObject from){
        User user = new User(from);
        map.put(user.getTelegramID(),user);
    }


    public static User getUserById(String id){
        return map.get(id);
    }

    public static UserState getUserState(String id){
        return map.get(id).getState();
    }
}
