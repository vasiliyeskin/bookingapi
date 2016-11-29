package isr.ek0.bookingapi.testutils;

import isr.ek0.bookingapi.model.Booking;

import java.time.LocalTime;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static isr.ek0.bookingapi.testutils.UsersUtil.USER_1;
import static isr.ek0.bookingapi.testutils.UsersUtil.USER_2;
import static java.time.LocalDate.now;

public class BookingUtil {
    public static final Booking BOOKING_1 = new Booking(USER_1.getEmail(), now(), LocalTime.of(18, 0), RestaurantUtil.ADMIN1_RESTAURANT1.getName());
    public static final Booking BOOKING_2 = new Booking(USER_2.getEmail(), now(), LocalTime.of(20, 0), RestaurantUtil.ADMIN1_RESTAURANT1.getName());
    public static final List<Booking> BOOKINGS = of(BOOKING_1, BOOKING_2);
    public static final Booking NEW_BOOKING = new Booking(now().plusDays(1), LocalTime.of(21, 0), RestaurantUtil.ADMIN1_RESTAURANT1.getName());
    public static final Booking WRONG_RESTAURANT_NEW_BOOKING = new Booking(now().plusDays(1), LocalTime.of(21, 0), RestaurantUtil.ADMIN1_RESTAURANT1.getName() + "wrong");
    public static final Booking NEW_BOOKING_SAME_DAY = new Booking(now(), LocalTime.of(21, 0), RestaurantUtil.ADMIN1_RESTAURANT1.getName());
    public static final Booking LATE_BOOKING = new Booking(now(), LocalTime.of(22, 30), RestaurantUtil.ADMIN1_RESTAURANT2.getName());
    public static final Booking WRONG_BOOKING = new Booking(now(), LocalTime.now().plusHours(1), RestaurantUtil.ADMIN1_RESTAURANT1.getName());
    public static final Booking NULL_DATE_BOOKING = new Booking(null, LocalTime.of(16, 0), RestaurantUtil.ADMIN1_RESTAURANT1.getName());
    public static final List<Booking> BOOKINGS_USER1_WITH_NEW = of(BOOKING_1, NEW_BOOKING);
}
