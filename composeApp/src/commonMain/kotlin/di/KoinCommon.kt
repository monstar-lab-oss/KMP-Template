package di

import data.db.DBHelper
import data.network.ApiService
import data.repository.FavoriteRepositoryImpl
import data.network.ApiServiceImpl
import data.repository.AuthRepositoryImpl
import data.repository.ProductPagingSource
import data.repository.ProductRepositoryImpl
import domain.repository.FavoriteRepository
import domain.repository.AuthRepository
import domain.repository.ProductRepository
import domain.usecase.favorite.AddToFavoriteUseCase
import domain.usecase.favorite.GetFavoritesUseCase
import domain.usecase.favorite.RemoveFromFavoriteUseCase
import domain.usecase.product.GetPaginatedProductsUseCase
import domain.usecase.product.GetProductsUseCase
import domain.usecase.LoginUseCase
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

object Modules {
    val services = module {
        single<ApiService> { ApiServiceImpl(get()) }
        single { DBHelper(get()) }
    }

    val repositories = module {
        factory { ProductPagingSource(get()) }
        factory<ProductRepository> { ProductRepositoryImpl(get()) }
        factory<AuthRepository> { AuthRepositoryImpl(get()) }
        factory<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
    }

    val useCases = module {
        factory { GetPaginatedProductsUseCase(get()) }
        factory { GetProductsUseCase(get()) }
        factory { GetFavoritesUseCase(get()) }
        factory { AddToFavoriteUseCase(get()) }
        factory { RemoveFromFavoriteUseCase(get()) }
        factory { LoginUseCase(get()) }
    }
}

fun initKoin(
    appModule: Module = module { },
    networkModule: Module = NetworkModule.networkClient,
    servicesModule: Module = Modules.services,
    repositoriesModule: Module = Modules.repositories,
    useCasesModule: Module = Modules.useCases,
): KoinApplication = startKoin {
    modules(
        appModule,
        networkModule,
        servicesModule,
        repositoriesModule,
        useCasesModule,
        platformModule
    )
}
