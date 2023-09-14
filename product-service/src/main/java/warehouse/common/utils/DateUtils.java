package warehouse.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static final ZoneId ZONE_ID = ZoneId.systemDefault();

    public static LocalDateTime localDateTimeOf(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZONE_ID);
    }

}
