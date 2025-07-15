package com.app.InventoryPro.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardSummaryDto {
    private long totalProducts;
    private long lowStockItems;
    private BigDecimal totalValue;
    private long recentActivityCount;
}
