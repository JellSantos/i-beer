package com.jell.learning.ibeer.infrastructure.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "swagger-ui.api-info")
public class SwaggerPropertiesConfig {

    private String title;

    private String description;

    private String license;

    private String basePackage;

    private String contactName;

    private String contactUrl;

    private String contactEmail;
}