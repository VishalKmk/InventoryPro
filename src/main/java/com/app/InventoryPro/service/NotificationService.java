package com.app.InventoryPro.service;

import com.app.InventoryPro.model.InventoryItem;
import com.app.InventoryPro.model.User;
import com.app.InventoryPro.model.UserSettings;
import com.app.InventoryPro.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 * * * *") // Runs every hour
    public void checkStockLevels() {
        List<InventoryItem> lowStockItems = inventoryItemRepository.findLowStockItems();
        for (InventoryItem item : lowStockItems) {
            User user = item.getUser();
            UserSettings userSettings = user.getUserSettings();
            if (userSettings != null && userSettings.isLowStockAlertEnabled()) {
                emailService.sendLowStockAlert(user, item.getProductName());
            }
        }

        // High stock alerts and reorder reminders
        List<InventoryItem> highStockItems = inventoryItemRepository.findAll();
        for (InventoryItem item : highStockItems) {
            User user = item.getUser();
            UserSettings userSettings = user.getUserSettings();
            if (userSettings != null && userSettings.isHighStockAlertEnabled()) {
                emailService.sendHighStockAlert(user, item.getProductName());
            }
        }
    }
}
