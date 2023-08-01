package programmer.zaman.now.kotlin.restful.entity

import java.io.Serializable
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "Products")
data class Product (

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(name = "ProductName")
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryName", referencedColumnName = "CategoryName", nullable = false)
    var categories: Category,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SupplierName", referencedColumnName = "SupplierName", nullable = false)
    var suppliers: Suppliers,

    @Column(name = "Price")
    var price: Long,

    @Column(name = "Quantity")
    var quantity: Int,

    @Column(name = "CreatedDate")
    var createdAt: Date,

    @Column(name = "UpdatedDate")
    var updatedAt: Date?
) : Serializable