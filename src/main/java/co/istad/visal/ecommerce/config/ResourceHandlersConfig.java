package co.istad.visal.ecommerce.config;

import co.istad.visal.ecommerce.utils.ResourcePrefix;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceHandlersConfig implements WebMvcConfigurer {

    @Value("${media.client-path}")
    private String mediaClientPath;

    @Value("${media.location}")
    private String mediaLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler(mediaClientPath+ "/**")
                .addResourceLocations(ResourcePrefix.FILE_SYSTEM + mediaLocation);
    }
}
