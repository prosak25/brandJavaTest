package com.java.brand.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.java.brand.model.Price;
import com.java.brand.model.dto.PriceDTO;
import com.java.brand.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void whenValid_thenFindActivePrice() {
        Price mockPrice = new Price.Builder()
                .productId(1L)
                .brandId(1L)
                .priceList(1)
                .startDate(LocalDateTime.now().minusDays(1))
                .endDate(LocalDateTime.now().plusDays(1))
                .priority(1)
                .price(new BigDecimal("99.99"))
                .build();

        when(priceRepository.findByStartDateLessThanEqualAndEndDateGreaterThanAndProductIdAndBrandIdOrderByPriorityDesc(
                any(LocalDateTime.class), any(LocalDateTime.class), anyLong(), anyLong()))
                .thenReturn(List.of(mockPrice));

        Optional<PriceDTO> activePrice = priceService.findActivePrice(LocalDateTime.now(), 1L, 1L);

        assertTrue(activePrice.isPresent());
        assertEquals(new BigDecimal("99.99"), activePrice.get().getPrice());
    }

    @Test
    void whenNonExistingProduct_thenThrowNotFound() {
        Price mockPrice = new Price.Builder()
                .productId(1L)
                .brandId(1L)
                .priceList(1)
                .startDate(LocalDateTime.now().minusDays(1))
                .endDate(LocalDateTime.now().plusDays(1))
                .priority(1)
                .price(new BigDecimal("99.99"))
                .build();

        when(priceRepository.findByStartDateLessThanEqualAndEndDateGreaterThanAndProductIdAndBrandIdOrderByPriorityDesc(
                any(LocalDateTime.class), any(LocalDateTime.class), anyLong(), anyLong()))
                .thenReturn(Collections.emptyList());

        Optional<PriceDTO> activePrice = priceService.findActivePrice(LocalDateTime.now(), 2L, 1L);

        assertFalse(activePrice.isPresent());
    }
}

