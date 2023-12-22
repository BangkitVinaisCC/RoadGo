package com.wisnumkt.capstone1.componen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wisnumkt.capstone1.ui.theme.Capstone1Theme

@Composable
fun Filter() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(32.dp)  // Increased height for better touch area
            .background(color = Color(0x00000000))
    ) {
        FilterItem("Terbaik", isSelected = true)
        FilterItem("Terdekat", isSelected = false)
        FilterItem("Populer", isSelected = false)
    }
}

@Composable
fun FilterItem(text: String, isSelected: Boolean) {
    Text(
        text = text,
        fontSize = if (isSelected) 12.sp else 11.sp,  // Adjusted font size
        lineHeight = 20.sp,
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
        color = if (isSelected) Color(0xFF00BDD6) else Color(0xFF565E6C),
        modifier = Modifier
            .background(
                color = if (isSelected) Color(0xFFEBFDFF) else Color.Transparent,
                shape = RoundedCornerShape(16.dp)  // Rounded shape for a better look
            )
            .padding(horizontal = 16.dp, vertical = 4.dp)  // Adjusted padding
    )
}

@Preview(showBackground = true)
@Composable
fun FilterPreview() {
    Capstone1Theme {
        Filter()
    }
}