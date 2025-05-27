package com.example.ktorlog.feature.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import com.example.ktorlog.theme.Primary700

@Composable
fun LoadingDialog() {
    Dialog(onDismissRequest = {  }) {
        CircularProgressIndicator(color = Primary700)
    }
}