package programmer.zaman.now.kotlin.restful.service

import programmer.zaman.now.kotlin.restful.dto.request.CategoryRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.ListRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.CategoryResponse

interface CategoryService {

    fun createCategory(categoryRequestDto: CategoryRequestDto): CategoryResponse

    fun editCategory(id: Int, categoryRequestDto: CategoryRequestDto): CategoryResponse

    fun deleteCategory(id: Int)

    fun getCategoryById(id: Int): CategoryResponse

    fun getAllCategory(listRequestDto: ListRequestDto): List<CategoryResponse>
}