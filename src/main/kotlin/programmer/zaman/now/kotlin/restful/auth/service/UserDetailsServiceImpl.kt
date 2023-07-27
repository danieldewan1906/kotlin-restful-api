package programmer.zaman.now.kotlin.restful.auth.service

import lombok.NoArgsConstructor
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import programmer.zaman.now.kotlin.restful.auth.jwt.JwtUtils
import programmer.zaman.now.kotlin.restful.entity.User
import programmer.zaman.now.kotlin.restful.error.NotFoundException
import programmer.zaman.now.kotlin.restful.repository.UserRepository
import programmer.zaman.now.kotlin.restful.service.UserService

@Service
class UserDetailsServiceImpl(
    val userService : UserService
) : UserDetailsService {

    private val userDetailsImpl = UserDetailsImpl()

    override fun loadUserByUsername(username: String?): UserDetails {
        val user: User = userService.getUserByUser(username!!)
        return userDetailsImpl.build(user)
    }
}