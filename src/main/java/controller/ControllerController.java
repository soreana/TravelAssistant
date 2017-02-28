package controller;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */

public class ControllerController {

    public void controllerFactory(String controller, String message) {
        Controller result = null;
        switch (controller){
            case "/start":
                result = new StartController();
                break;
            case "/travel":
                result = new TravelController();
                break;
            case "/compete":
                result = new CompeteController();
                break;
            default:
                result = new DefaultController();
        }
        result.incomingMessage(message);
    }
}