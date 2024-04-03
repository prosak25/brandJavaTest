package com.java.brand.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "price")
public class Price {
    @Column(name = "brand_id")
    private Long brandId;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "price_list")
    private Integer priceList;
    @Column(name = "product_id")
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String curr;

    public Long getBrandId() { return brandId; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public Integer getPriceList() { return priceList; }
    public Long getProductId() { return productId; }
    public Integer getPriority() { return priority; }
    public BigDecimal getPrice() { return price; }
    public String getCurr() { return curr; }


    public static class Builder {
        private Long brandId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer priceList;
        private Long productId;
        private Integer priority;
        private BigDecimal price;
        private String curr;

        public Builder brandId(Long brandId) { this.brandId = brandId; return this; }
        public Builder startDate(LocalDateTime startDate) { this.startDate = startDate; return this; }
        public Builder endDate(LocalDateTime endDate) { this.endDate = endDate; return this; }
        public Builder priceList(Integer priceList) { this.priceList = priceList; return this; }
        public Builder productId(Long productId) { this.productId = productId; return this; }
        public Builder priority(Integer priority) { this.priority = priority; return this; }
        public Builder price(BigDecimal price) { this.price = price; return this; }
        public Builder curr(String curr) { this.curr = curr; return this; }

        public Price build() {
            Price price = new Price();
            price.brandId = this.brandId;
            price.startDate = this.startDate;
            price.endDate = this.endDate;
            price.priceList = this.priceList;
            price.productId = this.productId;
            price.priority = this.priority;
            price.price = this.price;
            price.curr = this.curr;
            return price;
        }
    }


}
