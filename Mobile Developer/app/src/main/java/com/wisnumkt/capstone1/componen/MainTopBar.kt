package com.wisnumkt.capstone1.componen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wisnumkt.capstone1.ui.theme.Capstone1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(query: String,
               onQueryChange: (String) -> Unit,
               modifier: Modifier = Modifier) {
    Column (modifier = modifier
        .padding(16.dp)

    ){
        Row (modifier = modifier
            .fillMaxWidth()
        ){
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = modifier
                    .fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null
                    )
                },
                placeholder = { Text(text = "Search Here!")}
            )
        }
        Row (modifier = Modifier.padding(top = 8.dp)){
            Text(text = "location : Surabaya")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainTopBarPreview() {
    Capstone1Theme {
        MainTopBar("Halo", {})
    }
}