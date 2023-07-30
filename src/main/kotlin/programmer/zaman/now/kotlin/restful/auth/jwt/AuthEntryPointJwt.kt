package programmer.zaman.now.kotlin.restful.auth.jwt

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ControllerAdvice
import programmer.zaman.now.kotlin.restful.error.UnauthorizedException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class AuthEntryPointJwt : AuthenticationEntryPoint{

    private final val logger: Logger = LoggerFactory.getLogger(AuthEntryPointJwt::class.java)

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        logger.info("Inside Rest Authentication Entry Point")
        var error: String = "{ \"status\":\"FAILURE\",\"error\":{\"code\":\"401\",\"message\":\"" + authException?.message+ "\"} }"
        response?.status = HttpServletResponse.SC_UNAUTHORIZED
        response?.contentType = "application/json"

        if (authException is BadCredentialsException) {
            response?.outputStream?.println("{ \"Bad credential\": \"" + authException.message + "\" }")
        }
        if (authException is AuthenticationCredentialsNotFoundException) {
            logger.info("Inside AuthenticationCredentialsNotFoundException")
            error = "{ \"status\":\"FAILURE\",\"error\":{\"code\":\"" + "TOKEN_EXPIRED" + "\",\"message\":\"" + "TOKEN_EXPIRED" + "\"} }"
        }
        response?.outputStream?.println(error)

    }
}