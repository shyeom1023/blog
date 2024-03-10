package com.example.blog.benchmark.api

import com.example.blog.benchmark.api.crawling.CrawlingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/benchmark")
class BenchmarkControllerV1(
        private val crawlingService: CrawlingService
) {

    @GetMapping("")
    fun cpuBenchMark(){
        crawlingService.cpuBenchMark()
    }
}