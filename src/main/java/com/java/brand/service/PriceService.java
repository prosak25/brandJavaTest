package com.java.brand.service;

import com.java.brand.model.dto.PriceDTO;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {
    Optional<PriceDTO> findActivePrice(LocalDateTime date, Long productId, Long brandId);
}
