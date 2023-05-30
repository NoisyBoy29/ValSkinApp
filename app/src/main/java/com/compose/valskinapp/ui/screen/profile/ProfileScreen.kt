package com.compose.valskinapp.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose.valskinapp.R
import com.compose.valskinapp.ui.theme.ValSkinAppTheme

@Composable
fun ProfileScreen(
    navController: NavHostController,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Image(
                painter = painterResource(R.drawable.fotonaufal),
                contentDescription = "Profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = "Naufal Maulana Al-Ghifari Irawan",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Irawanaufal29209@gmail.com",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ValSkinAppTheme {
        val navController = rememberNavController()
        ProfileScreen(navController = navController)
    }
}
