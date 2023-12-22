package com.wisnumkt.capstone1.componen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wisnumkt.capstone1.ui.theme.Capstone1Theme

@Composable
fun BottomNews() {
    Column(
        Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFFFFFFF))
            .padding(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(146.dp, Alignment.Start),
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = "Rekomendasi Terbaik",
                fontSize = 16.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF171A1F),
            )

            Text(
                text = "View all",
                fontSize = 14.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF9095A0),
                textAlign = TextAlign.Right,
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNewsPreview() {
    Capstone1Theme {
        BottomNews()
    }
}