package programmer.zaman.now.kotlin.restful.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import programmer.zaman.now.kotlin.restful.auth.jwt.AuthEntryPointJwt
import programmer.zaman.now.kotlin.restful.auth.jwt.AuthTokenFilter
import programmer.zaman.now.kotlin.restful.auth.jwt.JwtUtils
import programmer.zaman.now.kotlin.restful.auth.service.UserDetailsImpl
import programmer.zaman.now.kotlin.restful.auth.service.UserDetailsServiceImpl
import programmer.zaman.now.kotlin.restful.repository.UserRepository
import programmer.zaman.now.kotlin.restful.service.UserService


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(
    val unauthorizedHandler: AuthEntryPointJwt,
    val jwtUtils: JwtUtils,
    val userDetailsServiceImpl: UserDetailsServiceImpl
    ) {

    private val URL_WHITELIST = arrayOf(
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        "/v2/api-docs/**",
        "/api/public/**",
        "/api/public/authenticate",
        "/actuator/*",
        "/swagger-ui/**",
        "/auth/**"
    )

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests().antMatchers(*URL_WHITELIST).permitAll()
            .anyRequest().authenticated()
        http.addFilterBefore(AuthTokenFilter(jwtUtils, userDetailsServiceImpl), UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(authentication: AuthenticationConfiguration): AuthenticationManager {
        return authentication.authenticationManager
    }
}