package programmer.zaman.now.kotlin.restful.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import programmer.zaman.now.kotlin.restful.auth.service.UserDetailsImpl
import programmer.zaman.now.kotlin.restful.dto.request.CartRequest
import programmer.zaman.now.kotlin.restful.dto.response.CartResponseDto
import programmer.zaman.now.kotlin.restful.dto.response.WebResponse
import programmer.zaman.now.kotlin.restful.service.CartService

@RestController
@RequestMapping("/api")
class CartController(val cartService: CartService) {

    @PostMapping(
        value = ["/cart"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun addToCart(@RequestBody cartRequest: CartRequest, @AuthenticationPrincipal userDetailsImpl: UserDetailsImpl): WebResponse<CartResponseDto> {
        val response = cartService.addToCart(userDetailsImpl.username, cartRequest.product, cartRequest.quantity)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }
}