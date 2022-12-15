package com.example.coupon.member.controller.dto

import com.example.coupon.member.domain.Member
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.NotBlank

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class ReqMemberSaveDTO(

    @NotBlank
    val name: String,

    @NotBlank
    val birthday: String
){
    fun toEntity(): Member{
        return Member(name = this.name, birthday = this.birthday)
    }
}