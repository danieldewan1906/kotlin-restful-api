package programmer.zaman.now.kotlin.restful.service

import programmer.zaman.now.kotlin.restful.dto.CreateProductRequestDto
import programmer.zaman.now.kotlin.restful.dto.ListProductRequestDto
import programmer.zaman.now.kotlin.restful.dto.ProductResponse
import programmer.zaman.now.kotlin.restful.dto.UpdateProductRequestDto

interface ProductService {

    fun create(createProductRequestDto: CreateProductRequestDto): ProductResponse

    fun getProductById(id: String): ProductResponse

    fun updateProduct(id: String, updateProductRequestDto: UpdateProductRequestDto): ProductResponse

    fun deleteProduct(id: String)

    fun getListProduct(listProductRequestDto: ListProductRequestDto): List<ProductResponse>

}