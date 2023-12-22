package com.wisnumkt.capstone1.ui.profileseller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wisnumkt.capstone1.R
import com.wisnumkt.capstone1.core.api.ApiConfig
import com.wisnumkt.capstone1.core.model.remote.DataItem
import com.wisnumkt.capstone1.core.repository.SellerRepository
import com.wisnumkt.capstone1.core.utils.getRekomId
import com.wisnumkt.capstone1.core.utils.getUserEmail
import com.wisnumkt.capstone1.navigation.Screen
import com.wisnumkt.capstone1.ui.theme.Capstone1Theme
import com.wisnumkt.capstone1.ui.viewmodel.SearchViewModel
import com.wisnumkt.capstone1.ui.viewmodel.ViewModelFactory

@Composable
fun ProfileSeller(viewModel: SearchViewModel = viewModel(
        factory = ViewModelFactory(
            SellerRepository(apiService = ApiConfig.getApiService())
        )
    )
) {
    val context = LocalContext.current
    val id = remember { getRekomId(context) }
    if (id != null) {
        viewModel.fetchRekomById(id)
    }
    val rekomById: DataItem by viewModel.rekomById.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.seller_avatar),
            contentDescription = "Seller Avatar",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = rekomById.name,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Produk Excellenct service!",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(horizontal = 14.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = rekomById.tentang,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .offset(y = 100.dp)
        ) {
            Text("Booking")
        }

        if (showDialog) {
            ShowBookingDialog(rekomById.name) {
                // Handle booking action if needed
                showDialog = false
            }
        }

        // You can add the remaining products here
    }
}

@Composable
fun ShowBookingDialog(name: String, onBookingConfirmed: () -> Unit) {
    AlertDialog(
        onDismissRequest = { /* Handle dismiss if needed */ },
        title = { Text(text = "Terimakasih") },
        text = { Text(text = "Anda sudah booking $name tunggu sampai $name ke rumah anda") },
        confirmButton = {
            Button(
                onClick = {
                    // Handle the booking action
                    onBookingConfirmed.invoke()
                }
            ) {
                Text(text = "OK")
            }
        }
    )
}

@Preview
@Composable
fun profilesellerPreview() {
    Capstone1Theme {
        ShowBookingDialog("",{})
    }
}