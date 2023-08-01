package programmer.zaman.now.kotlin.restful.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
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
    @JsonIgnore
    val password: String,

    @Column(name = "Firstname")
    val firstname: String,

    @Column(name = "Lastname")
    val lastname: String,

    @Column(name = "Role")
    @JsonIgnore
    val role: String
) : Serializable