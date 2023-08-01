package programmer.zaman.now.kotlin.restful.service

import programmer.zaman.now.kotlin.restful.dto.response.CartResponseDto
import programmer.zaman.now.kotlin.restful.entity.Cart

interface CartService {

    fun addToCart(username: String, prodId: Int, qty: Int) : CartResponseDto

    fun updateCart(username: String, productName: String, qty: Int) : CartResponseDto

    fun deleteCart(username: String, productName: String)
}