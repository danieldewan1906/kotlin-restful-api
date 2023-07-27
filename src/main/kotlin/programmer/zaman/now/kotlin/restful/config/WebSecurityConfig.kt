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
    val unauthorizedHandler: AuthEntryPointJwt
    ) {

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests().antMatchers("/auth/**").permitAll()
            .anyRequest().authenticated()
        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun authTokenFilter(): AuthTokenFilter {
        return AuthTokenFilter()
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