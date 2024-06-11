package com.koreatech.simplecompoestudy.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatterISO8601(dateTime: String): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    val zonedDateTime = ZonedDateTime.parse(dateTime, dateTimeFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 H시 m분 s초", Locale.KOREAN)
    val localDateTime = zonedDateTime.toLocalDateTime()
    return localDateTime.format(outputFormatter)
}

fun Context.showToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

@Composable
fun HorizontalSpacer(width: Dp) = Spacer(modifier = Modifier.width(width))

@Composable
fun VerticalSpacer(height: Dp) = Spacer(modifier = Modifier.height(height))