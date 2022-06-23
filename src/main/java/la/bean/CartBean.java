package la.bean;

import java.util.ArrayList;
import java.util.List;

public class CartBean {
    private List<ItemBean> items = new ArrayList<ItemBean>();
    private int total;

    public CartBean() {
    }

    public List<ItemBean> getItems() {
        return items;
        }

    public void addCart(ItemBean bean, int nums) {
        ItemBean item = null;
        for (ItemBean i : items) {
            if (i.getItem_id() == bean.getItem_id()) {
                item = i;
                break;
            }
        }
		
        if (item == null) {
            bean.setQuantity(nums);
            items.add(bean);
        } else {
            item.setQuantity(nums + item.getQuantity());
        }
        recalcTotal();
    }

    public void deleteCart(int itemCode) {
        for (ItemBean item : items) {
            if (item.getItem_id() == itemCode) {
                items.remove(item);
                break;
            }
        }
        recalcTotal();
    }

    public int getTotal() {
        return total;
    }

    private void recalcTotal() {
        total = 0;
        for (ItemBean item : items) {
            total += item.getPrice() * item.getQuantity();
        }
    }
}