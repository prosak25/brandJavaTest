package com.java.brand.controller;

import com.java.brand.controller.PriceController;
import com.java.brand.model.dto.PriceDTO;
import com.java.brand.service.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @Test
    void whenValidRequest_thenReturnPrice() throws Exception {
        PriceDTO mockPriceDTO = new PriceDTO.Builder()
                .productId(1L)
                .brandId(1L)
                .priceList(1)
                .date(LocalDateTime.now())
                .price(new BigDecimal("99.99"))
                .build();

        given(priceService.findActivePrice(any(LocalDateTime.class), anyLong(), anyLong()))
                .willReturn(Optional.of(mockPriceDTO));

        mockMvc.perform(get("/testProject/v1/api/price")
                        .param("date", "2024-04-04 10:00:00")
                        .param("productId", "1")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void whenNoResult_thenRespondWithNotFound() throws Exception {
        given(priceService.findActivePrice(any(LocalDateTime.class), anyLong(), anyLong()))
                .willReturn(Optional.empty());


        mockMvc.perform(get("/testProject/v1/api/price")
                        .param("date", "2024-04-04T10:00:00")
                        .param("productId", "1")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

