package com.example.demo.controllers;

import com.example.demo.entities.Coupon;
import com.example.demo.entities.JsonResponseBody;
import com.example.demo.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControllerClass {
    @Autowired
    CouponService couponService;

    @PostMapping("/insertCoupon")
    public ResponseEntity<Coupon> insertCoupon(@RequestBody Coupon coupon){
        Coupon couponResult = couponService.insertCoupon(coupon);
        return new ResponseEntity<Coupon>(couponResult, HttpStatus.CREATED);
    }

    @PostMapping("/findCoupon")
    public ResponseEntity<JsonResponseBody> findCoupons(@RequestParam (value = "jwt") String jwt){
        try {
            String coupons = couponService.getAvailableCoupons(jwt);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), coupons));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(),
                                                                      "Error: " + e.toString()));
        }
    }
}
