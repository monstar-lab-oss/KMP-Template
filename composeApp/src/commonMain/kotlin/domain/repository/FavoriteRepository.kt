package domain.repository

import domain.model.Product

interface FavoriteRepository {
    suspend fun getFavorites(): List<Product>

    suspend fun addToFavorite(product: Product)

    suspend fun removeFromFavorite(product: Product)
}
