package com.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.entity.Item;
import com.example.app.entity.ItemVariant;
import com.example.app.service.ItemService;
import com.example.app.service.ItemVariantService;

@Controller
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemVariantService variantService;
	
	// 商品一覧（GET）
	@GetMapping
	public String list(Model model) {
		model.addAttribute("items", itemService.findAll());
		model.addAttribute("currentPath", "items");
		return "items/list";
	}
	
	// 商品登録画面（GET）
	@GetMapping("/new")
	public String showNewForm(Model model) {
		model.addAttribute("item",new Item());
		return "items/new";
	}
	
	// 商品登録処理（POST）
	@PostMapping
	public String create(@ModelAttribute Item item) {
		itemService.save(item);
		return "redirect:/items";
	}
	
	// 商品詳細（GET）
	@GetMapping("/{id}")
	public String detail(
			@PathVariable Long id,
			Model model
			) {
		Item item = itemService.findById(id);
		List<ItemVariant> variants = variantService.findByItemId(id);

		model.addAttribute("item",item);
		model.addAttribute("variants",variants);
		return "items/detail";
	}
	
	// 商品編集画面（GET）
	@GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable Long id,Model model) {
		model.addAttribute("item", itemService.findById(id));		
		return "items/edit";
	}
	
	// 商品更新処理（POST）
	@PostMapping("/{id}/edit")
	public String update(@PathVariable Long id,Item item) {
		item.setId(id);
		itemService.save(item);
		return "redirect:/items/" + id;
	}
	
	// 商品削除（POST）
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		itemService.delete(id);
		return "redirect:/items";
	}
	
	
	
}
