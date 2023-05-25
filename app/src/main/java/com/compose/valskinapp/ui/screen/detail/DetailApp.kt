package com.compose.valskinapp.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.compose.valskinapp.data.SkinRepository
import com.compose.valskinapp.ui.theme.ValSkinAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSkin(
    modifier: Modifier = Modifier,
    idSkin: String,
    detailViewModel: DetailViewModel = viewModel(factory = DetailViewModelFactory(SkinRepository())),
){
    val skinData by detailViewModel.getSkinData(idSkin).collectAsState()

    Scaffold{paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = rememberImagePainter(skinData.photoUrl),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )
            Divider(
                color = MaterialTheme.colorScheme.onBackground,
                thickness = 5.dp
            )
            Text(
                text = skinData.name,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            )
            Divider(
                color = MaterialTheme.colorScheme.onBackground,
                thickness = 5.dp
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = skinData.description,
                lineHeight = 30.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .alpha(0.7f)
            )
            Spacer(modifier = Modifier.padding(14.dp))
            Text(
                text = skinData.price,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ValSkinAppTheme {
        DetailSkin(idSkin = "1")
    }
}