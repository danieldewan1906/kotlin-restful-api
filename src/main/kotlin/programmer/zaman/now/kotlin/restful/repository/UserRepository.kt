package programmer.zaman.now.kotlin.restful.repository

import org.springframework.data.jpa.repository.JpaRepository
import programmer.zaman.now.kotlin.restful.entity.User
import java.util.*

interface UserRepository : JpaRepository<User, Int> {
    fun findByUsername(username: String?): Optional<User>
}