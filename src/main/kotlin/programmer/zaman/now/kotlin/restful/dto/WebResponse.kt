package programmer.zaman.now.kotlin.restful.dto

data class WebResponse<T> (
    val code: Int,
    val status: String,
    val data: T
)