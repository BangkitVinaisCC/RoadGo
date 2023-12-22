package com.wisnumkt.capstone1.ui.search

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.wisnumkt.capstone1.componen.Filter
import com.wisnumkt.capstone1.componen.MainTopBar
import com.wisnumkt.capstone1.componen.RekomendList
import com.wisnumkt.capstone1.core.api.ApiConfig
import com.wisnumkt.capstone1.core.repository.SellerRepository
import com.wisnumkt.capstone1.core.utils.saveRekomId
import com.wisnumkt.capstone1.ui.theme.Capstone1Theme
import com.wisnumkt.capstone1.ui.viewmodel.SearchViewModel
import com.wisnumkt.capstone1.ui.viewmodel.ViewModelFactory

@androidx.annotation.OptIn(UnstableApi::class) @OptIn(ExperimentalFoundationApi::class)
@Composable
fun Search(modifier: Modifier = Modifier, viewModel: SearchViewModel = viewModel(factory = ViewModelFactory(
    SellerRepository(apiService = ApiConfig.getApiService()))), navigateToDetail: (String) -> Unit,) {
    val listState = rememberLazyListState()
    val groupedSeller by viewModel.listSeller.collectAsState()
    val query by viewModel.query
    val context = LocalContext.current

    Column(
        modifier = modifier
    ) {
        MainTopBar(query = query,
            onQueryChange = viewModel::search,)
        Filter()
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 90.dp)
        ){
            groupedSeller.forEach { (_, sellers) ->
                items(sellers, key = { it.id }) { seller ->
                    RekomendList(
                        name = seller.name,
                        product = seller.produk,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 300)),
                        onClick = {
                            saveRekomId(context, seller.id)
                            navigateToDetail(seller.id)}
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    Capstone1Theme {
        Search(navigateToDetail = {})
    }
}