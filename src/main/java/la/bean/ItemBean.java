package la.bean;

import java.io.Serializable;
import java.util.Date;

public class ItemBean implements Serializable {
	private int item_id;
	private int category_id;
	private String item_name;
	private int price;
	private int stock;
	private int seller_id;
	private Date start_date;
	private Date stop_date;
	private int sales_figures;

	private int quantity;

	public ItemBean(int item_id, String item_name, int category_id, int stock, int price, Date start_date,
			int seller_id, Date stop_date) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.category_id = category_id;
		this.stock = stock;
		this.price = price;
		this.start_date = start_date;
		this.seller_id = seller_id;
		this.stop_date = stop_date;
	}
	
	public ItemBean(int item_id, String item_name, int category_id, int stock, int price, Date start_date,
			int seller_id) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.category_id = category_id;
		this.stock = stock;
		this.price = price;
		this.start_date = start_date;
		this.seller_id = seller_id;
	}
	
	public ItemBean(int item_id, String item_name, int price, int stock, Date stop_date, Date start_date,
			int sales_figures) {
		this.item_id = item_id;
		this.item_name = item_name;
		this.price = price;
		this.stock = stock;
		this.stop_date = stop_date;
		this.start_date = start_date;
		this.sales_figures = sales_figures;
	}

	public ItemBean(int item_id, String item_name, int price, int stock, Date stop_date, Date start_date) {
		this.item_id = item_id;
		this.item_name = item_name;
		this.price = price;
		this.stock = stock;
		this.stop_date = stop_date;
		this.stop_date = start_date;
	}

	public ItemBean(int item_id, String item_name, int price, int stock) {
		this.item_id = item_id;
		this.item_name = item_name;
		this.price = price;
		this.stock = stock;
	}

	public ItemBean(int item_id, String item_name, int price, int quantity, int stock) {
		this.item_id = item_id;
		this.item_name = item_name;
		this.price = price;
		this.quantity = quantity;
		this.stock = stock;
	}

	public ItemBean() {

	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	

	public int getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}

	public Date getStop_date() {
		return stop_date;
	}

	public void setStop_date(Date stop_date) {
		this.stop_date = stop_date;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public int getSales_figures() {
		return sales_figures;
	}

	public void setSales_figures(int sales_figures) {
		this.sales_figures = sales_figures;
	}
	

}