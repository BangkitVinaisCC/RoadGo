package com.wisnumkt.capstone1.componen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wisnumkt.capstone1.R
import com.wisnumkt.capstone1.ui.theme.Capstone1Theme

@Composable
fun RekomendList(name: String, product: String, modifier: Modifier, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0x00323842))
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column {
            Text(
                text = name,
                fontSize = 26.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF4A1CA6),
            )
            Text(
                text = product,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF9095A0),
            )
        }
        Text(
            text = "chat",
            fontSize = 11.sp,
            lineHeight = 25.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF00BDD6),
            modifier = Modifier
                .padding(start = 190.dp, top = 1.dp, end = 6.dp, bottom = 1.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFF00BDD6),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .height(20.dp)
                .background(color = Color(0x00000000), shape = RoundedCornerShape(size = 10.dp))
                .padding(start = 5.dp, top = 1.dp, end = 6.dp, bottom = 1.dp),
        )
    }
}

@Preview (showBackground = true)
@Composable
fun RekomendListPreview() {
    Capstone1Theme {
        RekomendList("Kang Yusup","tukang sayur", Modifier, {})
    }
}