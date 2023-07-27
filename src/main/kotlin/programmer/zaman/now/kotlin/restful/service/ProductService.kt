package programmer.zaman.now.kotlin.restful.service

import programmer.zaman.now.kotlin.restful.dto.request.CreateProductRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.ListProductRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.ProductResponse
import programmer.zaman.now.kotlin.restful.dto.request.UpdateProductRequestDto

interface ProductService {

    fun create(createProductRequestDto: CreateProductRequestDto): ProductResponse

    fun getProductById(id: String): ProductResponse

    fun updateProduct(id: String, updateProductRequestDto: UpdateProductRequestDto): ProductResponse

    fun deleteProduct(id: String)

    fun getListProduct(listProductRequestDto: ListProductRequestDto): List<ProductResponse>

}