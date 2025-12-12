package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.app.entity.ItemVariant;

public interface ItemVariantMapper {
	
	List<ItemVariant> findAll();
	
	List<ItemVariant> findByItemId(Long itemId);
	ItemVariant findById(Long id);
	
	void insert(ItemVariant variant);
	void update(ItemVariant variant);
	void delete(Long id);
	
	void clearImage(Long id);
	
	void updateStock(
			@Param("id") Long id,
			@Param("stock") Integer stock
			);
	
	void increaseStock(
			@Param("id") Long id,
			@Param("qty") int qty
			);
	void decreaseStock(
			@Param("id") Long id,
			@Param("qty") int qty
			);
}
