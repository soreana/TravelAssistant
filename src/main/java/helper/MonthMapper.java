package helper;

/**
 * Created by sinakashipazha on 3/4/2017 AD.
 */
public class MonthMapper {
    public static String getPersianMounth(String value){
        switch (value){
            case "farvardin":
                return "فروردین";
            case "ordibehesht":
                return "اردیبهشت";
            case "khordad":
                return "خرداد";
            case "tir":
                return "تیر";
            case "mordad":
                return "مرداد";
            case "shahrivar":
                return "شهریور";
            case "mehr":
                return "مهر";
            case "aban":
                return "آبان";
            case "azar":
                return "آذر";
            case "dey":
                return "دی";
            case "bahman":
                return "بهمن";
            case "esfand":
                return "اسفند";
            default:
                return "";
        }
    }
}
