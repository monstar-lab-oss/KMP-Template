package data.repository

import data.db.DBHelper
import domain.model.Product
import domain.repository.FavoriteRepository

class FavoriteRepositoryImpl(private val dbHelper: DBHelper): FavoriteRepository {
    override suspend fun getFavorites(): List<Product> = dbHelper.fetchAllProducts()

    override suspend fun addToFavorite(product: Product) = dbHelper.insertProduct(product)

    override suspend fun removeFromFavorite(product: Product) = dbHelper.deleteProduct(product.id)
}
