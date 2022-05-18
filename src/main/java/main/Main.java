package main;

import model.entities.reservation.Reservation;
import model.exceptions.DomainException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.valueOf;
import static java.util.Calendar.*;
import static javax.swing.JOptionPane.*;

public class Main {
    public static void main(String[] args) {

        final String HOTEL_INFO = "OVERLOOK HOTEL";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = getInstance();
        Calendar today = getInstance();
        int day = today.get(DAY_OF_MONTH);
        int month = today.get(MONTH);

        List<Integer> dayList = new ArrayList<>();
        for (int i = 1; i < 32; i++) {
            dayList.add(i);
        }
        Object[] days = dayList.toArray();

        List<Integer> monthList = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            monthList.add(i);
        }
        Object[] months = monthList.toArray();

        List<Integer> yearsList = new ArrayList<>();
        for (int i = calendar.get(YEAR); i >= 1900; i--) {
            yearsList.add(i);
        }
        Object[] years = yearsList.toArray();

        try {
            Integer number = valueOf(showInputDialog("Room number"));

            Object checkInDay = showInputDialog(null, "CHECKIN DAY", HOTEL_INFO, QUESTION_MESSAGE, null, days, days[day - 1]);
            Object checkInMonth = showInputDialog(null, "CHECKIN MONTH", HOTEL_INFO, QUESTION_MESSAGE, null, months, months[month]);
            Object checkInYear = showInputDialog(null, "CHECKIN YEAR", HOTEL_INFO, QUESTION_MESSAGE, null, years, years[0]);
            Date checkIn = simpleDateFormat.parse(checkInDay.toString() + "/" + checkInMonth.toString() + "/" + checkInYear.toString());

            Object checkOutDay = showInputDialog(null, "CHECKOUT DAY", HOTEL_INFO, QUESTION_MESSAGE, null, days, days[day - 1]);
            Object checkOutMonth = showInputDialog(null, "CHECKOUT MONTH", HOTEL_INFO, QUESTION_MESSAGE, null, months, months[month]);
            Object checkOutYear = showInputDialog(null, "CHECKOUT YEAR", HOTEL_INFO, QUESTION_MESSAGE, null, years, years[0]);
            Date checkOut = simpleDateFormat.parse(checkOutDay.toString() + "/" + checkOutMonth.toString() + "/" + checkOutYear.toString());

            Reservation reservation = new Reservation(number, checkIn, checkOut);
            showMessageDialog(null, reservation);
            showMessageDialog(null, "ATUALIZE OS DADOS");

            Object checkinDay = showInputDialog(null, "CHECKIN DAY", HOTEL_INFO, QUESTION_MESSAGE, null, days, days[day - 1]);
            Object checkinMonth = showInputDialog(null, "CHECKIN MONTH", HOTEL_INFO, QUESTION_MESSAGE, null, months, months[month]);
            Object checkinYear = showInputDialog(null, "CHECKIN YEAR", HOTEL_INFO, QUESTION_MESSAGE, null, years, years[0]);
            checkIn = simpleDateFormat.parse(checkinDay.toString() + "/" + checkinMonth.toString() + "/" + checkinYear.toString());

            Object checkoutDay = showInputDialog(null, "CHECKOUT DAY", HOTEL_INFO, QUESTION_MESSAGE, null, days, days[day - 1]);
            Object checkoutMonth = showInputDialog(null, "CHECKOUT MONTH", HOTEL_INFO, QUESTION_MESSAGE, null, months, months[month]);
            Object checkoutYear = showInputDialog(null, "CHECKOUT YEAR", HOTEL_INFO, QUESTION_MESSAGE, null, years, years[0]);
            checkOut = simpleDateFormat.parse(checkoutDay.toString() + "/" + checkoutMonth.toString() + "/" + checkoutYear.toString());

            reservation.updateDates(checkIn, checkOut);
            showMessageDialog(null, reservation);
        } catch (ParseException e) {
            showMessageDialog(null, "INVALID DATE FORMAT!");
        } catch (DomainException e) {
            showMessageDialog(null, "ERROR IN RESERVATION: " + e.getMessage());
        } catch (RuntimeException e) {
            showMessageDialog(null, "UNEXPECTED ERROR!");
        }
    }
}
