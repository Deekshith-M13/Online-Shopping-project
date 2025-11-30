package com.project.inventory_service.repository;

import com.project.inventory_service.model.inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface inventoryRepository extends JpaRepository<inventory,Long> {
    Optional<inventory> findBySkuCode(String skuCode);
}
