package com.example.demo.services;

import com.example.demo.entities.Coupon;


public interface CouponService {
    Coupon insertCoupon(Coupon coupon);
    String getAvailableCoupons(String jwt);
}
