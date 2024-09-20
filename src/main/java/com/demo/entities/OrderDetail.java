package com.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	 @ManyToOne
	 @JoinColumn(name = "order_id")
	private Order order;
	 
	 @ManyToOne
	 @JoinColumn(name = "poster_id ")
	private Poster poster; 
	 @ManyToOne
	 @JoinColumn(name = "size_id")
	private Size size;
	 @ManyToOne
	 @JoinColumn(name = "frame_id")
	 private Frame frame;
	public OrderDetail(int id, Order order, Poster poster, Size size, Frame frame) {
		super();
		this.id = id;
		this.order = order;
		this.poster = poster;
		this.size = size;
		this.frame = frame;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public Poster getPoster() {
		return poster;
	}


	public void setPoster(Poster poster) {
		this.poster = poster;
	}


	public Size getSize() {
		return size;
	}


	public void setSize(Size size) {
		this.size = size;
	}


	public Frame getFrame() {
		return frame;
	}


	public void setFrame(Frame frame) {
		this.frame = frame;
	}


	public OrderDetail() {
		super();
	}


	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", order=" + order + ", poster=" + poster + ", size=" + size + ", frame="
				+ frame + "]";
	}
	 
	 
	 
}
