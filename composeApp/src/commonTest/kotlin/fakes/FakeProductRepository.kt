package fakes

import domain.repository.ProductRepository

internal class FakeProductRepository(private val isSuccess: Boolean) : ProductRepository {
    override suspend fun getProducts() =
        if (isSuccess) FakeDataSource.productList else throw Exception("No Data")
}
