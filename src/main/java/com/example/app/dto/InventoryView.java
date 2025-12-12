package com.example.app.dto;

import lombok.Data;

@Data
public class InventoryView {
	
	private Long itemId;
  private String itemName;
  private String imagePath;

  private Long variantId;
  private String color;
  private String size;
  private String skuCode;
  private Integer stock;
  private Integer price;
  
  private String category;         // 分類
  private Integer quantityPerSet;  // 入り数
	
}
