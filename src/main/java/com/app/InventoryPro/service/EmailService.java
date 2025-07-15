package com.app.InventoryPro.service;

import com.app.InventoryPro.model.User;

public interface EmailService {
    void sendLowStockAlert(User user, String productName);
    void sendHighStockAlert(User user, String productName);
    void sendReorderReminder(User user, String productName);
    void sendEmailChangeNotification(User user, String newEmail);
}
