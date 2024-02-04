package com.chr.ourhome.web;

import java.awt.print.Pageable;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chr.ourhome.domain.item.Item;
import com.chr.ourhome.service.ItemService;
import com.chr.ourhome.web.dto.ItemFormDto;
import com.google.common.base.Optional;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService itemService;
	
	@GetMapping("/admin/item/new")
	public String itemForm(Model model) {
		model.addAttribute("itemFormDto", new ItemFormDto());
		return "item/itemForm";
	}
	
	@PostMapping(value="/admin/item/new")
	public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult, Model model,
												@RequestParam(name="itemImgFile") List<MultipartFile> itemImgFileList) {
		
		if(bindingResult.hasErrors()) {
			return "item/itemForm";
		}
		
		if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId()==null) {
			model.addAttribute("errorMessage", "첫 번째 상품 이미지는 필수 입력 값 입니다.");
			
			return "item/itemForm";
		}
		
		try {
			itemService.saveItem(itemFormDto, itemImgFileList);
		}catch (Exception e) {
			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
			return "item/itemForm";
		}
		return "redirect:/";
	}
	
	@GetMapping("/admin/item/{itemId}")
	public String itemDtl(@PathVariable("itemId") Long itemId, Model model) {
		
		try {
			ItemFormDto itemFormDto=itemService.getItemDtl(itemId);
			model.addAttribute("itemFormDto", itemFormDto);
		} catch (EntityNotFoundException e) {
			// TODO: handle exception
			model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
			model.addAttribute("itemFomDto", new ItemFormDto());
			return "item/itemForm";
		}
		return "item/itemForm";
	}
	
	// 상품 수정 Controller
	// 상품 등록 Controller와 거의 동일 (itemService.saveItem -> updateItem)
	@PostMapping("/admin/item/{itemId}")
	public String itemUpdate(@Valid ItemFormDto itemFormDto, BindingResult bindingResult, Model model,
													@RequestParam(name="itemImgFile") List<MultipartFile> itemImgFileList) {
		
		if(bindingResult.hasErrors()) {
			return "item/itemForm";
		}
		
		if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId()==null) {
			model.addAttribute("errorMessage", "첫 번째 상품 이미지는 필수 입력 값 입니다.");
			return "item/itemForm";
		}
		
		try {
			itemService.updateItem(itemFormDto, itemImgFileList);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
			return "item/itemForm";
		}
		
		return "redirect:/";
	}
	
//	// 요청 URL에 페이지 번호가 없는 경우와 있는 경우 2가지를 매핑
//	@GetMapping({"/admin/items", "/admin/items/{page}"})
//	public String itemManage(ItemSearchDto itemSearchDto,
//													@PathVariable("page") Optional<Integer> page,
//													Model model) {
//		Pageable pageable=PageRequest.of(page.isPresent() ? page.get() : 0, 3);
//		Page<Item> items=itemService.getAdminItemPage(itemSearchDto, pageable);
//		model.addAttribute("items", items);
//		model.addAttribute("itemSearchDto", itemSearchDto);
//		model.addAttribute("maxPage", 5);
//		return "item/itemMng";
//	}
}
