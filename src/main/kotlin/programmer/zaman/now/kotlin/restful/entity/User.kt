package programmer.zaman.now.kotlin.restful.entity

import javax.persistence.*

@Entity
@Table(name = "User")
data class User (

    @Id
    @Column(name = "Id")
    val id: String,

    @Column(name = "Username")
    val username: String,

    @Column(name = "Password")
    val password: String,

    @Column(name = "Role")
    val role: String
)