package programmer.zaman.now.kotlin.restful.service

import programmer.zaman.now.kotlin.restful.dto.request.CreateUserRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.LoginRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.LoginResponseDto

interface AuthenticationService {

    fun signIn(loginRequestDto: LoginRequestDto): LoginResponseDto

    fun signUp(createUserRequestDto: CreateUserRequestDto)
}