package com.example.app.controller;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.dto.InventoryView;
import com.example.app.service.InventoryService;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public String listInventory(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sku,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String stockFilter,
            Model model) {

        List<InventoryView> inventory = inventoryService.searchInventory(
                keyword, sku, color, size, stockFilter);
        // プルダウン用データ
        List<String> colors = inventoryService.getDistinctColors();
        List<String> sizes = inventoryService.getDistinctSizes();

     // ------------------------
     // ★ 商品単位の見出しだけを抽出する
     // ------------------------
        Map<Long, InventoryView> map = new LinkedHashMap<>();
        for(InventoryView v : inventory) {
        	map.putIfAbsent(v.getItemId(), v);
        }
        List<InventoryView> itemHeaders = new ArrayList<>(map.values());
        
        model.addAttribute("inventory", inventory);
        model.addAttribute("itemHeaders", itemHeaders);
        model.addAttribute("colors", colors);
        model.addAttribute("sizes", sizes);

        // 検索条件保持
        model.addAttribute("keyword", keyword);
        model.addAttribute("sku", sku);
        model.addAttribute("color", color);
        model.addAttribute("size", size);
        model.addAttribute("stockFilter", stockFilter);

        return "inventory/list";
    }
}
