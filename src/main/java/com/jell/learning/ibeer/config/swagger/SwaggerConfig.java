package com.jell.learning.ibeer.config.swagger;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerPropertiesConfig.class)
public class SwaggerConfig {

    @Bean
    public Docket docket(SwaggerPropertiesConfig property) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(property))
                .select()
                .apis(basePackage(property.getBasePackage()))
                .paths(any())
                .build();
    }

    private ApiInfo apiInfo(SwaggerPropertiesConfig propertiesConfig) {
        return new ApiInfoBuilder()
                .title(propertiesConfig.getTitle())
                .description(propertiesConfig.getDescription())
                .license(propertiesConfig.getLicense())
                .contact(contact(propertiesConfig))
                .build();
    }

    private Contact contact(SwaggerPropertiesConfig propertiesConfig) {
        return new Contact(
                propertiesConfig.getContactName(),
                propertiesConfig.getContactUrl(),
                propertiesConfig.getContactEmail()
        );
    }
}
