package com.wisnumkt.capstone1.componen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wisnumkt.capstone1.R
import com.wisnumkt.capstone1.navigation.Screen
import com.wisnumkt.capstone1.ui.theme.Capstone1Theme

@Composable
fun MainFitur( modifier: Modifier = Modifier, navController : NavController) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        item {
            FeatureItem(
                title = "Tukang Sayur",
                iconDrawableId = R.drawable.ic_vegetable,
                onClick = { navController.navigate(Screen.Search.route) }
            )
        }
        item {
            FeatureItem(title = "History", iconDrawableId = R.drawable.ic_history) {
                // Implementasi navigasi atau tindakan yang sesuai
            }
        }
        item {
            FeatureItem(title = "Self Service", iconDrawableId = R.drawable.ic_self_service) {
                // Implementasi navigasi atau tindakan yang sesuai
            }
        }
        // Tambahkan item lain jika diperlukan
    }
}

@Composable
fun FeatureItem(title: String, iconDrawableId: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
            .fillMaxWidth()
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,  // Warna dapat disesuaikan
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                painter = painterResource(id = iconDrawableId),
                contentDescription = title,
                modifier = Modifier
                    .size(24.dp)  // Ukuran ikon disesuaikan
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = title,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainFiturPreview() {
    Capstone1Theme {
        val navController = rememberNavController()
        MainFitur(navController = navController)
    }
}