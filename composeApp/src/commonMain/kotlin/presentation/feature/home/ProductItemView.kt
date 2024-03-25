package presentation.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import domain.model.Product

@Composable
fun ProductItemView(
    product: Product,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        AsyncImage(
            model = product.thumbnail,
            contentDescription = product.title,
            modifier = Modifier.size(220.dp),
            contentScale = ContentScale.Crop,
        )

        Box(
            modifier = Modifier
                .size(220.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color(0xFF1F2023),
                        ),
                    ),
                ),
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = { onFavoriteClick() }
            ) {
                if (isFavorite)
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Favorite",
                        tint = Color(0xFFFFD81A),
                        modifier = Modifier
                            .size(44.dp)
                            .padding(8.dp)
                    )

                else
                    Icon(
                        Icons.Outlined.StarOutline,
                        contentDescription = "Not Favorite",
                        tint = Color(0xFFFFD81A),
                        modifier = Modifier
                            .size(44.dp)
                            .padding(8.dp)
                    )
            }

            Text(
                text = product.title,
                textAlign = TextAlign.Center,
                color = Color.White,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp),
            )
        }
    }
}
