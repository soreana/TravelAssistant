package helper;

import botrestapi.Token;
import com.ghasemkiani.util.PersianCalendarHelper;
import com.ghasemkiani.util.icu.PersianCalendar;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

import other.Destination;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sinakashipazha on 2/28/2017 AD.
 */
public class TelegramMessages {
    private static String getUrlForMethod(String methodName) {
        return "https://api.telegram.org/bot" + Token.getToken() + "/" + methodName;
    }

    public static void travelOrCompeteKeyboardToChat(String chatId) {
        JSONObject jsonObject = getJsonKeyboardOfBotFunctions(chatId);
        httpsPostRequestSendMessage(jsonObject);
    }

    private static JSONObject getJsonKeyboardOfBotFunctions(String chatId) {
        JSONObject jsonObject = createRequestJsonObject(chatId, Messages.getMenuMessage());
        JSONArray mainArray = new JSONArray();
        JSONArray innerArray = new JSONArray();

        innerArray.put(new JSONObject("{\"text\":\"مسابقه\"}"));
        innerArray.put(new JSONObject("{\"text\":\"سفر\"}"));

        mainArray.put(innerArray);

        JSONObject replyMarkup = new JSONObject();
        replyMarkup.put("keyboard", mainArray);
        replyMarkup.put("resize_keyboard", true);

        jsonObject.put("reply_markup", replyMarkup);

        return jsonObject;
    }

    public static void sendDestinationsListToUser(String chatId, ArrayList<Destination> destinations) {
        JSONObject jsonObject = getJsonKeyboardOfDestination(chatId, destinations);
        httpsPostRequestSendMessage(jsonObject);
    }

    private static JSONObject getJsonKeyboardOfDestination(String chatId, ArrayList<Destination> destinations) {
        JSONObject jsonObject = createRequestJsonObject(chatId, Messages.getChooseDestinationMessage());

        // TODO handle all destinations

        JSONArray mainArray = new JSONArray();

        mainArray.put(createArrayOfButton("بیش‌تر...", "travel_more_destination"));

        for (int i = 0; i < 5; i++) {
            mainArray.put(createArrayOfButton(destinations.get(i).toString()));
        }

        JSONObject inlineKeyboard = new JSONObject();
        inlineKeyboard.put("inline_keyboard", mainArray);

        jsonObject.put("reply_markup", inlineKeyboard);

        return jsonObject;
    }


    private static JSONArray createArrayOfButton(String text) {
        return createArrayOfButton(text, text);
    }

    private static JSONArray createArrayOfButton(String text, String callback_date) {
        JSONArray temp = new JSONArray();
        temp.put(createButton(text, "travel_" + callback_date));
        return temp;
    }

    private static JSONObject createButton(String text, String callbackData) {
        return new JSONObject()
                .put("text", text)
                .put("callback_data", callbackData);
    }

    private static JSONObject createButton(String text) {
        return createButton(text, text);
    }

    public static JSONObject createRequestJsonObject(String chatId, String message) {
        return new JSONObject()
                .put("chat_id", chatId)
                .put("text", message);
    }

