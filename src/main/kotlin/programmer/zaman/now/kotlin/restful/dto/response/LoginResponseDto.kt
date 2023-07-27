package programmer.zaman.now.kotlin.restful.dto.response

data class LoginResponseDto (

    val token: String,
    val type: String = "Bearer",
    val username: String,
    val role: String

)