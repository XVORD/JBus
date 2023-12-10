package com.ChristopherSatyaFredellaBalakosaJBusER.controller;

import com.ChristopherSatyaFredellaBalakosaJBusER.*;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonAutowired;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
/**
 * PaymentController class
 * Handles HTTP requests related to payments and provides functionality for making bookings, accepting payments, and canceling payments.
 * @author Christopher Satya
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{
    /**
     * The JSON database table for storing `Payment` objects.
     */
    @JsonAutowired(value = Payment.class, filepath = "C:\\Kuliah\\Java Prak\\JBus\\data\\payments.json")
    public static JsonTable<Payment> paymentTable;
    /**
     * Retrieves the JSON database table associated with this controller.
     * @return The JSON table for `Payment` objects.
     */
    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }
    /**
     * Handles the HTTP POST request to make a booking payment. This method allows users to make
     * a booking payment for a bus seat based on the provided details.
     * @param buyerId       The ID of the buyer (account) making the booking.
     * @param renterId      The ID of the renter (company) associated with the bus.
     * @param busId         The ID of the bus for which the booking is made.
     * @param busSeats      The list of bus seats to book.
     * @param departureDate The departure date and time of the bus.
     * @return A `BaseResponse` object indicating the result of the booking payment operation.
     */
    @RequestMapping(value="/makeBooking", method = RequestMethod.POST)
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate
    ) {
        for (Account account : AccountController.accountTable) {
            if (account.id == buyerId && account.balance >= BusController.busTable.get(busId).price.price && Algorithm.<Schedule>find(BusController.busTable.get(busId).schedules, s -> s.departureSchedule.equals(departureDate)) != null) {
                account.balance -= BusController.busTable.get(busId).price.price;
                Payment payment = new Payment(buyerId, renterId, busId, busSeats, Timestamp.valueOf(departureDate));
                payment.status = Invoice.PaymentStatus.WAITING;
                paymentTable.add(payment);
                return new BaseResponse<>(true, "Berhasil melakukan booking", payment);

            } else
                return new BaseResponse<>(false, "Gagal melakukan booking", null);
        }
        return new BaseResponse<>(false, "Gagal melakukan booking", null);
    }
    /**
     * Handles the HTTP POST request to accept a payment. This method allows users to accept a
     * payment for a booking, updating the payment status to "SUCCESS."
     * @param id The ID of the payment to accept.
     * @return A `BaseResponse` object indicating the result of the payment acceptance operation.
     */
    @RequestMapping(value="/{id}/accept", method = RequestMethod.POST)
    public BaseResponse<Payment> accept(@PathVariable int id){
        if(Algorithm.<Payment>find(paymentTable,  x -> x.id == id) != null) {
            Payment p = Algorithm.<Payment>find(paymentTable, x -> x.id == id);
            p.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Berhasil", p);
        }
        return new BaseResponse<>(false, "Gagal melakukan booking", null);

    }
    /**
     * Handles the HTTP POST request to cancel a payment. This method allows users to cancel a
     * payment for a booking, updating the payment status to "FAILED."
     * @param id The ID of the payment to cancel.
     * @return A `BaseResponse` object indicating the result of the payment cancellation operation.
     */
    @RequestMapping(value="/{id}/cancel", method = RequestMethod.POST)
    public BaseResponse<Payment> cancel(@PathVariable int id){
        if(Algorithm.<Payment>find(paymentTable,  x -> x.id == id) != null) {
            Payment p = Algorithm.<Payment>find(paymentTable, x -> x.id == id);
            p.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<>(true, "Berhasil", p);
        }
        return new BaseResponse<>(false, "Gagal melakukan booking", null);

    }
}
