package programmer.zaman.now.kotlin.restful.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import programmer.zaman.now.kotlin.restful.dto.request.ListRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.SupplierRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.SupplierResponseDto
import programmer.zaman.now.kotlin.restful.entity.Suppliers
import programmer.zaman.now.kotlin.restful.error.NotFoundException
import programmer.zaman.now.kotlin.restful.repository.SuppliersRepository
import programmer.zaman.now.kotlin.restful.service.SuppliersService
import programmer.zaman.now.kotlin.restful.validation.ValidationUtil
import java.util.stream.Collectors

@Service
class SuppliersServiceImpl(
    val suppliersRepository: SuppliersRepository,
    val validationUtil: ValidationUtil
) : SuppliersService {

    override fun createSupplier(supplierRequestDto: SupplierRequestDto): SupplierResponseDto {
        validationUtil.validate(supplierRequestDto)
        val supplier = Suppliers(
            id = null,
            supplierName = supplierRequestDto.supplierName!!,
            address = supplierRequestDto.address!!,
            phone = supplierRequestDto.phone!!,
            fax = supplierRequestDto.fax
        )
        suppliersRepository.save(supplier)
        return supplierResponse(supplier)
    }

    override fun editSupplier(id: Int, supplierRequestDto: SupplierRequestDto): SupplierResponseDto {
        val supplier = checkSupplierById(id)
        validationUtil.validate(supplierRequestDto)
        supplier.apply {
            supplierName = supplierRequestDto.supplierName!!
            address = supplierRequestDto.address!!
            phone = supplierRequestDto.phone!!
            fax = supplierRequestDto.fax
        }
        suppliersRepository.save(supplier)
        return supplierResponse(supplier)
    }

    override fun deleteSupplier(id: Int) {
        val suppliers = checkSupplierById(id)
        suppliersRepository.delete(suppliers)
    }

    override fun getSupplierById(id: Int): SupplierResponseDto {
        val suppliers = checkSupplierById(id)
        return supplierResponse(suppliers)
    }

    override fun getAllSupplier(listRequestDto: ListRequestDto): List<SupplierResponseDto> {
        val page = suppliersRepository.findAll(PageRequest.of(listRequestDto.pageNo, listRequestDto.pageSize))
        val supplier: List<Suppliers> = page.get().collect(Collectors.toList())
        return supplier.map { supplierResponse(it) }
    }

    private fun supplierResponse(suppliers: Suppliers): SupplierResponseDto {
        return SupplierResponseDto(
            id = suppliers.id!!,
            supplierName = suppliers.supplierName,
            address = suppliers.address,
            phone = suppliers.phone,
            fax = suppliers.fax!!
        )
    }

    private fun checkSupplierById(id: Int): Suppliers {
        val supplier = suppliersRepository.findByIdOrNull(id)
        if (supplier == null) {
            throw NotFoundException()
        } else {
            return supplier
        }
    }
}