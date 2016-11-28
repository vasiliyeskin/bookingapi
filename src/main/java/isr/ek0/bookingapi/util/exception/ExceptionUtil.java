package isr.ek0.bookingapi.util.exception;

import isr.ek0.bookingapi.model.Booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.time.LocalTime.of;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.Assert.notNull;

public class ExceptionUtil {
    public ExceptionUtil() {
    }

    public static Booking validateBooking(Booking booking) {
        notNull(booking, "booking must not be null");
        if (booking.getTime().isAfter(of(23, 0)) || booking.getTime().isBefore(of(8, 0))) {
            throw new WrongBookingException("booking must be for a time from 8AM to 11PM");
        }
        LocalDateTime ldt = LocalDateTime.of(booking.getBookingId().getDate(), booking.getTime());
        LocalDateTime earliestDateTime = now().plusHours(2);
        if (ldt.isBefore(earliestDateTime)) {
            throw new WrongBookingException("booking must be for a datetime from " + earliestDateTime.truncatedTo(ChronoUnit.MINUTES));
        }
        return booking;
    }

    public static void checkNotFound(int result, Object id, Class clazz, boolean isModification) {
        if (result == 0 && isModification) {
            checkNotFound(null, id, clazz);
        }
    }

    public static <T, S> T checkNotFound(T object, Object id, Class<S> clazz) {
        if (object == null) {
            throw new NotFoundException("Not found " + clazz.getSimpleName().toLowerCase() + " with id = " + id);
        }
        return object;
    }

    public static List<Double> validateCoordinates(List<Double> coordinates) {
        return coordinates.stream().filter(coordinate -> checkNotNull(coordinate, new WrongCoordinatesException("wrong coordinate"))).collect(toList());
    }

    private static boolean checkNotNull(Double coordinate, RuntimeException ex) {
        if (coordinate == null) {
            throw ex;
        }
        return true;
    }

}
