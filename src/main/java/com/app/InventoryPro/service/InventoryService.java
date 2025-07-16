package com.app.InventoryPro.service;

import com.app.InventoryPro.model.InventoryItem;
import com.app.InventoryPro.dto.DashboardSummaryDto;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface InventoryService {
    InventoryItem createInventoryItem(InventoryItem inventoryItem);

    InventoryItem getInventoryItemById(Long itemId);

    List<InventoryItem> getAllInventoryItems();

    InventoryItem updateInventoryItem(Long itemId, InventoryItem inventoryItem);

    void deleteInventoryItem(Long itemId);

    DashboardSummaryDto getDashboardSummary();
}
