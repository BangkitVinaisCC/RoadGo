package com.wisnumkt.capstone1.componen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.wisnumkt.capstone1.ui.theme.Capstone1Theme
import com.wisnumkt.capstone1.R
import com.wisnumkt.capstone1.navigation.Screen

@Composable
fun TopProfile(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, Color.Gray)
    ) {
        // Left side - Text and E-Wallet icon
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                text = "Si Paling Care",
                fontWeight = FontWeight.Light,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = "E-Wallet",
                modifier = Modifier.size(24.dp)
            )
        }

        // Right side - Photo and points
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(80.dp) // The size of the circular frame
                    .background(Color.Transparent, shape = CircleShape)
                    .clip(CircleShape)
            ) {
                val image: Painter = painterResource(id = R.drawable.ic_profile2)
                Image(
                    painter = image,
                    contentDescription = "Profile photo",
                    modifier = Modifier.size(70.dp),// Make the image fill the frame
                    contentScale = ContentScale.Crop // This will crop the image to fill the bounds if it's not a perfect square
                )
            }

            Text(
                text = "12 point",
                modifier = Modifier
                    .border(1.dp, Color.Gray)
                    .padding(4.dp),
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopProfilePreview() {
    Capstone1Theme {
        TopProfile("Hazel")
    }
}