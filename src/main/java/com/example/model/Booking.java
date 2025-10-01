package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class Booking {
    @JsonProperty("bookingid")
    private Long bookingId;

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("totalprice")
    private Double totalPrice;

    @JsonProperty("depositpaid")
    private Boolean depositPaid;

    @JsonProperty("bookingdates")
    private BookingDates bookingDates;

    @JsonProperty("additionalneeds")
    private String additionalNeeds;

    // Constructors
    public Booking() {}

    public Booking(String firstName, String lastName, Double totalPrice,
                   Boolean depositPaid, BookingDates bookingDates, String additionalNeeds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.bookingDates = bookingDates;
        this.additionalNeeds = additionalNeeds;
    }

    // Getters and Setters
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public Boolean getDepositPaid() { return depositPaid; }
    public void setDepositPaid(Boolean depositPaid) { this.depositPaid = depositPaid; }

    public BookingDates getBookingDates() { return bookingDates; }
    public void setBookingDates(BookingDates bookingDates) { this.bookingDates = bookingDates; }

    public String getAdditionalNeeds() { return additionalNeeds; }
    public void setAdditionalNeeds(String additionalNeeds) { this.additionalNeeds = additionalNeeds; }

    public static class BookingDates {
        @JsonProperty("checkin")
        private String checkIn;

        @JsonProperty("checkout")
        private String checkOut;

        public BookingDates() {}

        public BookingDates(String checkIn, String checkOut) {
            this.checkIn = checkIn;
            this.checkOut = checkOut;
        }

        public String getCheckIn() { return checkIn; }
        public void setCheckIn(String checkIn) { this.checkIn = checkIn; }

        public String getCheckOut() { return checkOut; }
        public void setCheckOut(String checkOut) { this.checkOut = checkOut; }
    }
}