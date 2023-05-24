package com.compose.valskinapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.compose.valskinapp.data.SkinData
import com.compose.valskinapp.ui.theme.ValSkinAppTheme

@Composable
fun ValSkinApp(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        LazyColumn {
            items(SkinData.skins, key = { it.id }) { skin ->
                SkinListItem(
                    name = skin.name,
                    photoUrl = skin.photoUrl,
                    modifier = Modifier.fillMaxWidth()
                )
            }
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

//List
@Composable
fun SkinListItem(
    name: String,
    photoUrl: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {}
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(
            text = name,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SkinListItemPreview() {
    ValSkinAppTheme() {
        SkinListItem(
            name = "BlastX Bundle",
            photoUrl = "https://raw.githubusercontent.com/NoisyBoy29/image/master/app/src/main/res/drawable/blastx.jpg"
        )
    }
}

