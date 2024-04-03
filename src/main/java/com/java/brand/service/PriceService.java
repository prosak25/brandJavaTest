package com.java.brand.service;

import com.java.brand.model.Price;
import com.java.brand.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceService {
    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<Price> findActivePrice (LocalDateTime date, Long productId, Long brandId) {
        return priceRepository.findByStartDateLessThanEqualAndEndDateGreaterThanAndProductIdAndBrandId(
                date, productId, brandId);
    }
}
