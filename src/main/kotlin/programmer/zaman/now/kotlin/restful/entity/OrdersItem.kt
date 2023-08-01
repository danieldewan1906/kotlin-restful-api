package programmer.zaman.now.kotlin.restful.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "OrdersItem")
data class OrdersItem (

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderNumber", referencedColumnName = "OrderNumber", nullable = false)
    var orders: Orders,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductName", referencedColumnName = "ProductName", nullable = false)
    var products: Product,

    @Column(name = "Qty")
    var quantity: Int,

    @Column(name = "Price")
    var price: Long,

    @Column(name = "Total")
    var total: Long

) : Serializable