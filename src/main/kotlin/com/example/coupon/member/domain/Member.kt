package com.example.coupon.member.domain

import com.example.coupon.common.domain.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "member.member")
@DynamicInsert
@DynamicUpdate
class Member(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "birthday", nullable = false)
    var birthday: String

): BaseEntity(){

}