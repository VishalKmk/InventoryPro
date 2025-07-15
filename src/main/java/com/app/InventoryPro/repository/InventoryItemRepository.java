package com.app.InventoryPro.repository;

import com.app.InventoryPro.model.InventoryItem;
import com.app.InventoryPro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    @Query("SELECT i FROM InventoryItem i WHERE i.stockQuantity <= i.minStock")
    List<InventoryItem> findLowStockItems();

    List<InventoryItem> findByUser(User user);
}
