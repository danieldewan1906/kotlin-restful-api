package programmer.zaman.now.kotlin.restful.entity

import java.io.Serializable
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "Cart")
data class Cart (

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductName", referencedColumnName = "ProductName", nullable = false)
    var products: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Username", referencedColumnName = "Username", nullable = false)
    var users: User,

    @Column(name = "Qty")
    var quantity: Int,

    @Column(name = "Price")
    var price: Long,

    @Column(name = "Total")
    var total: Long,

    @Column(name = "TimeMade")
    var timeMade: Date

) : Serializable {
    constructor(): this(null,
        Product(null, "",
            Category(null, ""),
            Suppliers(null, "", "", "", ""), 0, 0, Date(), Date()),
        User(null, "", "", "", "", ""),
        0, 0, 0, Date()) {
    }
}