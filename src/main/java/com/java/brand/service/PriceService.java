package com.java.brand.service;

import com.java.brand.model.Price;
import com.java.brand.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {
    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> findActivePrice (LocalDateTime date, Long productId, Long brandId) {
        return priceRepository.findByStartDateLessThanEqualAndEndDateGreaterThanAndProductIdAndBrandId(
                date, productId, brandId).stream().max(Comparator.comparingInt(Price::getPriority));
    }
}
