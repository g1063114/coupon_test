package com.example.coupon

import com.example.coupon.coupon.controller.dto.ReqCouponIssuanceSaveDTO
import com.example.coupon.coupon.service.CouponService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
class CouponApplicationTests(

) {
	@Autowired
	private lateinit var couponService: CouponService
	@Test
	fun contextLoads() {
	}

	@Test
	fun 테스트() {
		val executorService = Executors.newFixedThreadPool(3)
		val countDownLatch = CountDownLatch(3)
		for (i in 1..3) {
			executorService.execute {
				couponService.saveCouponIssuance(
					ReqCouponIssuanceSaveDTO(2,1, LocalDateTime.now())
				)
				countDownLatch.countDown()
			}
		}
		countDownLatch.await()
	}
}
