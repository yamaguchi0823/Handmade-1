package com.example.app.mapper;

import java.util.List;

import com.example.app.dto.SaleView;
import com.example.app.entity.Sale;

public interface SaleMapper {
	
	List<SaleView> findAll();
	
	Sale findById(Long id);
	
	void insert(Sale sale);
	
	List<Sale> findByVariantId(Long variantId);
	
}
