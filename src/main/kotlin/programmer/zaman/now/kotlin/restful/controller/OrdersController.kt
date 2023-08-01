package programmer.zaman.now.kotlin.restful.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import programmer.zaman.now.kotlin.restful.auth.service.UserDetailsImpl
import programmer.zaman.now.kotlin.restful.dto.request.OrdersRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.OrdersResponse
import programmer.zaman.now.kotlin.restful.dto.response.WebResponse
import programmer.zaman.now.kotlin.restful.service.OrdersService

@RestController
@RequestMapping("/api")
class OrdersController(val ordersService: OrdersService) {

    @PostMapping(
        value = ["/orders"]
    )
    fun createOrder(@RequestBody ordersRequestDto: OrdersRequestDto, @AuthenticationPrincipal userDetailsImpl: UserDetailsImpl) : WebResponse<OrdersResponse> {
        val response = ordersService.createOrder(userDetailsImpl.username, ordersRequestDto)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }
}