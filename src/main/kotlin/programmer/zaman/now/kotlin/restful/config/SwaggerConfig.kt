package programmer.zaman.now.kotlin.restful.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.OperationsSorter
import springfox.documentation.swagger.web.UiConfiguration
import springfox.documentation.swagger.web.UiConfigurationBuilder
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .securitySchemes(Collections.singletonList(tokenAccessKey()))
            .securityContexts(Collections.singletonList(securityContext()))
            .useDefaultResponseMessages(false)
            .apiInfo(getApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("programmer.zaman.now.kotlin.restful"))
            .paths(PathSelectors.any())
            .build()
    }

    @Bean
    fun tokenAccessKey(): SecurityScheme{
        return ApiKey("JWT", "Authorization", "header")
    }

    @Bean
    fun uiConfig(): UiConfiguration {
        return UiConfigurationBuilder
            .builder()
            .operationsSorter(OperationsSorter.METHOD)
            .build()
    }

    private fun securityContext(): SecurityContext{
        return SecurityContext.builder().securityReferences(defaultAuth()).build()
    }

    private fun defaultAuth(): List<SecurityReference> {
        val authorizationScope : AuthorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes : Array<AuthorizationScope> = arrayOf(authorizationScope)
        return Collections.singletonList(SecurityReference("JWT", authorizationScopes))
    }

    private fun getApiInfo(): ApiInfo{
        val contact = Contact("Daniel Dewanto", "", "danieldewanto19@gmail.com")
        return ApiInfoBuilder()
            .title("API Kotlin")
            .description("RESTful API")
            .version("1.0.0")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
            .contact(contact)
            .build()
    }

}