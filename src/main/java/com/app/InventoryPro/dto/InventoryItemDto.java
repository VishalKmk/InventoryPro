package com.app.InventoryPro.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryItemDto {

    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    private String productName;
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private BigDecimal price;

    @NotNull
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private int stockQuantity;

    @NotNull
    @Min(value = 0, message = "Minimum stock cannot be negative")
    private int minStock;

    private Integer maxStock;

    @NotNull
    @Min(value = 1, message = "Reorder quantity must be at least 1")
    private int reorderQuantity;
}
