package com.ChristopherSatyaFredellaBalakosaJBusER.controller;

import com.ChristopherSatyaFredellaBalakosaJBusER.*;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonAutowired;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{
    @JsonAutowired(value = Payment.class, filepath = "C:\\Kuliah\\Java Prak\\JBus\\data\\payments.json")
    public static JsonTable<Payment> paymentTable;

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

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

    @RequestMapping(value="/{id}/accept", method = RequestMethod.POST)
    public BaseResponse<Payment> accept(@PathVariable int id){
        if (Algorithm.<Payment>exists(paymentTable, x -> x.id == id)){
            return new BaseResponse<>(false,"payment ada", null);
        }
        Payment newPayment = Algorithm.<Payment>find(paymentTable, x -> x.id == id);
        newPayment.status = Invoice.PaymentStatus.SUCCESS;
        return new BaseResponse<>(true, "payment accept", newPayment);
    }

    @RequestMapping(value="/{id}/cancel", method = RequestMethod.POST)
    public BaseResponse<Payment> cancel(@PathVariable int id){
        if (!(Algorithm.<Payment>exists(paymentTable, x -> x.id == id))){
            return new BaseResponse<>(false,"payment tidak ada", null);
        }
        Payment newPayment = Algorithm.<Payment>find(paymentTable, x -> x.id == id);
        newPayment.status = Invoice.PaymentStatus.FAILED;
        return new BaseResponse<>(true, "payment cancel", newPayment);
    }
}
