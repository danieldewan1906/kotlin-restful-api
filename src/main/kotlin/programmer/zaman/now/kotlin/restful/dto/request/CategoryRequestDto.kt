package programmer.zaman.now.kotlin.restful.dto.request

import javax.validation.constraints.NotBlank

data class CategoryRequestDto (

    @field:NotBlank
    val name: String?
)