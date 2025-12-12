package com.example.app.mapper;

import java.util.List;

import com.example.app.entity.Item;

public interface ItemMapper {
	
	List<Item> findAll();
	Item findById(Long id);
	
	void insert(Item item);
	void update(Item item);
	void delete(Long id);

}
