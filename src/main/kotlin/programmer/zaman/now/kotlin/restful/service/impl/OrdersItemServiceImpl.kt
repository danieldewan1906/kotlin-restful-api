package programmer.zaman.now.kotlin.restful.service.impl

import org.springframework.stereotype.Service
import programmer.zaman.now.kotlin.restful.entity.OrdersItem
import programmer.zaman.now.kotlin.restful.repository.OrdersItemRepository
import programmer.zaman.now.kotlin.restful.service.OrdersItemService

@Service
class OrdersItemServiceImp(
    val ordersItemRepository: OrdersItemRepository
) : OrdersItemService {

    override fun createOrderItems(ordersItem: OrdersItem) {
        ordersItemRepository.save(ordersItem)
    }
}