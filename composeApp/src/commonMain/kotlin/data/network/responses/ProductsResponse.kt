package data.network.responses

import domain.model.Product
import domain.model.ProductList
import kotlinx.serialization.Serializable

@Serializable
internal data class ProductsResponse(
    val limit: Int,
    val products: List<ProductDTO>,
    val skip: Int,
    val total: Int,
)

@Serializable
internal data class ProductDTO(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String,
)

internal fun ProductDTO.toDomainModel() = Product(
    brand, category, description, discountPercentage,
    id, images, price, rating, stock, thumbnail, title,
)

internal fun ProductsResponse.toDomainModel() = ProductList(
    limit,
    products.map { it.toDomainModel() },
    skip,
    total,
)
