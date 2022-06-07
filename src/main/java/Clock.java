public class Clock {
    private static int TOTAL_HOURS_LIMIT = 24, TOTAL_MINUTES_LIMIT = 60;
    private int hour, minute;
    public Clock(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
        updateTime();
    }

    public void add(int minutes) {
        minute += minutes;
        updateTime();
    }
    private void updateTime() {
        if(minute >= TOTAL_MINUTES_LIMIT) {
            int exactMinutes = minute % TOTAL_MINUTES_LIMIT;
            int exactHours = (minute - exactMinutes) / TOTAL_MINUTES_LIMIT;
            hour += exactHours;
            minute = exactMinutes;
        } else if (minute < 0) {
            minute = Math.abs(minute);
            int exactMinutes = minute % TOTAL_MINUTES_LIMIT;
            //if -4 so it needs - 1 an hour and minutes becomes 54 but min = 0, +1 shouldn't be
            int exactHours = (exactMinutes != 0 ? 1 : 0) + (minute - exactMinutes) / TOTAL_MINUTES_LIMIT;
            hour -= exactHours;
            minute = exactMinutes == 0 ? 0 : TOTAL_MINUTES_LIMIT - exactMinutes;
        }
        if(hour >= TOTAL_HOURS_LIMIT) {
            hour = hour % TOTAL_HOURS_LIMIT;
        } else if (hour < 0) {
            do {
                hour += TOTAL_HOURS_LIMIT;
            } while (hour < 0);
        }
    }
    @Override
    public boolean equals(Object obj) {
        Clock clockTwo = (Clock) obj;
        return hour == clockTwo.hour && minute == clockTwo.minute;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d", hour));
        sb.append(":");
        sb.append(String.format("%02d",minute));
        return sb.toString();
    }


}