package com.compose.valskinapp.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.valskinapp.data.SkinRepository

@Composable
fun DetailSkinApp(
    modifier: Modifier = Modifier,
    idBook: String,
    detailViewModel: DetailAppViewModel = viewModel(factory = DetailViewModelFactory(SkinRepository())),
){
    val bookData by detailViewModel.getBookData(idBook).collectAsState()

    Scaffold{paddingValue ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValue)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = bookData.photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )
            Spacer(modifier = Modifier.padding(14.dp))
            Text(
                text = bookData.name,
                style = MaterialTheme.typography.h2,
                fontSize = 30.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            )
            Divider(
                color = MaterialTheme.colors.onBackground,
                thickness = 1.dp
            )
            Text(
                text = bookData.descripstion,
                lineHeight = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .alpha(0.7f)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    JetBooksTheme {
        DetailBook(idBook = "1")
    }
}