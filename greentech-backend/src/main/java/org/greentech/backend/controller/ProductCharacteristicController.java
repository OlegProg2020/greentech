package org.greentech.backend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.greentech.backend.dto.request.ProductCharacteristicPutDto;
import org.greentech.backend.service.ProductCharacteristicService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping(path = "/products/{productId}/characteristics/{parameterId}")
@RequiredArgsConstructor
public class ProductCharacteristicController {
    private final ProductCharacteristicService productCharacteristicService;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void putCharacteristicToProduct(@PathVariable("productId") Integer productId,
                                           @PathVariable("parameterId") Integer parameterId,
                                           @RequestBody ProductCharacteristicPutDto characteristicDto) {
        productCharacteristicService.putCharacteristicToProduct(productId, parameterId, characteristicDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeCharacteristicFromProduct(@PathVariable("productId") Integer productId,
                                                @PathVariable("parameterId") Integer parameterId) {
        productCharacteristicService.removeCharacteristicFromProduct(productId, parameterId);
    }
}
