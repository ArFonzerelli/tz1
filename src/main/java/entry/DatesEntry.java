package entry;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.TimeZone;

public class DatesEntry {

    private DateTimeZone singaporeTimeZone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Singapore"));

    private DateTimeZone londonTimeZone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
    
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    private DateTime firstDateTime;

    private DateTime secondDateTime;

    private DatesEntry(){}

    public DatesEntry(String line) {
        setOriginalDateTimes(line);
    }

    public DateTime getFirstDateTime() {
        return firstDateTime;
    }

    public DateTime getSecondDateTime() {
        return secondDateTime;
    }

    private void setOriginalDateTimes(String line) {
        String [] splittedLine = line.split(",");
        if (splittedLine.length != 4)
            return;

        firstDateTime = formatter.parseDateTime(splittedLine[0]).withZoneRetainFields(DateTimeZone.forID(splittedLine[1]));
        secondDateTime = formatter.parseDateTime(splittedLine[2]).withZoneRetainFields(DateTimeZone.forID(splittedLine[3]));
    }

    public int getBusinessHours(){
        DateTime firstBusinessWeekStart = firstDateTime.withDayOfWeek(DateTimeConstants.MONDAY).withTime(8, 0, 0, 0).withZoneRetainFields(singaporeTimeZone);
        DateTime firstBusinessWeekEnd = firstDateTime.withDayOfWeek(DateTimeConstants.FRIDAY).withTime(19, 0, 0, 0).withZoneRetainFields(londonTimeZone);

        DateTime lastBusinessWeekStart = secondDateTime.withDayOfWeek(DateTimeConstants.MONDAY).withTime(8, 0, 0, 0).withZoneRetainFields(singaporeTimeZone);
        DateTime lastBusinessWeekEnd = secondDateTime.withDayOfWeek(DateTimeConstants.FRIDAY).withTime(19, 0, 0, 0).withZoneRetainFields(londonTimeZone);

        Hours businessHoursInFirstWeek = Hours.hours(0);

        // Первая дата в течение бизнес недели
        if (firstDateTime.isAfter(firstBusinessWeekStart) && firstDateTime.isBefore(firstBusinessWeekEnd))
            businessHoursInFirstWeek = Hours.hoursBetween(firstDateTime, firstBusinessWeekEnd);
        else
//            Первая дата до начала бизнес недели (с утра до 8 часов)
            if (firstDateTime.isBefore(firstBusinessWeekStart))
                businessHoursInFirstWeek = Hours.hoursBetween(firstBusinessWeekStart, firstBusinessWeekEnd);

        Weeks totalWeeks = Weeks.weeksBetween(firstBusinessWeekStart, lastBusinessWeekStart);

        Hours businessHoursInLastWeek = Hours.hours(0);

        // Вторая дата в течение последней бизнес недели
        if (secondDateTime.isAfter(lastBusinessWeekStart) && secondDateTime.isBefore(lastBusinessWeekEnd))
            businessHoursInLastWeek = Hours.hoursBetween(lastBusinessWeekStart, secondDateTime);
        else
//            Вторая дата после окончания бизнес недели (на выходных)
            if (secondDateTime.isAfter(lastBusinessWeekEnd))
                businessHoursInLastWeek = Hours.hoursBetween(lastBusinessWeekStart, lastBusinessWeekEnd);

        Hours businessHoursInWholeWeek = Hours.hoursBetween(firstBusinessWeekStart, firstBusinessWeekEnd);

        int hoursInPeriodBetweenFirstAndLastWeek = 0;

        if (totalWeeks.getWeeks() > 0)
            hoursInPeriodBetweenFirstAndLastWeek = (totalWeeks.getWeeks() - 1) * businessHoursInWholeWeek.getHours();

        return businessHoursInFirstWeek.getHours() + hoursInPeriodBetweenFirstAndLastWeek + businessHoursInLastWeek.getHours();
    }

}
