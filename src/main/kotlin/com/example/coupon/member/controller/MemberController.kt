package com.example.coupon.member.controller

import com.example.coupon.member.controller.dto.ReqMemberSaveDTO
import com.example.coupon.member.service.MemberService
import com.example.coupon.member.service.dto.MemberSearchDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/member")
class MemberController(
    val memberService: MemberService,
){

    @GetMapping
    fun getMemberAll(): List<MemberSearchDTO>{
        return memberService.findMember()
    }

    @PostMapping
    fun saveMember(
        @Valid @RequestBody request: ReqMemberSaveDTO
    ){
        memberService.saveMember(request)
    }
}