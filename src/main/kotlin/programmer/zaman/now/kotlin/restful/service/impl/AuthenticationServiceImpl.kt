package programmer.zaman.now.kotlin.restful.service.impl

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import programmer.zaman.now.kotlin.restful.auth.jwt.JwtUtils
import programmer.zaman.now.kotlin.restful.auth.service.UserDetailsImpl
import programmer.zaman.now.kotlin.restful.dto.request.CreateUserRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.LoginRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.LoginResponseDto
import programmer.zaman.now.kotlin.restful.entity.User
import programmer.zaman.now.kotlin.restful.service.AuthenticationService
import programmer.zaman.now.kotlin.restful.service.UserService
import java.util.UUID
import javax.servlet.http.Cookie

@Service
class AuthenticationServiceImpl(
    val authenticationManager: AuthenticationManager,
    val userService: UserService,
    val jwtUtils: JwtUtils,
    val passwordEncoder: PasswordEncoder
) : AuthenticationService {

    override fun signIn(loginRequestDto: LoginRequestDto): LoginResponseDto {
        val token: String = jwtUtils.generateJwtToken(loginRequestDto)
        val user: User = userService.getUserByUser(loginRequestDto.username)
        return LoginResponseDto(
            token = token,
            username = user.username,
            role = user.role
        )
    }

    override fun signUp(createUserRequestDto: CreateUserRequestDto) {
        val user: User = User(
            id = null,
            firstname = createUserRequestDto.firstname,
            lastname = createUserRequestDto.lastname,
            username = createUserRequestDto.username,
            password = passwordEncoder.encode(createUserRequestDto.password),
            role = "Customer"
        )
        userService.createUser(user)
    }
}