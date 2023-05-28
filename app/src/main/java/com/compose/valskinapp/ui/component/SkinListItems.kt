package com.compose.valskinapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.compose.valskinapp.ui.theme.ValSkinAppTheme

@Composable
fun SkinListItem(
    name: String,
    photoUrl: String,
    price: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(12.dp)

    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 8.dp)
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = price,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.body2,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SkinListItemPreview() {
    ValSkinAppTheme {
        SkinListItem(
            name = "BlastX Bundle",
            photoUrl = "https://raw.githubusercontent.com/NoisyBoy29/image/master/app/src/main/res/drawable/blastx.jpg",
            price = "7200 VP"
        )
    }
}
