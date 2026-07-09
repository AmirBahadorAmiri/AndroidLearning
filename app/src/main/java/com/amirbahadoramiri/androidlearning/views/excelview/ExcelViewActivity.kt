package com.amirbahadoramiri.androidlearning.views.excelview

import android.os.Bundle
import com.amirbahadoramiri.androidlearning.R
import com.amirbahadoramiri.androidlearning.bases.BaseActivity
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream

class ExcelViewActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edgeEnabled()
        setContentView(R.layout.activity_excelview)
        setViewCompat()

        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Sheet1")

        // ردیف هدر
        val headerRow = sheet.createRow(0)
        headerRow.createCell(0).setCellValue("نام")
        headerRow.createCell(1).setCellValue("سن")
        headerRow.createCell(2).setCellValue("شهر")

        // داده‌ها
        val row1 = sheet.createRow(1)
        row1.createCell(0).setCellValue("علی")
        row1.createCell(1).setCellValue(28.toDouble())
        row1.createCell(2).setCellValue("تهران")

        val row2 = sheet.createRow(2)
        row2.createCell(0).setCellValue("حسن")
        row2.createCell(1).setCellValue(24.toDouble())
        row2.createCell(2).setCellValue("مشهد")

        val file = File(getExternalFilesDir(null), "MyData.xlsx")
        FileOutputStream(file).use { workbook.write(it) }
        workbook.close()

    }
}
