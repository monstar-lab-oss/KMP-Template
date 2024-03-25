package domain.usecase.favorite

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import domain.model.Product
import domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AddToFavoriteUseCase(private val repository: FavoriteRepository) {
    @NativeCoroutines
    operator fun invoke(product: Product) = flow {
        emit(runCatching { repository.addToFavorite(product) })
    }
        .flowOn(Dispatchers.Default)
}
