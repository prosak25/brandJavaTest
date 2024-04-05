package com.java.brand.controller;

import com.java.brand.model.dto.PriceDTO;
import com.java.brand.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("testProject/v1")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }


    @GetMapping("/api/price")
    @Operation(summary = "Get price for a product in a specific date",
            description = "Get price for a product in a specific date",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Price not found")
            })
    public ResponseEntity<PriceDTO> getPrice(
            @Parameter(description = "Date [yyyy-MM-dd'T'HH:mm:ss.SSSXXX]", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @Parameter(description = "Product Id", required = true)
            @RequestParam Long productId,
            @Parameter(description = "Brand Id", required = true)
            @RequestParam Long brandId) {

        Optional<PriceDTO> actualPrice = priceService.findActivePrice(date, productId, brandId);
        if (actualPrice.isPresent()) {
            return ResponseEntity.ok(actualPrice.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró ningún precio para la solicitud especificada");
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }
}
