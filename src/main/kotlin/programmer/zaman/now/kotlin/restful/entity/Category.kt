package programmer.zaman.now.kotlin.restful.entity

import net.bytebuddy.dynamic.loading.InjectionClassLoader.Strategy
import javax.persistence.*

@Entity
@Table(name = "Category")
data class Category (

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(name = "CategoryName", length = 100)
    var name: String

)