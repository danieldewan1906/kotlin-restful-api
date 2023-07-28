package programmer.zaman.now.kotlin.restful.entity

import javax.persistence.*

@Entity
@Table(name = "User")
data class User (

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(name = "Username")
    val username: String,

    @Column(name = "Password")
    val password: String,

    @Column(name = "Role")
    val role: String
)