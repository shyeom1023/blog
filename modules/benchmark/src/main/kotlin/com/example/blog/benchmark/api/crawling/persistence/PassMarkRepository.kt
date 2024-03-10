package com.example.blog.benchmark.api.crawling.persistence

import org.jsoup.Jsoup
import org.springframework.stereotype.Repository

@Repository
class PassMarkRepository {

    fun getCpuBenchMarks(url: String): MutableList<CpuPassMark> {
        val searchSite = url
        val doc = Jsoup.connect(searchSite).get()
        val selectDocByLi = doc
                .select("#mark")
                .select("li")

        val benchmarks = selectDocByLi.mapNotNull {
            val prdName = it.select(".prdname").first()
            val benchmark = it.select(".count").first()
            if (prdName == null || benchmark == null) {
                return@mapNotNull null
            }
            CpuPassMark(prdName.text(), benchmark.text())
        }

        return benchmarks.toMutableList()
    }
}

data class CpuPassMark(
        val prdName: String,
        val benchmark: String
)