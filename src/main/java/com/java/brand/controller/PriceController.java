package com.java.brand.controller;

import com.java.brand.model.Price;
import com.java.brand.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/api/prices")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }


    @GetMapping("/api/prices")
    @Operation(summary = "Get price for a product in a specific date",
            description = "Get price for a product in a specific date",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Price not found")
            })
    public ResponseEntity<Price> getPrice(
            @Parameter(description = "Date ", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @Parameter(description = "Product Id", required = true)
            @RequestParam Long productId,
            @Parameter(description = "Brand Id", required = true)
            @RequestParam Long brandId) {

        Optional<Price> price = priceService.findActivePrice(date, productId, brandId);
        return price.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
