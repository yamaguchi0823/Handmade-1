package com.example.app.entity;
// 商品
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Item {
	
	private Long id;
	private String name;
	private String category;
	private String description;	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
