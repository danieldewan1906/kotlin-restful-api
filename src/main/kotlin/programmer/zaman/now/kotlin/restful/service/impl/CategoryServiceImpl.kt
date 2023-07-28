package programmer.zaman.now.kotlin.restful.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import programmer.zaman.now.kotlin.restful.dto.request.CategoryRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.ListRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.CategoryResponse
import programmer.zaman.now.kotlin.restful.entity.Category
import programmer.zaman.now.kotlin.restful.error.NotFoundException
import programmer.zaman.now.kotlin.restful.repository.CategoryRepository
import programmer.zaman.now.kotlin.restful.service.CategoryService
import programmer.zaman.now.kotlin.restful.validation.ValidationUtil
import java.util.stream.Collectors

@Service
class CategoryServiceImpl(
    val categoryRepository: CategoryRepository,
    val validationUtil: ValidationUtil
    ) : CategoryService {

    override fun createCategory(categoryRequestDto: CategoryRequestDto): CategoryResponse {
        validationUtil.validate(categoryRequestDto)
        val category = Category(
            id = null,
            name = categoryRequestDto.name!!
        )
        categoryRepository.save(category)
        return convertCategoryToCategoryResponse(category)
    }

    override fun editCategory(id: Int, categoryRequestDto: CategoryRequestDto): CategoryResponse {
        val category = getCategoryByIdAndThrowError(id)
        validationUtil.validate(categoryRequestDto)
        category.apply {
            name = categoryRequestDto.name!!
        }
        categoryRepository.save(category)
        return convertCategoryToCategoryResponse(category)
    }

    override fun deleteCategory(id: Int) {
        val category = getCategoryByIdAndThrowError(id)
        categoryRepository.delete(category)
    }

    override fun getCategoryById(id: Int): CategoryResponse {
        val category = getCategoryByIdAndThrowError(id)
        return convertCategoryToCategoryResponse(category)
    }

    override fun getAllCategory(listRequestDto: ListRequestDto): List<CategoryResponse> {
        val page = categoryRepository.findAll(PageRequest.of(listRequestDto.pageNo, listRequestDto.pageSize))
        var category: List<Category> = page.get().collect(Collectors.toList())
        return category.map { convertCategoryToCategoryResponse(it) }
    }

    private fun getCategoryByIdAndThrowError(id: Int): Category {
        val category = categoryRepository.findByIdOrNull(id)
        if (category == null) {
            throw NotFoundException()
        } else {
            return category
        }
    }

    private fun convertCategoryToCategoryResponse(category: Category): CategoryResponse {
        return CategoryResponse(
            id = category.id!!,
            name = category.name
        )
    }
}