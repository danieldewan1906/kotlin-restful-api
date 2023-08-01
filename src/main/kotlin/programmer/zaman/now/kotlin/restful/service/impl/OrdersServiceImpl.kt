package programmer.zaman.now.kotlin.restful.service.impl

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import programmer.zaman.now.kotlin.restful.dto.enum.StatusOrder
import programmer.zaman.now.kotlin.restful.dto.request.CartRequest
import programmer.zaman.now.kotlin.restful.dto.request.OrdersRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.UpdateProductRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.OrdersResponse
import programmer.zaman.now.kotlin.restful.entity.Orders
import programmer.zaman.now.kotlin.restful.entity.OrdersItem
import programmer.zaman.now.kotlin.restful.entity.Product
import programmer.zaman.now.kotlin.restful.entity.User
import programmer.zaman.now.kotlin.restful.error.BadRequestException
import programmer.zaman.now.kotlin.restful.repository.OrdersRepository
import programmer.zaman.now.kotlin.restful.service.*
import programmer.zaman.now.kotlin.restful.validation.MapperUtils
import java.util.*

@Service
class OrdersServiceImpl(
    val ordersRepository: OrdersRepository,
    val userService: UserService,
    val productService: ProductService,
    val ordersItemService: OrdersItemService,
    val cartService: CartService
) : OrdersService {

    @Transactional
    override fun createOrder(username: String, orderRequest: OrdersRequestDto): OrdersResponse {
        val user = checkUser(username)
        val order = Orders(
            id = null,
            orderNumber = generateOrderNumber(),
            orderDate = Date(),
            users = user,
            address = orderRequest.address,
            status = StatusOrder.DRAFT,
            orderTime = Date(),
            shipCost = orderRequest.shipCost,
            orderTotal = null,
            transactionTotal = null
        )

        var items: List<OrdersItem> = listOf()
        for (cartRequest: CartRequest in orderRequest.items) {
            // check product
            val product = checkProduct(cartRequest.product)

            if (product.quantity < cartRequest.quantity) {
                throw BadRequestException("Stock Product is not Enough")
            }

            val orderItem = OrdersItem(
                id = null,
                orders = order,
                products = product,
                quantity = cartRequest.quantity,
                price = product.price,
                total = product.price * cartRequest.quantity
            )
            items = listOf(orderItem)
        }

        var total: Long = 0
        for (orderItems : OrdersItem in items) {
            total = orderItems.total
        }

        order.orderTotal = total
        order.transactionTotal = order.orderTotal!! + order.shipCost
        ordersRepository.save(order)

        for (orderItems: OrdersItem in items) {
            ordersItemService.createOrderItems(orderItems)
            val product = orderItems.products
            product.quantity = product.quantity - orderItems.quantity
            val updateProduct = UpdateProductRequestDto(
                name = product.name,
                category = product.categories.id,
                supplier = product.suppliers.id,
                price = product.price,
                quantity = product.quantity
            )
            productService.updateProduct(product.id!!, updateProduct!!)
            cartService.deleteCart(username, product.name)
        }
        return orderResponse(order)
    }

    private fun generateOrderNumber(): String {
        return String.format("%016d", System.nanoTime())
    }

    private fun checkUser(username: String): User {
        return userService.getUserByUser(username)
    }

    private fun checkProduct(productId: Int): Product {
        val productResponse = productService.getProductById(productId)
        val product = MapperUtils().map(productResponse, Product::class.java)
        return product!!
    }

    private fun orderResponse(orders: Orders) : OrdersResponse {
        return OrdersResponse(
            orderNumber = orders.orderNumber,
            orderDate = orders.orderDate,
            users = orders.users,
            address = orders.address,
            totalTransaction = orders.transactionTotal!!,
            shipCost = orders.shipCost,
            totalOrder = orders.orderTotal!!,
            status = orders.status,
            orderTime = orders.orderTime
        )
    }
}