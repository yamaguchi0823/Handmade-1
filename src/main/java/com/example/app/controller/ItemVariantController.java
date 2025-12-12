package com.example.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.entity.ItemVariant;
import com.example.app.service.ItemService;
import com.example.app.service.ItemVariantService;

@Controller
@RequestMapping("/items/{itemId}")
public class ItemVariantController {
	
	@Value("${app.upload.dir}")
	private String uploadDir;
	
	@Autowired
	private ItemVariantService variantService;
	@Autowired
	private ItemService itemService;
	
	private List<String> getCategoryList(){
		return List.of(
				"ピアス",
				"イヤリング",
				"MGイヤリング",
				"磁石",
				"画鋲",
				"その他"
				);
	}
	
  // ================================
  // バリエーション登録画面（GET）
  // ================================
	@GetMapping("/variants/new")
	public String showNewForm(@PathVariable Long itemId, Model model) {
		ItemVariant variant = new ItemVariant();
		variant.setItemId(itemId);
		
		model.addAttribute("item", itemService.findById(itemId));
		model.addAttribute("variant", variant);
		model.addAttribute("categories", getCategoryList());
		
		return "variants/new";
	}
	
//================================
  // バリエーション登録（POST）
// ================================
	@PostMapping("/variants")
	public String create(
      @PathVariable Long itemId,
      @ModelAttribute ItemVariant variant,
      @RequestParam("imageFile") MultipartFile imageFile
) throws IOException {

		 String savedFileName = saveImage(imageFile);  // 共通化
	    variant.setImageFilename(savedFileName);
	    variant.setItemId(itemId);

	    variantService.insert(variant);
	    return "redirect:/items/" + itemId;
	}
	
  // ================================
  // バリエーション編集画面（GET）
  // ================================
	@GetMapping("/variants/{variantId}/edit")
	public String showEditForm(
			@PathVariable Long itemId,
			@PathVariable Long variantId,
			Model model
			) {
		ItemVariant variant = variantService.findById(variantId);
		
		model.addAttribute("variant",variant);
		model.addAttribute("item",itemService.findById(itemId));
		model.addAttribute("categories", getCategoryList());
		
		return "variants/edit";
	}
	
	//================================
  // バリエーション更新（POST）
  // ================================
	@PostMapping("/variants/{variantId}/edit")
	public String update(
			@PathVariable Long itemId,
			@PathVariable Long variantId,
			@ModelAttribute ItemVariant variant,
			@RequestParam("imageFile") MultipartFile imageFile,
			RedirectAttributes redirectAttributes
			) throws IOException {
		
		// 既存データを取得（画像ファイル名を保持するため）
		ItemVariant old = variantService.findById(variantId);
		String oldFilename = old.getImageFilename();
		
	// ★ サイズ制限（3MB上限）
    final long MAX_FILE_SIZE = 3 * 1024 * 1024;
    // --- 画像を新規アップロードした場合のみ ---
    if (!imageFile.isEmpty() && imageFile.getSize() > MAX_FILE_SIZE) {
    	redirectAttributes.addFlashAttribute("errorMessage", "画像サイズは3MB以下にしてください！");
      return "redirect:/items/" + itemId + "/variants/" + variantId + "/edit";
      }

    // 新しい画像がある？→保存＆古いの削除
    if(!imageFile.isEmpty()) {
    	String newFilename = saveImage(imageFile);
    	
    	if (oldFilename != null) {
    		deleteImageFile(oldFilename);
    }
    	
    	variant.setImageFilename(newFilename);
    
    } else {
    	// 画像なし（ → 既存のファイル名を維持）
    	variant.setImageFilename(oldFilename);
    }
    // IDが無いと更新できないのでセット
    variant.setId(variantId);
    // 更新処理
    variantService.update(variant);
    
    return "redirect:/items/" + itemId + "/variants/" + variantId + "/edit";
	}
    
  // ================================
  // 画像の削除
  // ================================
		@PostMapping("/variants/{variantId}/delete-image")
		public String deleteImage(
				@PathVariable Long itemId,
				@PathVariable Long variantId
				){
			
			// 現在のバリエーション(＝元の画像名)を取得
			ItemVariant variant = variantService.findById(variantId);
			String oldFilename = variant.getImageFilename();
			
			try {
				if (oldFilename != null) {
          deleteImageFile(oldFilename);
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		// DBの image_filename を null に更新
			variantService.clearImage(variantId);
		// 編集画面に戻る
			return "redirect:/items/" + itemId + "/variants/" + variantId + "/edit";
		}
			
  // ================================
  // 在庫更新
  // ================================	
  @PostMapping("/variants/{variantId}/stock/update")
  public String updateStock(
          @PathVariable Long itemId,
          @PathVariable Long variantId,
          // @RequestParam("stock") Integer stock
          @RequestParam(defaultValue="0") int addQty,
          @RequestParam(defaultValue="0") int subQty,
          @RequestParam(required = false) Integer newStock
  		) {
  	
  	ItemVariant variant = variantService.findById(variantId);
  	int currentStock = variant.getStock();
  	
  	int updatedStock;
  	
  	// ①newStock（棚卸）が入力されている場合→最優先
  	if(newStock != null) {
  		updatedStock = newStock;
  	}
  	// ②追加・減少で計算
  	else {
  		updatedStock = currentStock + addQty - subQty;
  	}
  	
  	if(updatedStock < 0) {
  		updatedStock = 0; //在庫がマイナスにならないようにする
  	}
  	variantService.updateStock(variantId, updatedStock);
  	
      // variantService.updateStock(variantId, stock);
//    return "redirect:/items/" + itemId;
      return "redirect:/items/" + itemId + "/variants/" + variantId + "/edit";
  }
	
  // ================================
  // バリエーション削除
  // ================================ 
	@PostMapping("/variants/{variantId}/delete")
	public String delete(
			@PathVariable Long itemId,
			@PathVariable Long variantId
			) {
		variantService.delete(variantId);
		return "redirect:/items/" + itemId;
	}
	
	// ======================================================
  // ▼▼ 共通メソッド（画像保存・削除）▼▼
  // ======================================================
	private String saveImage(MultipartFile file) throws IOException{
		if(file == null || file.isEmpty())  return null;
		
		Path path = Paths.get(uploadDir);
		if(!Files.exists(path)) {
			Files.createDirectories(path);
		}
		
		 String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
     String filename = timestamp + "_" + file.getOriginalFilename();
		
     Path full = path.resolve(filename);
     Files.copy(file.getInputStream(),full);
     
     return filename;
	}
     
  // ▼▼ 共通メソッド（画像削除）▼
     private void deleteImageFile(String filename) throws IOException {
       Path file = Paths.get(uploadDir).resolve(filename);
       Files.deleteIfExists(file);
   }

	}
