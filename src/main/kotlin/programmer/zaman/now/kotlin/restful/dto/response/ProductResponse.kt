package programmer.zaman.now.kotlin.restful.dto.response

import programmer.zaman.now.kotlin.restful.entity.Category
import programmer.zaman.now.kotlin.restful.entity.Suppliers
import java.util.*

data class ProductResponse (
    val id: Int,
    val name: String,
    val category: Category,
    val suppliers: Suppliers,
    val price: Long,
    val quantity: Int,
    val createdAt: Date,
    val updatedAt: Date?
)