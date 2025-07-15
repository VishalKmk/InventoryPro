package com.app.InventoryPro.controller;

import com.app.InventoryPro.model.InventoryItem;
import com.app.InventoryPro.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InventoryItem> createInventoryItem(@RequestBody InventoryItem inventoryItem) {
        return ResponseEntity.ok(inventoryService.createInventoryItem(inventoryItem));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<InventoryItem> getInventoryItemById(@PathVariable Long itemId) {
        return ResponseEntity.ok(inventoryService.getInventoryItemById(itemId));
    }

    @GetMapping
    public ResponseEntity<List<InventoryItem>> getAllInventoryItems() {
        return ResponseEntity.ok(inventoryService.getAllInventoryItems());
    }

    @PutMapping("/{itemId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InventoryItem> updateInventoryItem(@PathVariable Long itemId, @RequestBody InventoryItem inventoryItem) {
        return ResponseEntity.ok(inventoryService.updateInventoryItem(itemId, inventoryItem));
    }

    @DeleteMapping("/{itemId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long itemId) {
        inventoryService.deleteInventoryItem(itemId);
        return ResponseEntity.ok().build();
    }
}
