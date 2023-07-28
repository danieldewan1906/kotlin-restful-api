package programmer.zaman.now.kotlin.restful.dto.response

import java.util.*

data class ProductResponse (
    val id: Int,
    val name: String,
    val price: Long,
    val quantity: Int,
    val createdAt: Date,
    val updatedAt: Date?
)