package com.app.InventoryPro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_settings")
public class UserSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    private boolean lowStockAlertEnabled = true;
    private boolean highStockAlertEnabled = false;
    private boolean reorderReminderEnabled = true;

    @Enumerated(EnumType.STRING)
    private AlertFrequency alertFrequency = AlertFrequency.IMMEDIATE;
}
