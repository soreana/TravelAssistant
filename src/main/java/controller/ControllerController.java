package controller;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */

public class ControllerController extends Controller{

    @Override
    public Controller controllerFactory(String controller, String message) {
        switch (controller){
            case "/start":
            case "/travel":
            case "/match":
            case "/compete":
            default:

        }
        return null;
    }
}