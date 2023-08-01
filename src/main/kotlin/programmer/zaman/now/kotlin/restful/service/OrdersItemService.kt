package programmer.zaman.now.kotlin.restful.service

import programmer.zaman.now.kotlin.restful.entity.OrdersItem

interface OrdersItemService {

    fun createOrderItems(ordersItem: OrdersItem)
}