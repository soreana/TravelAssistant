package helper;

/**
 * Created by sinakashipazha on 3/1/2017 AD.
 */
public class Messages {
    private static final String welcomeMessage = "چه کاری از دستم براتون بر میاد؟" ;
    private static final String chooseDestinationMessage = "لطفا مقصد خود را انتخاب کنید." ;
    private static final String menuMessage = "لطفا از بین کلید واژه‌های زیر یکی را انتخاب کنيد." ;
    private static final String originMessage = "لطفا مبدا خود را انتخاب کنید.";
    private static final String durationMessage = "لطفا مدت سفر خود را انتخاب کنید.";
    private static final String travelModeMessage = "لطفا روش سفر خود را انتخاب کنید.";
    private static final String yearDateMessage= "لطفا تاریخ مورد نظر خود را انتخاب کنید.";
    private static final String monthDateMessage = "لطفا ماه سفر خود را انتخاب کنید.";
    private static final String dayDateMessage = "لطفا روز سفر خود را انتخاب کنید.";
    private static final String resultListMessage = "نتایج جست و جو:";
    private static final String paymentMessage = "لینک پرداخت";
    private static final String userRestorationFailureMessage = "کلید فشرده شده مورد قبول نیست.";


    public static String getWelcomeMessage(){
        return welcomeMessage;
    }

    public static String getChooseDestinationMessage() {
        return chooseDestinationMessage;
    }

    public static String getMenuMessage() {
        return menuMessage;
    }

    public static String getOriginMessage() {
        return originMessage;
    }

    public static String getDurationMessage() {
        return durationMessage;
    }

    public static String getTravelModeMessage() {
        return travelModeMessage;
    }

    public static String getYearDateMessage() {
        return yearDateMessage;
    }

    public static String getMonthDateMessage() {
        return monthDateMessage;
    }

    public static String getDayDateMessage() {
        return dayDateMessage;
    }

    public static String getResultListMessage() {
        return resultListMessage;
    }

    public static String getPaymentMessage() {
        return paymentMessage;
    }

    public static String getUserRestorationFailure() {
        return userRestorationFailureMessage;
    }
}
