package com.example.demo.study.retry

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.retry.support.RetryTemplate
import java.io.IOException

@SpringBootTest
class ReTryServiceTest {
    @Autowired
    lateinit var retryTemplate: RetryTemplate

    @Autowired
    lateinit var reTryService: ReTryService

    @Test
    fun testRetry() {
        assertThrows<IOException> {
            reTryService.retryTest()
        }
    }

    @Test
    fun testRetry2() {

        assertThrows<IOException> {
            reTryService.retryTest2()
        }

    }

}
