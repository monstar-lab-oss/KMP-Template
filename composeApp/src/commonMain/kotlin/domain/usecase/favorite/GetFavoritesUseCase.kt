package domain.usecase.favorite

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetFavoritesUseCase(private val repository: FavoriteRepository) {
    @NativeCoroutines
    operator fun invoke() = flow { emit(runCatching { repository.getFavorites() }) }
        .flowOn(Dispatchers.Default)
}
