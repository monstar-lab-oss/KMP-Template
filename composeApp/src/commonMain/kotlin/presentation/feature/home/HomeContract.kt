package presentation.feature.home

import domain.model.Product

data class HomeScreenState(
    val productList: List<Product> = emptyList(),
    val favoriteList: List<Product> = emptyList(),
    val isLoading: Boolean = false
)

sealed class HomeScreenIntent {
    data object OnLaunch : HomeScreenIntent()

    data class OnFavoriteClick(val product: Product) : HomeScreenIntent()
}
