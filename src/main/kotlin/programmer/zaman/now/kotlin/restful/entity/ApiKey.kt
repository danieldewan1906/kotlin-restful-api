package programmer.zaman.now.kotlin.restful.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ApiKeys")
data class ApiKey (

    @Id
    val id: String
)