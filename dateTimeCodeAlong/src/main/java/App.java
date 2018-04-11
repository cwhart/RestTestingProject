import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.GregorianCalendar;

public class App {

    public static void main(String[] args) {
        LocalDate ld = LocalDate.now();
        System.out.println(ld);

        ld = LocalDate.parse("2015-01-01");
        System.out.println(ld);

        ld = LocalDate.parse("02/07/2010", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(ld);

        String isoDate = ld.toString();
        System.out.println(isoDate);

        ld = LocalDate.parse(isoDate);
        System.out.println(ld);

        String formatted = ld.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(formatted);

        formatted = ld.format((DateTimeFormatter.ofPattern("MM=dd=yyyy+=+=+=")));
        System.out.println(formatted);

        formatted = ld.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        System.out.println(formatted);

        LocalDate past = ld.minusDays(8);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);

        past = ld.minusMonths(3);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);

        Period diff = ld.until(past);
        System.out.println(diff);
        System.out.println(diff.getMonths());
        diff = past.until(ld);
        System.out.println(diff.getMonths());

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        formatted = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        System.out.println(formatted);

        Date legacyDate = new Date();
        System.out.println(legacyDate);

        GregorianCalendar legacyCalendar = new GregorianCalendar();
        System.out.println(legacyCalendar);

        ZonedDateTime zdt = ZonedDateTime.ofInstant(legacyDate.toInstant(), ZoneId.systemDefault());
        ld = zdt.toLocalDate();
        System.out.println(ld);

        zdt = legacyCalendar.toZonedDateTime();
        ld = zdt.toLocalDate();
        System.out.println(ld);

    }
}
