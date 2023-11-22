package org.greentech.backend.service.implementation;

import lombok.RequiredArgsConstructor;
import org.greentech.backend.data.repository.ParameterRepository;
import org.greentech.backend.data.repository.ProductRepository;
import org.greentech.backend.entity.Parameter;
import org.greentech.backend.entity.Product;
import org.greentech.backend.exception.DataMissingException;
import org.greentech.backend.service.ProductCharacteristicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class ProductCharacteristicServiceImpl implements ProductCharacteristicService {
    private static final String PRODUCT_ID_NOT_FOUND = "Товар с переданным id не существует";
    private static final String PARAMETER_ID_NOT_FOUND = "Параметр с переданным id не существует";

    private final ProductRepository productRepository;
    private final ParameterRepository parameterRepository;

    @Override
    @Transactional
    public void putCharacteristicToProduct(Integer productId, Integer parameterId, String value) {
        Product product = findProductByIdInternal(productId);
        Parameter parameter = findParameterByIdInternal(parameterId);

        product.getCharacteristics().put(parameter, value);

        productRepository.save(product);
    }

    @Override
    @Transactional
    public void removeCharacteristicFromProduct(Integer productId, Integer parameterId) {
        Product product = findProductByIdInternal(productId);
        Parameter parameter = findParameterByIdInternal(parameterId);

        product.getCharacteristics().remove(parameter);

        productRepository.save(product);
    }


    private Product findProductByIdInternal(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new DataMissingException(PRODUCT_ID_NOT_FOUND));
    }

    private Parameter findParameterByIdInternal(Integer parameterId) {
        return parameterRepository.findById(parameterId)
                .orElseThrow(() -> new DataMissingException(PARAMETER_ID_NOT_FOUND));
    }
}
