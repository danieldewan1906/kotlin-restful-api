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
import programmer.zaman.now.kotlin.restful.dto.response.WebResponse
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
    fun login(@RequestBody loginRequestDto: LoginRequestDto): WebResponse<LoginResponseDto> {
        val response = authenticationServicec.signIn(loginRequestDto)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @PostMapping(
        value = ["/signup"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun signup(@RequestBody createUserRequestDto: CreateUserRequestDto): WebResponse<String> {
        authenticationServicec.signUp(createUserRequestDto)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "Successfully SignUp Your Account"
        )
    }
}