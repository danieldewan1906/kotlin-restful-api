package programmer.zaman.now.kotlin.restful.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


data class SupplierRequestDto (

    @field:NotBlank
    val supplierName: String?,

    @field:NotBlank
    val address: String?,

    @field:NotBlank
    val phone: String?,

    val fax: Long

)