package programmer.zaman.now.kotlin.restful.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import programmer.zaman.now.kotlin.restful.entity.Cart
import java.util.*

interface CartRepository : JpaRepository<Cart, Int> {

    @Query(
        value = "select * from cart where username like :username and product_name like :product",
        nativeQuery = true
    )
    fun findByUsernameAndProductName(username: String, product: String): Optional<Cart>
}