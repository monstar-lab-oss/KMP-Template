package domain.usecase.product

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import data.repository.ProductPagingSource
import domain.model.Product

class GetPaginatedProductsUseCase(private val pagingSource: ProductPagingSource) {
    private val pager: Pager<Int, Product> = run {
        val pagingConfig = PagingConfig(pageSize = 10, initialLoadSize = 10)
        check(pagingConfig.pageSize == pagingConfig.initialLoadSize) {
            "An elegant PagingSource implementation requires each page to be of equal size."
        }
        Pager(pagingConfig) { pagingSource }
    }

    operator fun invoke() = pager.flow
}
