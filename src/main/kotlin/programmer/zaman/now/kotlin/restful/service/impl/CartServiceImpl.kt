package programmer.zaman.now.kotlin.restful.service.impl

import org.springframework.stereotype.Service
import programmer.zaman.now.kotlin.restful.dto.response.CartResponseDto
import programmer.zaman.now.kotlin.restful.entity.Cart
import programmer.zaman.now.kotlin.restful.entity.Product
import programmer.zaman.now.kotlin.restful.entity.User
import programmer.zaman.now.kotlin.restful.repository.CartRepository
import programmer.zaman.now.kotlin.restful.service.CartService
import programmer.zaman.now.kotlin.restful.service.ProductService
import programmer.zaman.now.kotlin.restful.service.UserService
import programmer.zaman.now.kotlin.restful.validation.MapperUtils
import java.util.*

@Service
class CartServiceImpl(
    val cartRepository: CartRepository,
    val productService: ProductService,
    val userService: UserService
) : CartService {

    override fun addToCart(username: String, prodId: Int, qty: Int): CartResponseDto {
        val product = checkProduct(prodId)
        val user = checkUser(username)
        val usernameQuery = "%"+username+"%"
        val productNameQuery = "%"+product.name+"%"
        val optional: Optional<Cart> = cartRepository
            .findByUsernameAndProductName(usernameQuery, productNameQuery)

        var cart = Cart();
        if (optional.isPresent) {
            cart = optional.get()
            cart.quantity = cart.quantity + qty
            cart.total = cart.price * cart.quantity
            cartRepository.save(cart)
        } else {
            cart = Cart(
                id = null,
                products = product,
                users = user,
                quantity = qty,
                price = product.price,
                total = product.price * qty,
                timeMade = Date()
            )
            cartRepository.save(cart)
        }
        return cartResponse(cart)
    }

    override fun updateCart(username: String, productName: String, qty: Int): CartResponseDto {
        val cart = cartRepository.findByUsernameAndProductName(username, productName).get()
        cart.quantity = qty
        cart.total = cart.price * cart.quantity
        cartRepository.save(cart)
        return cartResponse(cart)
    }

    override fun deleteCart(username: String, productName: String) {
        val cart = cartRepository.findByUsernameAndProductName(username, productName).get()
        cartRepository.delete(cart)
    }

    private fun checkProduct(prodId: Int) : Product {
        val response = productService.getProductById(prodId)
        val product = MapperUtils().map(response, Product::class.java)
        return product!!
    }

    private fun checkUser(username: String): User {
        return userService.getUserByUser(username)
    }

    private fun cartResponse(cart: Cart) : CartResponseDto {
        return CartResponseDto(
            products = cart.products,
            users = cart.users,
            quantity = cart.quantity,
            price = cart.price,
            total = cart.total,
            timeMade = cart.timeMade
        )
    }
}