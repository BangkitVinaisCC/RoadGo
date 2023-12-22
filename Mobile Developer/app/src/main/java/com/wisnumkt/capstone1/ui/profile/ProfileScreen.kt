package com.wisnumkt.capstone1.ui.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wisnumkt.capstone1.R // Make sure to have a proper import for R
import com.wisnumkt.capstone1.core.utils.clearUserEmail
import com.wisnumkt.capstone1.core.utils.recreateMainActivity
import com.wisnumkt.capstone1.ui.theme.Capstone1Theme

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Optionally, you can add an icon or image here
            Text(
                text = "Coming Soon",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable(onClick = {
                    clearUserEmail(context)
                    recreateMainActivity(context)})
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "We are working hard to bring you this feature.",
                color = Color.Gray,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun ProfileScreenPreview() {
    Capstone1Theme {
        ProfileScreen()
    }
}