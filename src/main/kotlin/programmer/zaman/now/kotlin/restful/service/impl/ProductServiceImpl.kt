package programmer.zaman.now.kotlin.restful.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import programmer.zaman.now.kotlin.restful.dto.request.CreateProductRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.ListRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.ProductResponse
import programmer.zaman.now.kotlin.restful.dto.request.UpdateProductRequestDto
import programmer.zaman.now.kotlin.restful.entity.Category
import programmer.zaman.now.kotlin.restful.entity.Product
import programmer.zaman.now.kotlin.restful.entity.Suppliers
import programmer.zaman.now.kotlin.restful.error.NotFoundException
import programmer.zaman.now.kotlin.restful.repository.CategoryRepository
import programmer.zaman.now.kotlin.restful.repository.ProductRepository
import programmer.zaman.now.kotlin.restful.service.CategoryService
import programmer.zaman.now.kotlin.restful.service.ProductService
import programmer.zaman.now.kotlin.restful.service.SuppliersService
import programmer.zaman.now.kotlin.restful.validation.MapperUtils
import programmer.zaman.now.kotlin.restful.validation.ValidationUtil
import java.util.*
import java.util.stream.Collectors

@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val categoryService: CategoryService,
    val suppliersService: SuppliersService,
    val validationUtil: ValidationUtil
    ) : ProductService {

    override fun create(createProductRequestDto: CreateProductRequestDto): ProductResponse {
        validationUtil.validate(createProductRequestDto)
        val category = getCategory(createProductRequestDto.category)
        val supplier = getSupplier(createProductRequestDto.supplier)

        val product = Product(
            id = null,
            name = createProductRequestDto.name!!,
            categories = category,
            suppliers = supplier,
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
        val category = getCategory(updateProductRequestDto.category)
        val supplier = getSupplier(updateProductRequestDto.supplier)
        product.apply {
            name = updateProductRequestDto.name!!
            categories = category
            suppliers = supplier
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
            category = product.categories,
            suppliers = product.suppliers,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }

    private fun getCategory(categoryId: Int?) : Category {
        val categoryResponse = categoryService.getCategoryById(categoryId!!)
        val category = MapperUtils().map(categoryResponse, Category::class.java)
        return category!!
    }

    private fun getSupplier(supplierId: Int?) : Suppliers {
        val supplierResponse = suppliersService.getSupplierById(supplierId!!)
        val supplier = MapperUtils().map(supplierResponse, Suppliers::class.java)
        return supplier!!
    }

}