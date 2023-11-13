package org.greentech.backend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.greentech.backend.dto.request.ProductRequestDto;
import org.greentech.backend.dto.response.ProductResponseDto;
import org.greentech.backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto requestDto) {
        ProductResponseDto responseDto = productService.create(requestDto);

        return ResponseEntity
                .created(URI.create("/api/products/" + responseDto.getId()))
                .body(responseDto);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> findProductById(@PathVariable Integer productId) {
        return ResponseEntity
                .ok(productService.findById(productId));
    }

    @GetMapping("/byArticle/{productArticle}")
    public ResponseEntity<List<ProductResponseDto>> findProductByArticle(@PathVariable String productArticle) {
        return ResponseEntity
                .ok(productService.findAllByArticle(productArticle));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> searchForProducts(
            @RequestParam(name = "name", required = false, defaultValue = "") String approximateName,
            @RequestParam(name = "article", required = false, defaultValue = "") String approximateArticle,
            @RequestParam(name = "size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber) {
        return ResponseEntity
                .ok(productService.search(
                        approximateName,
                        approximateArticle,
                        pageSize, pageNumber));
    }

    @PatchMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDto> update(@PathVariable Integer productId,
                                                     @RequestBody ProductRequestDto requestDto) {
        return ResponseEntity
                .ok(productService.update(productId, requestDto));
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable Integer productId) {
        productService.deleteById(productId);
    }
}
