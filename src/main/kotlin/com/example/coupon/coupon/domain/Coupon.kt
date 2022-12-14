package com.example.coupon.coupon.domain

import com.example.coupon.common.domain.BaseDeleteEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "coupon.coupon")
@DynamicInsert
@DynamicUpdate
class Coupon(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description", nullable = true)
    var description: String? = null,

    @Column(name = "valid_date", nullable = false)
    var validDate: Int,

    @Column(name = "coupon_start_datetime", nullable = false)
    var couponStartDateTime: LocalDateTime,

    @Column(name = "coupon_end_datetime", nullable = false)
    var couponEndDateTime: LocalDateTime,

): BaseDeleteEntity()