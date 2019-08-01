package nz.net.osnz.java;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class DateUtil {

  public static boolean isFriday(LocalDate localDate) {
    return localDate.getDayOfWeek() == DayOfWeek.FRIDAY;
  }

}
