package Beans;



import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	private Map<Item, Integer> items;

	public Map<Item, Integer> getItems() {
		return items;
	}

	public void setItems(Map<Item, Integer> items) {
		this.items = items;
	}

	public Cart() {
		this.items = new HashMap<Item,Integer>();
	}
	
	public int getOrder(Item item) {
		return items.get(item);
	}
	
	public void put(Item i, int quantity)
	{
		this.items.put(i, quantity);
	}
	
	public void empty()
	{
		this.items = new HashMap<Item,Integer>();
	}
	

}
