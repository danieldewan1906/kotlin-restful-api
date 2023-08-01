package programmer.zaman.now.kotlin.restful.dto.response

import programmer.zaman.now.kotlin.restful.entity.Product
import programmer.zaman.now.kotlin.restful.entity.User
import java.util.*

data class CartResponseDto (

    var products: Product,
    var users: User,
    var quantity: Int,
    var price: Long,
    var total: Long,
    var timeMade: Date

)