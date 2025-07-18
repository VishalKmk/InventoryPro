package com.app.InventoryPro.service;

import com.app.InventoryPro.dto.DashboardSummaryDto;
import com.app.InventoryPro.exception.ResourceNotFoundException;
import com.app.InventoryPro.model.InventoryItem;
import com.app.InventoryPro.model.User;
import com.app.InventoryPro.repository.ActivityLogRepository;
import com.app.InventoryPro.repository.InventoryItemRepository;
import com.app.InventoryPro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public InventoryItem createInventoryItem(InventoryItem inventoryItem) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        inventoryItem.setUser(user);
        return inventoryItemRepository.save(inventoryItem);
    }

    @Override
    public InventoryItem getInventoryItemById(Long itemId) {
        return inventoryItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found with id: " + itemId));
    }

    @Override
    public List<InventoryItem> getAllInventoryItems() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getRole().toString().equals("ADMIN")) {
            return inventoryItemRepository.findAll();
        } else {
            return inventoryItemRepository.findByUser(user);
        }
    }

    @Override
    public InventoryItem updateInventoryItem(Long itemId, InventoryItem inventoryItemDetails) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found with id: " + itemId));

        inventoryItem.setProductName(inventoryItemDetails.getProductName());
        inventoryItem.setDescription(inventoryItemDetails.getDescription());
        inventoryItem.setPrice(inventoryItemDetails.getPrice());
        inventoryItem.setStockQuantity(inventoryItemDetails.getStockQuantity());
        inventoryItem.setMinStock(inventoryItemDetails.getMinStock());
        inventoryItem.setMaxStock(inventoryItemDetails.getMaxStock());
        inventoryItem.setReorderQuantity(inventoryItemDetails.getReorderQuantity());

        return inventoryItemRepository.save(inventoryItem);
    }

    @Override
    public void deleteInventoryItem(Long itemId) {
        if (!inventoryItemRepository.existsById(itemId)) {
            throw new ResourceNotFoundException("Inventory item not found with id: " + itemId);
        }
        inventoryItemRepository.deleteById(itemId);
    }

    @Override
    public DashboardSummaryDto getDashboardSummary() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<InventoryItem> userInventory = inventoryItemRepository.findByUser(user);

        long totalProducts = userInventory.size();

        long lowStockItems = userInventory.stream()
                .filter(item -> item.getStockQuantity() < item.getMinStock())
                .count();

        BigDecimal totalValue = userInventory.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getStockQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long recentActivityCount = activityLogRepository.countByUser(user);

        return DashboardSummaryDto.builder()
                .totalProducts(totalProducts)
                .lowStockItems(lowStockItems)
                .totalValue(totalValue)
                .recentActivityCount(recentActivityCount)
                .build();
    }
}
