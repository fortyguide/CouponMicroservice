package com.example.demo.daos;

import com.example.demo.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponInsertDao {
    Coupon insertCoupon(Coupon coupon);
}
