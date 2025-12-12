package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.dto.SaleView;
import com.example.app.entity.ItemVariant;
import com.example.app.entity.Sale;
import com.example.app.mapper.ItemVariantMapper;
import com.example.app.mapper.SaleMapper;

@Service
@Transactional
public class SaleService {
	
	@Autowired
	private SaleMapper saleMapper;
	
	@Autowired
	private ItemVariantMapper variantMapper;
	
	// 販売実績一覧
	public List<SaleView> findAllView() {
    return saleMapper.findAll();
	}
	
	public void registerSale(Sale sale) {
		
		// 1.バリエーション取得
		ItemVariant variant = variantMapper.findById(sale.getVariantId());
		// 2.在庫チェック
		if(variant.getStock() < sale.getQuantity()) {
			throw new IllegalArgumentException("在庫不足です");
		}
		// 3.販売を登録
		saleMapper.insert(sale);
		// 4.在庫を減らす
		variantMapper.decreaseStock(sale.getVariantId(),sale.getQuantity());
	}
	
	
}
