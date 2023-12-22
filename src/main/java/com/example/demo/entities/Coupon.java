package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "coupon")
public class Coupon {

    @Id
    @Column(name = "couponid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long couponId;

    @Column(name = "couponcode", nullable = false)
    @Getter @Setter
    private String couponCode;

    @Column(name = "account", nullable = false)
    @Getter @Setter
    private String account;
}
