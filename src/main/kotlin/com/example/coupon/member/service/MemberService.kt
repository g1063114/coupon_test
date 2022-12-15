package com.example.coupon.member.service

import com.example.coupon.member.controller.dto.ReqMemberSaveDTO
import com.example.coupon.member.repository.MemberRepository
import com.example.coupon.member.service.dto.MemberSearchDTO
import org.springframework.stereotype.Service

@Service
class MemberService(
    val memberRepository: MemberRepository,
){

    /**
     * 회원 조회
     */
    fun findMember(): List<MemberSearchDTO> {
        return memberRepository.findAll().map {
            MemberSearchDTO(it.name, it.birthday)
        }
    }

    /**
     * 회원 저장
     */
    fun saveMember(request: ReqMemberSaveDTO){
        memberRepository.save(request.toEntity())
    }
}