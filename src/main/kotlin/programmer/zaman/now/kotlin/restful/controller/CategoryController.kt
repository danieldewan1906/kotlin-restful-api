package programmer.zaman.now.kotlin.restful.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import programmer.zaman.now.kotlin.restful.dto.request.CategoryRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.ListRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.CategoryResponse
import programmer.zaman.now.kotlin.restful.dto.response.WebResponse
import programmer.zaman.now.kotlin.restful.service.CategoryService

@RestController
@RequestMapping("/api")
class CategoryController(val categoryService: CategoryService) {

    @PostMapping(
        value = ["/category"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createCategory(@RequestBody createCategoryRequestDto: CategoryRequestDto): WebResponse<CategoryResponse> {
        val categoryResponse = categoryService.createCategory(createCategoryRequestDto)
        return WebResponse(
            code = 200,
            status = "OK",
            data = categoryResponse
        )
    }

    @PutMapping(
        value = ["/category/{id}"],
        produces = ["application/json"]
    )
    fun updateCategory(@PathVariable("id") id: Int, @RequestBody categoryRequestDto: CategoryRequestDto): WebResponse<CategoryResponse> {
        val categoryResponse = categoryService.editCategory(id, categoryRequestDto)
        return WebResponse(
            code = 200,
            status = "OK",
            data = categoryResponse
        )
    }

    @DeleteMapping(
        value = ["/category/{id}"]
    )
    fun deleteCategory(@PathVariable("id") id: Int): WebResponse<String> {
        categoryService.deleteCategory(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "Category has been deleted!"
        )
    }

    @GetMapping(
        value = ["/category/{id}"]
    )
    fun getCategoryById(@PathVariable("id") id: Int): WebResponse<CategoryResponse> {
        val categoryResponse = categoryService.getCategoryById(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = categoryResponse
        )
    }

    @GetMapping(
        value = ["/category"]
    )
    fun getAllCategory(
        @RequestParam(value = "pageNo", defaultValue = "0") pageNo: Int,
        @RequestParam(value = "pageSize", defaultValue = "10") pageSize: Int
    ): WebResponse<List<CategoryResponse>> {

        val listRequest = ListRequestDto(pageNo, pageSize)
        val categoryResponse = categoryService.getAllCategory(listRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = categoryResponse
        )
    }
}