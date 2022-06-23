package la.bean;

import java.io.Serializable;
import java.util.Date;

public class SellerBean implements Serializable {
	
	private int item_id;
	private String item_name;
    private int category_id;
    //private String category;
    private int stock;
    private int price;
    private Date start_date;
    private int member_id;	//seller_id
    // nullable
    private Date stop_date;
	/**
	 * @param item_id
	 * @param item_name
	 * @param category_id
	 * @param stock
	 * @param price
	 * @param start_date
	 * @param member_id
	 * @param stop_date
	 */
	public SellerBean(int item_id, String item_name, int category_id, int stock, int price, Date start_date,
			int member_id, Date stop_date) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.category_id = category_id;
		this.stock = stock;
		this.price = price;
		this.start_date = start_date;
		this.member_id = member_id;
		this.stop_date = stop_date;
	}
	/**
	 * @param item_id
	 * @param item_name
	 * @param category_id
	 * @param stock
	 * @param price
	 * @param start_date
	 * @param member_id
	 */
	public SellerBean(int item_id, String item_name, int category_id, int stock, int price, Date start_date,
			int member_id) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.category_id = category_id;
		this.stock = stock;
		this.price = price;
		this.start_date = start_date;
		this.member_id = member_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public Date getStop_date() {
		return stop_date;
	}
	public void setStop_date(Date stop_date) {
		this.stop_date = stop_date;
	}

    
    
    
}
