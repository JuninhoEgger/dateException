package model.entities.reservation;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Reservation {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    public Reservation() {
    }

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public long duration() {
        long difference = checkOut.getTime() - checkIn.getTime();
        return DAYS.convert(difference, MILLISECONDS);
    }

    public String updateDates(Date checkIn, Date checkOut) {
        Date now = new Date();
        if (checkIn.before(now) || checkOut.before(now)) {
            return "RESERVATION DATES FOR UPDATES MUST BE FUTURE DATES";
        }
        if (!checkOut.after(checkIn)) {
            return "CHECKOUT DATE MUST BE AFTER CHECKIN DATE";
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        return null;
    }

    @Override
    public String toString() {
        return "Número do quarto: " + roomNumber +
                "\n Data de check-in: " + simpleDateFormat.format(checkIn) +
                "\n Data de check-out: " + simpleDateFormat.format(checkOut) +
                "\n Quantidade de noites: " + duration();
    }
}
