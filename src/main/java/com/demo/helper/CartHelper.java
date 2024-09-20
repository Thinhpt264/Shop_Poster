package com.demo.helper;

import java.util.List;

import com.demo.entities.Item;

public class CartHelper {
	public static int findIndexCart(List<Item> items, int poster_id, int size_id, int frame_id ) {
		for(int i = 0; i < items.size() ; i++ ) {
			if(items.get(i).getPoster().getId() == poster_id && items.get(i).getSelected_frame().getId() == frame_id &&  items.get(i).getSelected_size().getId() == size_id) {
				return i;
			}
		}
		return -1;
	}
	
	public static double total(List<Item> cart) {
		double total = 0;
		for (Item item : cart) {
			total += ((item.getPoster().getPrice()+item.getSelected_size().getPrice())*item.getQuantity());
		}
		return total;
	}
}
