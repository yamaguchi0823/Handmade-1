package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.entity.Sale;
import com.example.app.service.ItemVariantService;
import com.example.app.service.SaleService;

@Controller
@RequestMapping("/sales")
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	@Autowired
	private ItemVariantService variantService;
	
	
//	販売一覧
	@GetMapping
	public String list(Model model) {
    model.addAttribute("sales", saleService.findAllView());
    return "sales/list";
}
	
//	販売登録画面
	@GetMapping("/new")
	public String showNewForm(Model model) {
		model.addAttribute("sale",new Sale());
		model.addAttribute("variants", variantService.findAll()); //後で追加
		return "sales/new";
	}
	
//	販売登録処理
	@PostMapping
	public String create(@ModelAttribute Sale sale) {
		saleService.registerSale(sale);
		return "redirect:/sales";
	}
	
	
	
	
}
