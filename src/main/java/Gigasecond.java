import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Gigasecond {
    private LocalDateTime dateTime;

    public Gigasecond(LocalDate moment) {
        this(moment.atTime(0,0,0));
    }

    public Gigasecond(LocalDateTime moment) {
        this.dateTime = moment;
        long gigaSecond = Math.round(Math.pow(10,9));
        this.dateTime = dateTime.plusSeconds(gigaSecond);
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
}
