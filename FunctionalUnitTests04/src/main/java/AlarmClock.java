public class AlarmClock {

    // Given a day of the week encoded as
    // 0=Sun, 1=Mon, 2=Tue, ...6=Sat, and a boolean indicating
    // if we are on vacation, return a String of the form "7:00"
    // indicating when the alarm clock should ring. Weekdays, the
    // alarm should be "7:00" and on the weekend it should be "10:00".
    // Unless we are on vacation -- then on weekdays it should be
    // â€œ10:00" and weekends it should be "off".
    //
    // alarmClock(1, false) â†’ "7:00"
    // alarmClock(5, false) â†’ "7:00"
    // alarmClock(0, false) â†’ "10:00"
    public String alarmClock(int day, boolean vacation) {
        boolean isWeekend;
        if(day>=1 && day <=5) {
            isWeekend = false;
        } else isWeekend = true;

        if(isWeekend && vacation) return "off";
        else if(isWeekend && !vacation) return "10:00";
        else if(!isWeekend && vacation) return "10:00";
        else return "7:00";
    }

}
