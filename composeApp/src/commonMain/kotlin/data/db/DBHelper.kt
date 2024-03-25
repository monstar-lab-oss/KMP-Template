package data.db

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import com.monstarlab.kmp.ProductDB
import com.monstarlab.kmp.ProductDatabase
import domain.model.Product

class DBHelper(sqlDriver: SqlDriver) {
    private val listOfStringsAdapter = object : ColumnAdapter<List<String>, String> {
        override fun decode(databaseValue: String) =
            if (databaseValue.isEmpty()) {
                listOf()
            } else {
                databaseValue.split(",")
            }

        override fun encode(value: List<String>) = value.joinToString(separator = ",")
    }

    private val dbRef = ProductDatabase(sqlDriver, ProductDB.Adapter(listOfStringsAdapter))

    fun insertProduct(product: Product) =
        dbRef.productQueries.insertProduct(product.toDatabaseModel())

    fun fetchAllProducts() =
        dbRef.productQueries
            .selectAll()
            .executeAsList().map { it.toDomainModel() }

    fun deleteProduct(productId: Int) =
        dbRef.productQueries.deleteProduct(productId.toLong())

    private fun ProductDB.toDomainModel() = Product(
        brand,
        category,
        description,
        discountPercentage,
        id.toInt(),
        images,
        price.toInt(),
        rating,
        stock.toInt(),
        thumbnail,
        title
    )

    private fun Product.toDatabaseModel() = ProductDB(
        id.toLong(),
        brand,
        category,
        description,
        discountPercentage,
        rating,
        price.toLong(),
        stock.toLong(),
        thumbnail,
        title,
        images
    )
}
