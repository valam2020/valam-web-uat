package com.valam.app.controller;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.dto.PaymentDto;
import com.valam.app.model.PaymentDetails;
import com.valam.app.service.PaymentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @ApiOperation(value = "api to add new payment with userid and dispatcherid")
    @PostMapping("/add")
    public PaymentDetails addPayment(@RequestBody PaymentDto paymentDto) {
        return paymentService.savePayment(paymentDto);
    }

    @ApiOperation(value = "api to get payment history")
    @GetMapping("/history")
    public List<PaymentDetails> paymentHistoryAll() {
        return paymentService.getPayment();
    }

    @ApiOperation(value = "api to find payment details by Id")
    @GetMapping("/{id}")
    public PaymentDetails findPaymentById(@PathVariable Long id) {
        return paymentService.getpaymentByID(id);
    }

    @ApiOperation(value = "api to get payment total")
    @GetMapping("/paymentTotal")
    public float getPayment(float distance) {
        return paymentService.getTotalPayment(distance);

    }


}
