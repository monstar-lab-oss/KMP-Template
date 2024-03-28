package data.repository

import app.cash.paging.PagingSource
import app.cash.paging.PagingSourceLoadParams
import app.cash.paging.PagingSourceLoadResult
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import app.cash.paging.PagingState
import data.network.ApiService
import data.network.responses.ProductsResponse
import data.network.responses.toDomainModel
import domain.model.Product
import io.ktor.client.call.body
import io.ktor.http.isSuccess

class ProductPagingSource(private val apiService: ApiService) :
    PagingSource<Int, Product>() {

    override suspend fun load(params: PagingSourceLoadParams<Int>):
        PagingSourceLoadResult<Int, Product> {
        val page = params.key ?: FIRST_PAGE_INDEX
        val httpResponse = apiService.getProducts(skip = page * 10)
        return when {
            httpResponse.status.isSuccess() -> {
                val productList = httpResponse.body<ProductsResponse>().toDomainModel()
                PagingSourceLoadResultPage(
                    data = productList.products,
                    prevKey = (page - 1).takeIf { it >= FIRST_PAGE_INDEX },
                    nextKey = if (productList.products.isNotEmpty()) page + 1 else null,
                )
            }

            else -> {
                PagingSourceLoadResultError(Exception("Received a ${httpResponse.status}."))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? = null

    companion object {
        const val FIRST_PAGE_INDEX = 0
    }
}
