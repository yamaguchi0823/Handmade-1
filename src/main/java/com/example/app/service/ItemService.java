package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.entity.Item;
import com.example.app.mapper.ItemMapper;
@Service
public class ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	
	public List<Item> findAll(){
		return itemMapper.findAll();
	}
	
	public Item findById(Long id) {
		return itemMapper.findById(id);
	}
	
	public void save(Item item) {
		if(item.getId() == null) {
			itemMapper.insert(item);
		} else {
			itemMapper.update(item);
		}
	}
	
	public void delete(Long id) {
		itemMapper.delete(id);
	}

}
