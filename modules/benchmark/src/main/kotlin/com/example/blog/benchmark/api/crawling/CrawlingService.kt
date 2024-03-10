package com.example.blog.benchmark.api.crawling

import com.example.blog.benchmark.api.crawling.persistence.PassMarkRepository
import com.example.blog.benchmark.api.file.ExcelService
import com.example.blog.benchmark.type.CpuPassMarkUrl
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class CrawlingService(
        private val passMarkRepository: PassMarkRepository,
        private val excelService: ExcelService
) {
    companion object {
        private val EXCEL_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
    }

    fun cpuBenchMark() {

        val data: MutableList<List<String>> = mutableListOf()
        var size = 0

        CpuPassMarkUrl.values().forEach {
            // data crawling
            val cpuBenchMarks = passMarkRepository.getCpuBenchMarks(it.url)
            val cpuBenchMarksSize = cpuBenchMarks.size

            // 순위 별 정보 셋팅
            data.add(listOf("${size + 1} ~ ${size + cpuBenchMarksSize}", it.title))

            // 정보 가공
            cpuBenchMarks.forEachIndexed { index, cpuPassMark ->
                val list = listOf("${index + 1 + size}", cpuPassMark.prdName, cpuPassMark.benchmark)
                data.add(list)
            }

            // size 재정의
            size += cpuBenchMarksSize
        }

        val header: List<String> = listOf("순위", "제품명", "점수")
        val filePath = "D:\\blog\\file\\excel"
        val fileName = "${LocalDateTime.now().format(EXCEL_DATE_FORMAT)}_CPU_BENCHMARK.xlsx"
        excelService.generateExcelFile(data, header, "$filePath\\$fileName")
    }
}