package programmer.zaman.now.kotlin.restful.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Suppliers")
data class Suppliers (

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(name = "SupplierName")
    var supplierName: String,

    @Column(name = "Address")
    var address: String,

    @Column(name = "Phone")
    var phone: String,

    @Column(name = "Fax", nullable = true)
    var fax: Long?
) : Serializable