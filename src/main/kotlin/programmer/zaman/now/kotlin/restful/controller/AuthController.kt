package programmer.zaman.now.kotlin.restful.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import programmer.zaman.now.kotlin.restful.dto.request.CreateUserRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.LoginRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.LoginResponseDto
import programmer.zaman.now.kotlin.restful.entity.User
import programmer.zaman.now.kotlin.restful.service.AuthenticationService
import programmer.zaman.now.kotlin.restful.service.UserService

@RestController
@RequestMapping("/auth")
class AuthController(
    val authenticationServicec: AuthenticationService
) {

    @PostMapping(
        value = ["/signin"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun login(@RequestBody loginRequestDto: LoginRequestDto): LoginResponseDto {
        return authenticationServicec.signIn(loginRequestDto)
    }

    @PostMapping(
        value = ["/signup"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun signup(@RequestBody createUserRequestDto: CreateUserRequestDto): ResponseEntity<CreateUserRequestDto> {
        authenticationServicec.signUp(createUserRequestDto)
        return ResponseEntity(
            HttpStatus.OK
        )
    }
}