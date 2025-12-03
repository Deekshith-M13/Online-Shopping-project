package com.project.api_gateway.inventory_service.repository;

import com.project.api_gateway.inventory_service.model.inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface inventoryRepository extends JpaRepository<inventory,Long> {

    List<inventory> findBySkuCodeIn(List<String> skuCode);
}
