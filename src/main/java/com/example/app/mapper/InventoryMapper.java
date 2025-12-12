package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.app.dto.InventoryView;

public interface InventoryMapper {

	List<InventoryView> searchInventory(
      @Param("keyword") String keyword,
      @Param("sku") String sku,
      @Param("color") String color,
      @Param("size") String size,
      @Param("stockFilter") String stockFilter
  );

  List<String> findDistinctColors();
  List<String> findDistinctSizes();

	
}
