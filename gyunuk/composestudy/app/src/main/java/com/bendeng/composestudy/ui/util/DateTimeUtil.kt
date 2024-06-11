package com.bendeng.composestudy.ui.util

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtils {
    @SuppressLint("SimpleDateFormat")
    fun formatDateTime(datetime: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")
            val offsetDateTime = OffsetDateTime.parse(datetime)
            offsetDateTime.format(formatter)
        } else {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(datetime)
            date?.let { SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초").format(it) } ?: "유효하지 않은 날짜"
        }
    }
}