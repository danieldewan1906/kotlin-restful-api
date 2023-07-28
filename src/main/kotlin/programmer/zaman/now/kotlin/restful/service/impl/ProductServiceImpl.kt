package programmer.zaman.now.kotlin.restful.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import programmer.zaman.now.kotlin.restful.dto.request.CreateProductRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.ListRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.ProductResponse
import programmer.zaman.now.kotlin.restful.dto.request.UpdateProductRequestDto
import programmer.zaman.now.kotlin.restful.entity.Product
import programmer.zaman.now.kotlin.restful.error.NotFoundException
import programmer.zaman.now.kotlin.restful.repository.ProductRepository
import programmer.zaman.now.kotlin.restful.service.ProductService
import programmer.zaman.now.kotlin.restful.validation.ValidationUtil
import java.util.*
import java.util.stream.Collectors

@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtil: ValidationUtil
    ) : ProductService {

    override fun create(createProductRequestDto: CreateProductRequestDto): ProductResponse {
        validationUtil.validate(createProductRequestDto)

        val product = Product(
            id = null,
            name = createProductRequestDto.name!!,
            price = createProductRequestDto.price!!,
            quantity = createProductRequestDto.quantity!!,
            createdAt = Date(),
            updatedAt = null
        )
        productRepository.save(product)
        return convertProductToProductResponse(product)
    }

    override fun getProductById(id: Int): ProductResponse {
        val product = findByIdOrThrowNotFound(id)
        return convertProductToProductResponse(product)
    }

    override fun updateProduct(id: Int, updateProductRequestDto: UpdateProductRequestDto): ProductResponse {
        val product = findByIdOrThrowNotFound(id)
        validationUtil.validate(updateProductRequestDto)
        product.apply {
            name = updateProductRequestDto.name!!
            price = updateProductRequestDto.price!!
            quantity = updateProductRequestDto.quantity!!
            updatedAt = Date()
        }
        productRepository.save(product)
        return convertProductToProductResponse(product)
    }

    override fun deleteProduct(id: Int) {
        val product = findByIdOrThrowNotFound(id)
        productRepository.delete(product)
    }

    override fun getListProduct(listRequestDto: ListRequestDto): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listRequestDto.pageNo, listRequestDto.pageSize))
        var products: List<Product> = page.get().collect(Collectors.toList())
        return products.map { convertProductToProductResponse(it) }
    }

    private fun findByIdOrThrowNotFound(id: Int): Product {
        val product = productRepository.findByIdOrNull(id)
        if (product == null) {
            throw NotFoundException()
        } else {
            return product
        }
    }

    private fun convertProductToProductResponse(product: Product): ProductResponse {
        return ProductResponse(
            id = product.id!!,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }

}