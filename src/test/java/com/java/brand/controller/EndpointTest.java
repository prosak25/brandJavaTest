package com.java.brand.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EndpointTest {

    @Autowired
    private MockMvc mockMvc;

    //given dates to test
    private final String[] dates = {
            "2020-06-14 10:00:00",
            "2020-06-14 16:00:00",
            "2020-06-14 21:00:00",
            "2020-06-15 10:00:00",
            "2020-06-16 21:00:00"
    };

    //expected prices to get when call requets
    private final BigDecimal[] prices = {
            BigDecimal.valueOf(35.50),
            BigDecimal.valueOf(25.45),
            BigDecimal.valueOf(35.50),
            BigDecimal.valueOf(30.50),
            BigDecimal.valueOf(38.95)
    };


    @Test
    void testGivenSpecificDatesAndPrices() throws Exception {
        for (int i = 0; i < dates.length; i++) {
            mockMvc.perform(get("/testProject/v1/api/price")
                            .param("date", dates[i])
                            .param("productId", "35455")
                            .param("brandId", "1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(prices[i]));
        }
    }
}
