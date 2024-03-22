package di

import data.network.ApiService
import data.network.ApiServiceImpl
import data.preference.createDataStore
import data.repository.AuthRepositoryImpl
import data.repository.ProductPagingSource
import data.repository.ProductRepositoryImpl
import domain.repository.AuthRepository
import domain.repository.ProductRepository
import domain.usecase.GetPaginatedProductsUseCase
import domain.usecase.GetProductsUseCase
import domain.usecase.GetUserFromPreferenceUseCase
import domain.usecase.LoginUseCase
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

object Modules {
    val appModule = module {
        single {
            createDataStore()
        }
    }
    val services = module {
        single<ApiService> { ApiServiceImpl(get()) }
    }

    val repositories = module {
        factory<ProductRepository> { ProductRepositoryImpl(get()) }
        factory<AuthRepository> { AuthRepositoryImpl(get()) }
        factory { ProductPagingSource(get()) }
    }

    val useCases = module {
        factory { GetProductsUseCase(get()) }
        factory { GetPaginatedProductsUseCase(get()) }
        factory { LoginUseCase(get()) }
        factory { GetUserFromPreferenceUseCase(get()) }
    }
}

fun initKoin(
    appModule: Module = Modules.appModule,
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
    )
}
