package programmer.zaman.now.kotlin.restful.dto.response

import programmer.zaman.now.kotlin.restful.dto.enum.StatusOrder
import programmer.zaman.now.kotlin.restful.entity.User
import java.util.Date

data class OrdersResponse (

    val orderNumber: String,
    val orderDate: Date,
    val users: User,
    val address: String,
    val totalTransaction: Long,
    val shipCost: Long,
    val totalOrder: Long,
    val status: StatusOrder,
    val orderTime: Date

)