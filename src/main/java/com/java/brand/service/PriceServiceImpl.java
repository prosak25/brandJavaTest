package com.java.brand.service;

import com.java.brand.model.dto.PriceDTO;
import com.java.brand.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<PriceDTO> findActivePrice (LocalDateTime date, Long productId, Long brandId) {
        return priceRepository.findByStartDateLessThanEqualAndEndDateGreaterThanAndProductIdAndBrandIdOrderByPriorityDesc(
                date, date, productId, brandId)
                .stream().findFirst().map(price -> PriceDTO.builder()
                        .productId(price.getProductId())
                        .brandId(price.getBrandId())
                        .priceList(price.getPriceList())
                        .date(date)
                        .price(price.getPrice())
                        .build());
    }
}
