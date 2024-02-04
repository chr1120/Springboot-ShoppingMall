package com.chr.ourhome.test;

import java.time.LocalDateTime;
import java.util.List;

import com.chr.ourhome.constant.ItemSellStatus;
import com.chr.ourhome.domain.item.Item;
import com.chr.ourhome.domain.item.ItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindItemTest {

	private ItemRepository itemRepository;
	
	public void createItemList() {
		for(int i=1; i<=10; i++) {
			Item item=new Item();
			item.setItemName("테스트 상품"+i);
			item.setPrice(10000+i);
			item.setItemDetail("테스트 상품 상세 설명"+i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStock(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			Item savedItem=itemRepository.save(item);
			System.out.println(savedItem.toString());
		}
	}
	
	public void findByItemNmTest() {
		this.createItemList();
		List<Item> itemList=itemRepository.findByItemName("테스트 상품1");
		for (Item item : itemList) {
			System.out.println("findByItemNmTest: "+item.toString());
		}
	}
	
	public void findByItemDetailByNativeTest() {
		this.createItemList();
		List<Item> itemList=this.itemRepository.findByItemDetail("테스트 상품 상세 설명");
		for(Item item : itemList) {
			System.out.println("findByItemDetailByNativeTest: "+item.toString());
		}
	}
}
