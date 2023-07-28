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
import programmer.zaman.now.kotlin.restful.dto.request.CreateProductRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.ListRequestDto
import programmer.zaman.now.kotlin.restful.dto.request.UpdateProductRequestDto
import programmer.zaman.now.kotlin.restful.dto.response.ProductResponse
import programmer.zaman.now.kotlin.restful.dto.response.WebResponse
import programmer.zaman.now.kotlin.restful.service.ProductService

@RestController
@RequestMapping("/api")
class ProductController(val productService: ProductService) {

    @PostMapping(
        value = ["/products"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequestDto): WebResponse<ProductResponse> {
        val productResponse = productService.create(body);
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @GetMapping(
        value = ["/products/{id}"]
    )
    fun getProductById(@PathVariable("id") id: Int): WebResponse<ProductResponse> {
        val productResponse = productService.getProductById(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @PutMapping(
        value = ["/products/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateProduct(@PathVariable("id") id: Int, @RequestBody body: UpdateProductRequestDto): WebResponse<ProductResponse> {
        val productResponse = productService.updateProduct(id, body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @DeleteMapping(
        value = ["/products/{id}"]
    )
    fun deleteProduct(@PathVariable("id") id: Int): WebResponse<String> {
        val productResponse = productService.deleteProduct(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "Delete Product Success"
        )
    }

    @GetMapping(
        value = ["/products"],
        produces = ["application/json"]
    )
    fun getListProduct(@RequestParam(value = "pageNo", defaultValue = "0") pageNo: Int,
                       @RequestParam(value = "pageSize", defaultValue = "10") pageSize: Int): WebResponse<List<ProductResponse>> {
        val request = ListRequestDto(pageNo, pageSize)
        val productResponse = productService.getListProduct(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }
}