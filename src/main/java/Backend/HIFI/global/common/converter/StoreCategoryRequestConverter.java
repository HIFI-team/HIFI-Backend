package Backend.HIFI.global.common.converter;

import Backend.HIFI.domain.store.entity.Category;
import org.springframework.core.convert.converter.Converter;


/**
 * StoreCategoryRequestConverter
 *
 * @author squirMM
 * @date 2023/07/26
 */
public class StoreCategoryRequestConverter implements Converter<String, Category> {
    @Override
    public Category convert(String value) {
        return Category.ofView(value);
    }
}
