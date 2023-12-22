package com.example.demo.daos;

import com.example.demo.entities.Coupon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class CouponInsertDaoImpl implements CouponInsertDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Coupon insertCoupon(Coupon coupon) {
        entityManager.persist(coupon);
        return coupon;
    }
}
