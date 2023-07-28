package programmer.zaman.now.kotlin.restful.auth.jwt

import io.jsonwebtoken.Jwt
import lombok.NoArgsConstructor
import lombok.RequiredArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import programmer.zaman.now.kotlin.restful.auth.service.UserDetailsImpl
import programmer.zaman.now.kotlin.restful.auth.service.UserDetailsServiceImpl
import programmer.zaman.now.kotlin.restful.service.UserService
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthTokenFilter(
    val jwtUtils: JwtUtils,
    val userDetailsServiceImpl: UserDetailsServiceImpl
) : OncePerRequestFilter() {

    private val logger: Logger = LoggerFactory.getLogger(AuthTokenFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val jwt: String? = parsetJwt(request)
            if (jwt != null && jwtUtils.validateJwtToken(jwt!!)) {
                val username: String = jwtUtils.getUsernameFromToken(jwt)
                val userDetails: UserDetails = userDetailsServiceImpl.loadUserByUsername(username)
                val authentication: UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            val message: String? = e.message
            logger.error(message)
        }

        filterChain.doFilter(request, response)
    }

    fun parsetJwt(request: HttpServletRequest): String? {
        val headerAuth: String = request.getHeader("Authorization")
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length)
        }
        return null
    }
}