package other;

import helper.MonthMapper;
import helper.OriginMapper;
import helper.PersianNumber;

import java.lang.reflect.Field;

/**
 * Created by sinakashipazha on 3/3/2017 AD.
 */
public class Travel {
    String origin;
    String destination;
    private String year;
    private String month;
    private String day;
    private String duration;
    private String travelType;

    public Travel(String origin) {
        this.origin = OriginMapper.getNameForAbbreviate(origin);
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setTravelYear(String travelYear) {
        this.year = travelYear;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    private String mapFieldToPersianName(String fieldName) {
        switch (fieldName) {
            case "origin":
                return "مبدا";
            case "destination":
                return "مقصد";
            case "year":
                return "سال";
            case "month":
                return "ماه";
            case "day":
                return "روز";
            case "duration":
                return "مدت سفر";
            case "travelType":
                return "وسیله نقلیه";
            default:
                return "";
        }
    }

    private String convertTravelTypeToPersian(String value) {
        return value.replace("bus", "اتوبوس").replace("airplane", "هواپیما").replace("train", "قطار");
    }

    @Override
    public String toString() {
        Class cl = this.getClass();
        Field[] fields = cl.getDeclaredFields();
        String result = "";

        try {
            for (Field current : fields) {
                if (current.get(this) != null) {
                    if ("year".equals(current.getName()))
                        result += " تاریخ";
                    result += " " + mapFieldToPersianName(current.getName()) + ": ";
                    if ("month".equals(current.getName()))
                        result += MonthMapper.getPersianMounth((String) current.get(this));
                    else
                        result += current.get(this);
                    result += "\n";
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return convertTravelTypeToPersian(PersianNumber.toArabic(result));
    }

    public static void main(String[] args) {
        Travel temp = new Travel("مشهد");
        temp.setDestination("تهران");
        temp.setTravelYear("1395");
        temp.setMonth("esfand");
        temp.setDay("12");
        temp.setDuration("4");
        temp.setTravelType("bus");
        System.out.println(temp);
    }
}
