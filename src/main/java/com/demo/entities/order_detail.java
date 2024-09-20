package com.demo.entities;

import jakarta.persistence.Id;

public class order_detail {
	@Id
	private int id;
	
	private int poster_id;

	private int order_id;
	
	private int size_id;
	
	private int frame_id;

	public order_detail(int id, int poster_id, int order_id, int size_id, int frame_id) {
		super();
		this.id = id;
		this.poster_id = poster_id;
		this.order_id = order_id;
		this.size_id = size_id;
		this.frame_id = frame_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPoster_id() {
		return poster_id;
	}

	public void setPoster_id(int poster_id) {
		this.poster_id = poster_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getSize_id() {
		return size_id;
	}

	public void setSize_id(int size_id) {
		this.size_id = size_id;
	}

	public int getFrame_id() {
		return frame_id;
	}

	public void setFrame_id(int frame_id) {
		this.frame_id = frame_id;
	}
	
	
}
