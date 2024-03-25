package domain.usecase.product

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetProductsUseCase(private val repository: ProductRepository) {
    @NativeCoroutines
    operator fun invoke() = flow { emit(runCatching { repository.getProducts() }) }
        .flowOn(Dispatchers.Default)
}
