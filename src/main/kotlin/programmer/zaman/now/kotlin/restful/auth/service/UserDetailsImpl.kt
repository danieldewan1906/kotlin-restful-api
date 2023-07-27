package programmer.zaman.now.kotlin.restful.auth.service

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Data
import lombok.Getter
import lombok.Setter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.util.StringUtils
import programmer.zaman.now.kotlin.restful.entity.User

class UserDetailsImpl : UserDetails {

    var userName: String = ""
    @JsonIgnore
    var userPassword: String = ""
    var roles: String = ""

    constructor(usernameUser: String, passwordUser: String, role: String) {
        userName = usernameUser
        userPassword = passwordUser
        roles = role
    }

    constructor(){
    }

    fun build(user: User): UserDetailsImpl {
        return UserDetailsImpl(
            user.username,
            user.password,
            user.role
        )
    }

    fun get_Username(): String {
        return username
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        if (StringUtils.hasText(roles)) {
            val splits: List<String> = roles.replace(" ", "").split(",")
            for (key in splits) {
                authorities.add(SimpleGrantedAuthority(key))
            }
        }
        return authorities
    }

    override fun getPassword(): String {
        return userPassword
    }

    override fun getUsername(): String {
        return userName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}