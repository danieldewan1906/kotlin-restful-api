package programmer.zaman.now.kotlin.restful.dto.request

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateProductRequestDto (

    @field:NotBlank
    val name: String?,

    @field:NotNull
    val categoryId: Int?,

    @field:NotNull
    @field:Min(value = 1)
    val price: Long?,

    @field:NotNull
    @field:Min(value = 0)
    val quantity: Int?
)