package programmer.zaman.now.kotlin.restful.dto.response

data class WebResponse<T> (
    val code: Int,
    val status: String,
    val data: T
)