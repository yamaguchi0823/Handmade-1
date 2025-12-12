package com.example.app.entity;

import java.time.LocalDateTime;

import lombok.Data;

// 商品バリエーション
@Data
public class ItemVariant {
	
	private Long id;
	private Long itemId; // FK: items.id
	
	private String color;
	private String size;
	private String category;
	private Integer quantityPerSet;
	
	private Integer costPrice;
	private Integer price;

	private Integer stock;
	
	private String skuCode;
	private String imageFilename;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private boolean isDeleted;

	public boolean getIsDeleted() {
	    return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
	    this.isDeleted = isDeleted;
	}

}
