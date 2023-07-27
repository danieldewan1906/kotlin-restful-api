package programmer.zaman.now.kotlin.restful.auth.jwt

import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import programmer.zaman.now.kotlin.restful.dto.request.LoginRequestDto
import java.util.*

@Component
class JwtUtils {

    private val logger = LoggerFactory.getLogger(JwtUtils::class.java)

    @Value("\${jwt.expirationMs}")
    lateinit var jwtExpirationMs: String

    @Value("\${jwt.refreshExpirationMs}")
    lateinit var refreshJwtExpirationMs: String

    @Value("\${jwt.secret}")
    lateinit var jwtSecret: String

    fun validateJwtToken(token: String): Boolean {
        println(jwtExpirationMs)
        try {
            println("test")
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT Signature: {}", e.message);
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT Token: {}", e.message);
        } catch (e: ExpiredJwtException) {
            logger.error("JWT Token Expired: {}", e.message);
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT Token is Unsupported: {}", e.message);
        } catch (e: IllegalArgumentException) {
            logger.error("JWT Claims String is Empty: {}", e.message);
        }
        return false;
    }

    fun generateJwtToken(loginRequestDto: LoginRequestDto): String {
        return Jwts.builder().setSubject((loginRequestDto.username))
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + jwtExpirationMs.toInt()))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun getUsernameFromToken(token : String): String {
        return Jwts.parser().setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
            .subject
    }
}