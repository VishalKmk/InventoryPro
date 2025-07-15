package com.app.InventoryPro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.InventoryPro.model.ActivityLog;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    List<ActivityLog> findTop50ByOrderByTimestampDesc();
}
