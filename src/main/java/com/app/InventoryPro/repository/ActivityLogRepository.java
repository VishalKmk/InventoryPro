package com.app.InventoryPro.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.InventoryPro.model.ActivityLog;
import com.app.InventoryPro.model.User;

/**
 * Repository interface for ActivityLog entity.
 * Provides methods to retrieve recent activity logs and count logs by user.
 */
@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    /**
     * Retrieves the 50 most recent activity logs, ordered by timestamp descending.
     *
     * @return List of up to 50 latest ActivityLog entries.
     */
    List<ActivityLog> findTop50ByOrderByTimestampDesc();

    /**
     * Counts the number of activity logs associated with the specified user.
     *
     * @param user the User entity to count logs for
     * @return the count of ActivityLog entries for the given user
     */
    long countByUser(User user);
}