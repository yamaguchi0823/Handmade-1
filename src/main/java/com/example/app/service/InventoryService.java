package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.dto.InventoryView;
import com.example.app.mapper.InventoryMapper;

@Service
public class InventoryService {
	
	private final InventoryMapper inventoryMapper;
	
	public InventoryService(InventoryMapper inventoryMapper) {
		this.inventoryMapper = inventoryMapper;
	}
	
	public List<InventoryView> searchInventory(
      String keyword, String sku, String color, String size, String stockFilter) {

  return inventoryMapper.searchInventory(keyword, sku, color, size, stockFilter);
}
	
	public List<String> getDistinctColors() {
    return inventoryMapper.findDistinctColors();
}

public List<String> getDistinctSizes() {
    return inventoryMapper.findDistinctSizes();
}
	
	
	
}
