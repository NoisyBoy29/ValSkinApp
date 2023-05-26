package com.compose.valskinapp

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.valskinapp.component.BundleHeader
import com.compose.valskinapp.component.ScrollToTopButton
import com.compose.valskinapp.component.SearchBar
import com.compose.valskinapp.component.SkinListItem
import com.compose.valskinapp.data.SkinRepository
import com.compose.valskinapp.screen.home.HomeAppViewModel
import com.compose.valskinapp.screen.home.ViewModelFactory
import com.compose.valskinapp.ui.theme.ValSkinAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ValSkinApp(
    modifier: Modifier = Modifier,
    viewModel: HomeAppViewModel = viewModel (factory = ViewModelFactory(SkinRepository()))
) {
    val groupedSkin by viewModel.groupedSkin.collectAsState()
    val query by viewModel.query

    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::search,
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                )
            }
            groupedSkin.forEach { (initial, skins) ->
                stickyHeader {
                    BundleHeader(initial)
                }
                items(skins, key = { it.id }) { skin ->
                    SkinListItem(
                        name = skin.name,
                        photoUrl = skin.photoUrl,
                        price = skin.price,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 100))
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ValSkinAppPreview() {
    ValSkinAppTheme() {
        ValSkinApp()
    }
}
