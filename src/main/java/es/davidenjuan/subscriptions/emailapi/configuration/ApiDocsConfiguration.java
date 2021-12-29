package es.davidenjuan.subscriptions.emailapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class ApiDocsConfiguration {

    @Bean
    public Docket swaggerSpringfoxDocket() {
        Docket docket = new Docket(DocumentationType.OAS_30)
            .select()
                .apis(RequestHandlerSelectors.basePackage("es.davidenjuan.subscriptions.emailapi.web.rest"))
                .build()
            .apiInfo(apiInfo())
            .forCodeGeneration(true)
            .genericModelSubstitutes(ResponseEntity.class)
            .useDefaultResponseMessages(false)
            .groupName("Subscriptions email API");
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Subscriptions email API")
            .description("REST API to send subscriptions emails")
            .version("1.0.0-RELEASE")
            .build();
    }
}
