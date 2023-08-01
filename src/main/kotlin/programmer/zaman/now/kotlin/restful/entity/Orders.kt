package programmer.zaman.now.kotlin.restful.entity

import programmer.zaman.now.kotlin.restful.dto.enum.StatusOrder
import java.io.Serializable
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "Orders")
data class Orders (

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(name = "OrderNumber")
    var orderNumber: String,

    @Column(name = "OrderDate")
    @Temporal(TemporalType.DATE)
    var orderDate: Date,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Username", referencedColumnName = "Username", nullable = false)
    var users: User,

    @Column(name = "Address")
    var address: String,

    @Column(name = "TotalTransaction")
    var transactionTotal: Long?,

    @Column(name = "ShipCost")
    var shipCost: Long,

    @Column(name = "TotalOrder")
    var orderTotal: Long?,

    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    var status: StatusOrder,

    @Column(name = "orderTime")
    @Temporal(TemporalType.DATE)
    var orderTime: Date

) : Serializable