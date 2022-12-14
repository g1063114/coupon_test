package com.example.coupon.common.domain

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseDeleteEntity(

    var deleteFlag: Boolean? = null,

    var deletedAt: LocalDateTime? = null
): BaseEntity()