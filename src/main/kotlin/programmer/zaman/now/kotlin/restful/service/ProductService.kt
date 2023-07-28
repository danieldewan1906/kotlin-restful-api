package programmer.zaman.now.kotlin.restful.service

import programmer.zaman.now.kotlin.restful.dto.request.CreateProductRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.ListRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.ProductResponse
import programmer.zaman.now.kotlin.restful.dto.request.UpdateProductRequestDto

interface ProductService {

    fun create(createProductRequestDto: CreateProductRequestDto): ProductResponse

    fun getProductById(id: Int): ProductResponse

    fun updateProduct(id: Int, updateProductRequestDto: UpdateProductRequestDto): ProductResponse

    fun deleteProduct(id: Int)

    fun getListProduct(listRequestDto: ListRequestDto): List<ProductResponse>

}