package Estore;

import java.util.LinkedHashSet;
import java.util.Set;

public class Customer {

	public static long id;
	private String name;
	private Set<Order> orders = new LinkedHashSet<Order>();
	private Set<Item> items = new LinkedHashSet<Item>();

	public static long getId() {
		return id;
	}

	public static void setId(long id) {
		Customer.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

}
