package com.java.brand.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceDTO {

    private final Long brandId;
    private final LocalDateTime date;
    private final Integer priceList;
    private final Long productId;
    private final BigDecimal price;

    private PriceDTO(Builder builder) {
        this.brandId = builder.brandId;
        this.date = builder.date;
        this.priceList = builder.priceList;
        this.productId = builder.productId;
        this.price = builder.price;
    }

    public Long getBrandId() {
        return brandId;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public Integer getPriceList() {
        return priceList;
    }
    public Long getProductId() {
        return productId;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long brandId;
        private LocalDateTime date;
        private Integer priceList;
        private Long productId;
        private BigDecimal price;

        public Builder brandId(Long brandId) { this.brandId = brandId; return this; }
        public Builder date(LocalDateTime date) { this.date = date; return this; }
        public Builder priceList(Integer priceList) { this.priceList = priceList; return this; }
        public Builder productId(Long productId) { this.productId = productId; return this; }
        public Builder price(BigDecimal price) { this.price = price; return this; }

        public PriceDTO build() {
            return new PriceDTO(this);
        }
    }
}
