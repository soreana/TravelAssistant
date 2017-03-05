package controller;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */

public class ControllerController {

    public void controllerFactory(String command, String message) {
        if(command.contains("travel_")){
            new TravelController().incomingMessage(message);
            return;
        }

        Controller result ;
        switch (command){
            case "/start":
                result = new StartController();
                break;
            case "سفر":
            case "/travel":
                result = new TravelController(message);
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