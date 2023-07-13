package com.example.examretry.retry

import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class ReTryService {

    @Retryable(interceptor = "retryInterceptor", value = [IOException::class])
    fun retryTest() {

        println("test try")
        throw IOException()

    }

    @Retryable(maxAttempts = 2, backoff = Backoff(delay = 1000), value = [IOException::class])
    fun retryTest2() {

        println("test try")
        throw IOException()

    }
}