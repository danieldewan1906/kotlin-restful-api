package programmer.zaman.now.kotlin.restful.dto.response

data class SupplierResponseDto (

    val id: Int,
    val supplierName: String,
    val address: String,
    val phone: String,
    val fax: Long

)