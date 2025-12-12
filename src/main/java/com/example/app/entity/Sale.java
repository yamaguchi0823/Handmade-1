package com.example.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
// 販売
@Data
public class Sale {
	
	private Long id;
	
	private LocalDate saleDate;
	private Long variantId; // FK: item_variants.id
	
	private Integer quantity;
	private Integer salePrice;
	
	private String channel;
	private String memo;
	
	private LocalDateTime createdAt;

}
