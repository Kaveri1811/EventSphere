package com.booking_service.booking_service.events;

public class BookingCreatedEvent {

    private Long bookingId;
    private Long userId;
    private Long eventId;
    private Integer seats;

    public BookingCreatedEvent() {
    }

    public BookingCreatedEvent(Long bookingId,
                               Long userId,
                               Long eventId,
                               Integer seats) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.eventId = eventId;
        this.seats = seats;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
