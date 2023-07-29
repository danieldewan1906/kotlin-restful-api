package programmer.zaman.now.kotlin.restful.service

import programmer.zaman.now.kotlin.restful.dto.request.ListRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.SupplierRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.SupplierResponseDto

interface SuppliersService {

    fun createSupplier(supplierRequestDto: SupplierRequestDto): SupplierResponseDto

    fun editSupplier(id: Int, supplierRequestDto: SupplierRequestDto): SupplierResponseDto

    fun deleteSupplier(id: Int)

    fun getSupplierById(id: Int): SupplierResponseDto

    fun getAllSupplier(listRequestDto: ListRequestDto): List<SupplierResponseDto>
}