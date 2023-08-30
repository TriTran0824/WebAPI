package com.webapi.retailer.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.webapi.retailer.constant.Constant.*;

/**
 * The class that handles CORS policy to allow angular to call REST APIs.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private final Environment mEnvironment;

    public CorsConfig(Environment environment) {
        this.mEnvironment = environment;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String allowedOrigin = mEnvironment.getProperty("allowed.origin");
        String allowedCorsMapping = mEnvironment.getProperty("allowed.cors.mapping");
        registry.addMapping(allowedCorsMapping)
                .allowedOrigins(allowedOrigin)
                .allowedMethods(METHOD_GET, METHOD_POST, METHOD_PUT, METHOD_DELETE)
                .allowedHeaders(STAR)
                .allowCredentials(true);
    }
}
