package data.repository

import data.network.ApiService
import data.network.responses.ProductsResponse
import data.network.responses.toDomainModel
import domain.repository.ProductRepository
import io.ktor.client.call.body

class ProductRepositoryImpl(private val apiService: ApiService) : ProductRepository {
    override suspend fun getProducts() =
        apiService.getProducts().body<ProductsResponse>().toDomainModel()
}
