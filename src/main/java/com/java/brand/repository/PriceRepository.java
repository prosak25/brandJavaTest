package com.java.brand.repository;

import com.java.brand.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, BigDecimal> {

    //@Query("SELECT * FROM Price p WHERE p.startDate <= :applicationDate AND p.endDate >= :applicationDate AND p.productId = :productId AND p.brandId = :brandId ORDER BY p.priority DESC")
    List<Price> findByStartDateLessThanEqualAndEndDateGreaterThanAndProductIdAndBrandIdOrderByPriorityDesc (
            LocalDateTime date,
            LocalDateTime sameDate,
            Long productId,
            Long brandId);
}
