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
            case "سفر":
            case "/travel":
                result = new TravelController();
                break;
            case "مسابقه":
            case "/compete":
                result = new CompeteController();
                break;
            default:
                result = new DefaultController();
        }
        result.incomingMessage(message);
    }
}