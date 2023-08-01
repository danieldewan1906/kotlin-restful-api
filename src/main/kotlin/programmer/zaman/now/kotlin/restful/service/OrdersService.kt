package programmer.zaman.now.kotlin.restful.service

import programmer.zaman.now.kotlin.restful.dto.request.OrdersRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.OrdersResponse

interface OrdersService {

    fun createOrder(username: String, orderRequest: OrdersRequestDto): OrdersResponse
}