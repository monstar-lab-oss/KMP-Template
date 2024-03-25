package presentation.feature.pagination

import androidx.paging.cachedIn
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import domain.usecase.product.GetPaginatedProductsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PaginatedHomeViewModel : KMMViewModel(), KoinComponent {
    private val getPaginatedProductsUseCase: GetPaginatedProductsUseCase by inject()

    val productList = getPaginatedProductsUseCase().cachedIn(viewModelScope.coroutineScope)
}
