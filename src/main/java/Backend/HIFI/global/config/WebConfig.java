package Backend.HIFI.global.config;

import Backend.HIFI.global.common.converter.StoreCategoryRequestConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig
 *
 * @author squirMM
 * @date 2023/07/26
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StoreCategoryRequestConverter());
    }
}
