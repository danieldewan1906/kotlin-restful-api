package programmer.zaman.now.kotlin.restful.dto.request

data class CreateUserRequestDto (

    val username: String,
    val password: String,
    val role: String
)