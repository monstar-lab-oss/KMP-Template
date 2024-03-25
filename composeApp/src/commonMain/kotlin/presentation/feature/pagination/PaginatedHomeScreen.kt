package presentation.feature.pagination

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import presentation.theme.Theme
import presentation.feature.home.ProductItemView

object PaginatedHomeScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = PaginatedHomeViewModel()
        val productList = viewModel.productList.collectAsLazyPagingItems()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Products",
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
                    when (val loadState = productList.loadState.refresh) {
                        is LoadStateLoading -> {
                            item { CircularProgressIndicator() }
                        }

                        is LoadStateNotLoading -> {
                            items(productList.itemCount) { index ->
                                val product = productList[index]
                                product?.let {
                                    ProductItemView(it, false, {}, {})
                                }
                            }
                        }

                        is LoadStateError -> {
                            item {
                                Text(loadState.error.message!!)
                            }
                        }

                        else -> error("when should be exhaustive")
                    }
                }
            }
        }
    }
}
