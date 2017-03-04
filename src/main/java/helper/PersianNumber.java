package helper;

/**
 * Created by sinakashipazha on 3/4/2017 AD.
 */
public class PersianNumber {
    public static String toArabic(String value){
        String newValue = value.replace("1", "١").replace("2", "٢").replace("3", "٣").replace("4", "٤").replace("5", "٥")
                .replace("6", "٦").replace("7", "٧").replace("8", "٨").replace("9", "٩").replace("0", "٠");

        return newValue;
    }
}
