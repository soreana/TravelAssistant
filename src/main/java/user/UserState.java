package user;

/**
 * Created by sinakashipazha on 3/1/2017 AD.
 */
public enum UserState {
    NOTHING,
    SENT_ORIGIN,
    CHOSEN_ORIGIN,
    SENT_DESTINATION,
    CHOSEN_DESTINATION,
    SENT_YEAR_OPTIONS,
    CHOSEN_YEAR,
    SENT_MONTH_OPTIONS,
    CHOSEN_MONTH,
    SENT_DAY_OPTIONS,
    CHOSEN_DAY,
    SENT_DURATION_OPTIONS,
    CHOSEN_DURATION,
    SENT_TRAVEL_TYPE_OPTIONS,
    CHOSEN_TRAVEL_TYPE;

    public UserState forward() {
        switch (this) {
            case NOTHING:
                return SENT_ORIGIN;
            case SENT_ORIGIN:
                return CHOSEN_ORIGIN;
            case CHOSEN_ORIGIN:
                return SENT_DESTINATION;
            case SENT_DESTINATION:
                return CHOSEN_DESTINATION;
            case CHOSEN_DESTINATION:
                return SENT_YEAR_OPTIONS;
            case SENT_YEAR_OPTIONS:
                return CHOSEN_YEAR;
            case CHOSEN_YEAR:
                return SENT_MONTH_OPTIONS;
            case SENT_MONTH_OPTIONS:
                return CHOSEN_MONTH;
            case CHOSEN_MONTH:
                return SENT_DAY_OPTIONS;
            case SENT_DAY_OPTIONS:
                return CHOSEN_DAY;
            case CHOSEN_DAY:
                return SENT_DURATION_OPTIONS;
            case SENT_DURATION_OPTIONS:
                return CHOSEN_DURATION;
            case CHOSEN_DURATION:
                return SENT_TRAVEL_TYPE_OPTIONS;
            case SENT_TRAVEL_TYPE_OPTIONS:
                return CHOSEN_TRAVEL_TYPE;
            case CHOSEN_TRAVEL_TYPE:
                return NOTHING;
            default:
                return NOTHING;
        }
    }
}
