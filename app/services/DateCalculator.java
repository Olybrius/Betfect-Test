package services;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joris on 22/04/2015.
 */
public class DateCalculator {

    /**
     * The methode computes the date of the meeting
     * @return listDate
     */
    public static List<DateTime> getMeeting()
    {
        DateTime now = new DateTime();

        if(now.getDayOfMonth() >= 14)
        {
            now = now.plusMonths(1);
        }

        now = now.withDayOfMonth(1);
        DateTime tmp = now;

        DateTime result = new DateTime();
        List<DateTime> listDate = new ArrayList<DateTime>();

        for(int i = 0; i < 6; i++)
        {
            tmp = now.plusMonths(i);
            tmp = tmp.withDayOfMonth(14);

            if(tmp.getDayOfWeek() == DateTimeConstants.SUNDAY)
            {
                result = tmp.plusDays(2);
            }
            else if(tmp.getDayOfWeek() == DateTimeConstants.SATURDAY)
            {
                result = tmp.plusDays(1);
            }
            else
            {
                result = tmp;
            }

            listDate.add(result);
        }

        return listDate;

    }

    /**
     * The method computes the end of the mont
     * @return listDate
     */
    public static List<DateTime> getEndMonth()
    {
        DateTime now = new DateTime();
        DateTime tmp = now;

        DateTime result = new DateTime();
        List<DateTime> listDate = new ArrayList<DateTime>();

        for(int i = 0; i < 6; i++)
        {
            tmp = now.plusMonths(i);
            tmp = getLastDayOfMonth(tmp);

            if(tmp.getDayOfWeek() == DateTimeConstants.FRIDAY)
            {
                result = tmp.minusDays(1);
            }
            else if(tmp.getDayOfWeek() == DateTimeConstants.SATURDAY)
            {
                result = tmp.minusDays(2);
            }
            else if(tmp.getDayOfWeek() == DateTimeConstants.SUNDAY)
            {
                result = tmp.minusDays(3);
            }
            else
            {
                result = tmp;
            }

            listDate.add(result);
        }

        return listDate;

    }

    /**
     * The methode computes the last day of a month
     * @return date
     */
    public static DateTime getLastDayOfMonth(DateTime date)
    {
        date = date.plusMonths(1);
        date = date.withDayOfMonth(1);
        date = date.minusDays(1);

        return date;
    }
}
