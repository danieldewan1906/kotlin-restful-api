package programmer.zaman.now.kotlin.restful.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import programmer.zaman.now.kotlin.restful.dto.request.ListRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.SupplierRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.SupplierResponseDto
import programmer.zaman.now.kotlin.restful.dto.response.WebResponse
import programmer.zaman.now.kotlin.restful.service.SuppliersService

@RestController
@RequestMapping("/api")
class SuppliersController(
    val suppliersService: SuppliersService
) {

    @PostMapping(value = ["/suppliers"])
    fun createSupplier(@RequestBody supplierRequestDto: SupplierRequestDto): WebResponse<SupplierResponseDto> {
        val supplierResponse = suppliersService.createSupplier(supplierRequestDto)
        return WebResponse(
            code = 200,
            status = "OK",
            data = supplierResponse
        )
    }

    @PutMapping(value = ["/suppliers/{id}"])
    fun updateSupplier(@PathVariable("id") id: Int, @RequestBody supplierRequestDto: SupplierRequestDto): WebResponse<SupplierResponseDto> {
        val supplierResponse = suppliersService.editSupplier(id, supplierRequestDto)
        return WebResponse(
            code = 200,
            status = "OK",
            data = supplierResponse
        )
    }

    @DeleteMapping(value = ["/suppliers/{id}"])
    fun deleteSupplier(@PathVariable("id") id: Int): WebResponse<String> {
        suppliersService.deleteSupplier(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "Suppliers has been deleted"
        )
    }

    @GetMapping(value = ["/suppliers/{id}"])
    fun getSupplierById(@PathVariable("id") id: Int): WebResponse<SupplierResponseDto> {
        val supplierResponse = suppliersService.getSupplierById(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = supplierResponse
        )
    }

    @GetMapping(value = ["/suppliers"])
    fun getAllSupplier(
        @RequestParam(value = "pageNo", defaultValue = "0") pageNo: Int,
        @RequestParam(value = "pageSize", defaultValue = "10") pageSize: Int
    ) : WebResponse<List<SupplierResponseDto>> {
        val listRequest = ListRequestDto(pageNo, pageSize)
        val supplierResponse = suppliersService.getAllSupplier(listRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = supplierResponse
        )
    }
}