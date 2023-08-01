package programmer.zaman.now.kotlin.restful.dto.request

data class OrdersRequestDto (

    val shipCost: Long,
    val address: String,
    val items: List<CartRequest>
)