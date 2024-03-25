package presentation.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import presentation.theme.Theme

object HomeScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = HomeViewModel()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.handleIntent(HomeScreenIntent.OnLaunch)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Home",
                            fontWeight = FontWeight.Black,
                            style = MaterialTheme.typography.h5,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    backgroundColor = Theme.colors.primary,
                    contentColor = Theme.colors.onPrimary,
                )
            },
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                LazyColumn {
                    items(state.productList) { product ->
                        ProductItemView(
                            product = product,
                            isFavorite = state.favoriteList.contains(product),
                            onFavoriteClick = {
                                viewModel.handleIntent(HomeScreenIntent.OnFavoriteClick(product))
                            },
                            onClick = {
                                // Handle OnClick
                            }
                        )
                    }
                }

                if (state.isLoading) CircularProgressIndicator()
            }
        }
    }
}
