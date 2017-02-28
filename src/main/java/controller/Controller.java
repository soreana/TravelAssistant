package controller;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */
abstract public class Controller {



    public abstract Controller controllerFactory(String controller, String message);
}
