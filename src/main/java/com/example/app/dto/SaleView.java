package com.example.app.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class SaleView {
	
	private Long id; // sale.id
	private LocalDate saleDate;
	private int quantity;
	private int salePrice;
	private String channel;
	private String memo;
	
	private Long variantId;
	
	// JOIN 対応
	private String itemName;
	private String color;
	private String size;
	
	private String category;         // 分類
	private Integer quantityPerSet;  // 入り数
	
	
}
