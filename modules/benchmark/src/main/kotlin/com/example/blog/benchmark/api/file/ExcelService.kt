package com.example.blog.benchmark.api.file

import org.apache.poi.ss.usermodel.HorizontalAlignment
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import java.io.FileOutputStream

@Service
class ExcelService {

    fun generateExcelFile(data: List<List<String>>, header: List<String>, filePath: String) {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Sheet1")

        // 헤더 행 생성
        val headerRow = sheet.createRow(0)
        header.forEachIndexed { index, columnHeader ->
            val cell = headerRow.createCell(index)
            cell.setCellValue(columnHeader)

            // 가운데 정렬을 위한 스타일 생성
            val style = workbook.createCellStyle()
            style.alignment = HorizontalAlignment.CENTER
            cell.cellStyle = style
        }

        // 데이터 행 생성
        data.forEachIndexed { rowIndex, rowData ->
            val dataRow = sheet.createRow(rowIndex + 1)
            rowData.forEachIndexed { columnIndex, cellValue ->
                val cell = dataRow.createCell(columnIndex)
                cell.setCellValue(cellValue)

                // 가운데 정렬을 위한 스타일 생성
                val style = workbook.createCellStyle()
                style.alignment = HorizontalAlignment.CENTER
                cell.cellStyle = style
            }
        }

        // 열의 크기 자동 조절 (autoSizeColumn 사용)
        for (i in header.indices) {
            sheet.autoSizeColumn(i)
        }

        // 파일 저장
        FileOutputStream(filePath).use {
            workbook.write(it)
        }
    }
}