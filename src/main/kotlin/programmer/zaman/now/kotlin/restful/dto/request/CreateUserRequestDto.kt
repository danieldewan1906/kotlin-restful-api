package programmer.zaman.now.kotlin.restful.dto.request

data class CreateUserRequestDto (

    val firstname: String,
    val lastname: String,
    val username: String,
    val password: String
)