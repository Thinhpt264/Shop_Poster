package com.demo.entities;

public class Item {
	private Poster poster;
	private Size selected_size;
	private Frame selected_frame;
	private int quantity;
	public Item(Poster poster, Size selected_size, Frame selected_frame, int quantity) {
		super();
		this.poster = poster;
		this.selected_size = selected_size;
		this.selected_frame = selected_frame;
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Item [poster=" + poster + ", selected_size=" + selected_size + ", selected_frame=" + selected_frame
				+ ", quantity=" + quantity + "]";
	}
	public Poster getPoster() {
		return poster;
	}
	public void setPoster(Poster poster) {
		this.poster = poster;
	}
	public Size getSelected_size() {
		return selected_size;
	}
	public void setSelected_size(Size selected_size) {
		this.selected_size = selected_size;
	}
	public Frame getSelected_frame() {
		return selected_frame;
	}
	public void setSelected_frame(Frame selected_frame) {
		this.selected_frame = selected_frame;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