    public static void httpsPostRequestSendMessage(JSONObject jsonObject) {
        try {
            URL url = new URL(getUrlForMethod("sendMessage"));
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setDoOutput(true);
            // no idea what is happening in this line :)
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("POST");
            con.connect();

            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");

            osw.write(jsonObject.toString());
            osw.flush();
            osw.close();

            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageToUser(String chatId, String message) {
        httpsPostRequestSendMessage(createRequestJsonObject(chatId, message));
    }

    public static String getChatId(String message) {
        return String.valueOf(new JSONObject(message)
                .getJSONObject("message")
                .getJSONObject("chat")
                .getInt("id"));
    }

    public static String getUserId(String message) {
        return String.valueOf(new JSONObject(message)
                .getJSONObject("message")
                .getJSONObject("from")
                .getInt("id"));
    }

    public static void travelModeKeyboardToChat(String chatId) {
        JSONObject jsonObject = getJsonKeyboardOfTravelMode(chatId);
        httpsPostRequestSendMessage(jsonObject);

    }

    private static JSONObject getJsonKeyboardOfTravelMode(String chatId) {
        JSONObject jsonObject = createRequestJsonObject(chatId, Messages.getTravelModeMessage());
        JSONArray mainArray = new JSONArray();
        JSONArray innerArray = new JSONArray();

        innerArray.put(new JSONObject("{\"text\":\"هواپیما\"}"));
        innerArray.put(new JSONObject("{\"text\":\"قطار\"}"));
        innerArray.put(new JSONObject("{\"text\":\"اتوبوس\"}"));

        mainArray.put(innerArray);

        JSONObject replyMarkup = new JSONObject();
        replyMarkup.put("keyboard", mainArray);
        replyMarkup.put("resize_keyboard", true);

        jsonObject.put("reply_markup", replyMarkup);

        return jsonObject;
    }

    private static JSONArray createOriginKeyboard() {
        Iterator<String> iterator = OriginMapper.getKeySet().iterator();

        JSONArray mainArray = new JSONArray();
        while (iterator.hasNext()) {
            JSONArray innerArray = new JSONArray();
            for (int i = 0; i < 3 && iterator.hasNext(); i++) {
                String originAbbreviate = iterator.next();
                innerArray.put(createButton(
                        OriginMapper.getNameForAbbreviate(originAbbreviate),
                        "travel_" + originAbbreviate));
            }

            mainArray.put(innerArray);
        }

        return mainArray;
    }

    public static void sendOriginsListToUser(String chatId) {
        JSONObject jsonObject = createRequestJsonObject(chatId, Messages.getOriginMessage());

        JSONObject replyMarkup = new JSONObject();

        replyMarkup.put("inline_keyboard", createOriginKeyboard());
        jsonObject.put("reply_markup", replyMarkup);

        httpsPostRequestSendMessage(jsonObject);
    }

    public static String getTextPartOfMessage(String message) {
        return new JSONObject(message)
                .getJSONObject("message")
                .getString("text");
    }

    private static JSONArray createYearKeyboard() {
        JSONArray mainArray = new JSONArray();

        JSONArray innerArray = new JSONArray();

        innerArray.put(createButton("۱۳۹۵", "travel_1395"));
        innerArray.put(createButton("۱۳۹۶", "travel_1396"));

        mainArray.put(innerArray);

        return mainArray;
    }

    public static void sendYearOptionsToUser(String chatId) {
        JSONObject jsonObject = createRequestJsonObject(chatId, Messages.getYearDateMessage());

        JSONObject replyMarkup = new JSONObject();
        replyMarkup.put("inline_keyboard", createYearKeyboard());

        jsonObject.put("reply_markup", replyMarkup);
        httpsPostRequestSendMessage(jsonObject);
    }

    private static JSONArray createMonthKeyboard(String year) {

        // todo clean up this code
        JSONArray mainArray = new JSONArray();

        JSONArray innerArray = new JSONArray();

        if ("1395".equals(year)) {
            innerArray.put(createButton("اسفند", "travel_esfand"));
            return mainArray;
        }

        innerArray.put(createButton("خرداد", "travel_khordad"));
        innerArray.put(createButton("اردیبهشت", "travel_ordibehesht"));
        innerArray.put(createButton("فروردین", "travel_farvardin"));

        mainArray.put(innerArray);

        innerArray = new JSONArray();

        innerArray.put(createButton("شهریور", "travel_shahrivar"));
        innerArray.put(createButton("مرداد", "travel_mordad"));
        innerArray.put(createButton("تیر", "travel_tir"));

        mainArray.put(innerArray);

        innerArray = new JSONArray();

        innerArray.put(createButton("آذر", "travel_azar"));
        innerArray.put(createButton("آبان", "travel_aban"));
        innerArray.put(createButton("مهر", "travel_mehr"));

        mainArray.put(innerArray);

        innerArray = new JSONArray();

        innerArray.put(createButton("اسفند", "travel_esfand"));
        innerArray.put(createButton("بهمن", "travel_bahman"));
        innerArray.put(createButton("دی", "travel_dey"));

        mainArray.put(innerArray);

        return mainArray;
    }

    public static void sendMonthOptions(String chatId, String year) {
        JSONObject jsonObject = createRequestJsonObject(chatId, Messages.getMonthDateMessage());

        JSONObject replyMarkup = new JSONObject();
        replyMarkup.put("inline_keyboard", createMonthKeyboard(year));

        jsonObject.put("reply_markup", replyMarkup);
        httpsPostRequestSendMessage(jsonObject);
    }

    private static JSONArray createDayKeyboard(String month, String year) {
        JSONArray mainArray = new JSONArray();
        JSONArray innerArray;

        for (int i = 1; i < 30;) {
            innerArray = new JSONArray();
            for (int j=0; j < 5 && i<30; j++) {
                innerArray.put(createButton(String.valueOf(i), "travel_" + i));
                i++;
            }
            mainArray.put(innerArray);
        }

        if (PersianCalendarHelper.isLeapYear(Long.parseLong(year)) || ! "esfand".equals(month))
            ((JSONArray)mainArray.get(mainArray.length() - 1))
                    .put(createButton(String.valueOf(30),"travel_" + 30));


        if("khordad".equals(month) || "ordibehesht".equals(month) || "farvardin".equals(month) ||
                "tir".equals(month) || "mordad".equals(month) || "shahrivar".equals(month)){
            innerArray = new JSONArray().put(createButton(String.valueOf(31),"travel_" + 31));
            mainArray.put(innerArray);
        }

        return mainArray;
    }

    public static void sendDayOptions(String chatId, String month, String year) {
        JSONObject jsonObject = createRequestJsonObject(chatId, Messages.getDayDateMessage());

        JSONObject replyMarkup = new JSONObject();
        replyMarkup.put("inline_keyboard", createDayKeyboard(month, year));

        jsonObject.put("reply_markup", replyMarkup);
        httpsPostRequestSendMessage(jsonObject);
    }


    public static void main(String[] args) {
//        sendOriginsListToUser(String.valueOf(85036220));
//        sendYearOptionsToUser(String.valueOf(85036220));
        sendDayOptions(String.valueOf(85036220),"khordad","1396");
//        sendMessageToUser(String.valueOf(82662030), "سلام");
//        ArrayList<Destination> destinations = new ArrayList<>();
//        destinations.add(new Destination("قزوین بی بازگشت"));
//        destinations.add(new Destination("قزوین با بازگشت"));
//        destinations.add(new Destination("اصفهان"));
//        destinations.add(new Destination("مشهد"));
//        destinations.add(new Destination("تهران"));
//        destinations.add(new Destination("اهواز"));
//        destinationListKeyboardToChat(String.valueOf(82662030),destinations);
//
//        travelOrCompeteKeyboardToChat(String.valueOf(82662030));
    }

}
