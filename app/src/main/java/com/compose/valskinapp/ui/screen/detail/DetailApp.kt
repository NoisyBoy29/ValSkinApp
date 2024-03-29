package com.compose.valskinapp.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.compose.valskinapp.data.SkinRepository
import com.compose.valskinapp.screen.detail.DetailViewModel
import com.compose.valskinapp.screen.detail.DetailViewModelFactory
import com.compose.valskinapp.ui.theme.ValSkinAppTheme

@Composable
fun DetailApp(
    modifier: Modifier = Modifier,
    idSkin: String,
    navController: NavHostController,
    detailViewModel: DetailViewModel = viewModel(factory = DetailViewModelFactory(SkinRepository())),
) {
    val skinData by detailViewModel.getSkinData(idSkin).collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(skinData.photoUrl),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack, contentDescription = "Back"
                    )
                }
            }
            Divider(
                color = MaterialTheme.colors.onBackground, thickness = 5.dp
            )
            Text(
                text = skinData.name,
                style = MaterialTheme.typography.h2,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            )
            Divider(
                color = MaterialTheme.colors.onBackground, thickness = 5.dp
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Divider(
                    color = MaterialTheme.colors.onBackground, thickness = 5.dp
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Price " + skinData.price,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailAppPreview() {
    ValSkinAppTheme {
        val navController = rememberNavController()
        DetailApp(idSkin = "1", navController = navController)
    }
}