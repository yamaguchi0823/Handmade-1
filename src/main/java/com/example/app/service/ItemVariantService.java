package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.entity.ItemVariant;
import com.example.app.mapper.ItemVariantMapper;

@Service
public class ItemVariantService {
	
	@Autowired
	private ItemVariantMapper variantMapper;
	
	public List<ItemVariant> findAll(){
		return variantMapper.findAll();
	}
	
	public List<ItemVariant> findByItemId(Long id){
		return variantMapper.findByItemId(id);
	}
	
	public ItemVariant findById(Long id) {
		return variantMapper.findById(id);
	}
	
	public void insert(ItemVariant variant) {
		variantMapper.insert(variant);
	}
	
	public void update(ItemVariant variant) {
		variantMapper.update(variant);
	}
		
	public void delete(Long id) {
		variantMapper.delete(id);
		}
	
	public void clearImage(Long variantId) {
		variantMapper.clearImage(variantId);
	}
	
	public void updateStock(Long id, int stock) {
		variantMapper.updateStock(id, stock);
	}
	
	public void increaseStock(Long id, int qty) {
		variantMapper.increaseStock(id, qty);
	}
	
	public void decreaseStock(Long id, int qty) {
		variantMapper.decreaseStock(id, qty);
	}
	
}
