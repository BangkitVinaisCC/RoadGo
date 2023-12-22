package com.wisnumkt.capstone1.componen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
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
import com.wisnumkt.capstone1.R
import com.wisnumkt.capstone1.ui.theme.Capstone1Theme

@Composable
fun TopMaps(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(color = Color(0xFFD9D9D9)),
        horizontalArrangement = Arrangement.spacedBy(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = "image description",
            modifier = Modifier
                .width(44.dp)
                .height(44.dp)
                .background(color = Color(0x00000000), shape = RoundedCornerShape(size = 4.dp))
                .padding(start = 12.dp, top = 9.dp, end = 12.dp, bottom = 9.dp)
        )
        Text(
            text = "Peta Tukang",
            fontSize = 18.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF171A1F),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 100.dp)
        )
    }
}


@Preview (showBackground = true)
@Composable
fun MainMapsPreview() {
    Capstone1Theme {
        TopMaps()
    }
}