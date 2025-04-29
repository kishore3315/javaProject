package com.example.ecommerce_order_management;

import java.util.ArrayList;
import java.util.List;

import com.example.ecommerce_order_management.Item;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "`order`")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Double totalprice;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToMany
	@JoinTable(name="order_items", joinColumns=@JoinColumn(name="order_id"),inverseJoinColumns=@JoinColumn(name="item_id"))
	private List<Item> itemsList=new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Item> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<Item> itemsList) {
		this.itemsList = itemsList;
	}

}
