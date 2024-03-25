package fakes

import domain.model.Product
import domain.repository.FavoriteRepository

internal class FakeFavoriteRepository(private val isSuccess: Boolean) : FavoriteRepository {
    override suspend fun getFavorites() =
        if (isSuccess) listOf(FakeDataSource.product) else throw Exception("Error")

    override suspend fun addToFavorite(product: Product) =
        if (isSuccess) Unit else throw Exception("Error")

    override suspend fun removeFromFavorite(product: Product) =
        if (isSuccess) Unit else throw Exception("Error")
}
