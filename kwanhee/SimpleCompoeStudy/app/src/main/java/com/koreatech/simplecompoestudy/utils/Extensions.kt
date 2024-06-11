package com.koreatech.simplecompoestudy.utils

import android.content.Context
import android.hardware.biometrics.BiometricManager.Strings
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

fun Context.showToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

@Composable
fun HorizontalSpacer(width: Dp) = Spacer(modifier = Modifier.width(width))

@Composable
fun VerticalSpacer(height: Dp) = Spacer(modifier = Modifier.height(height))