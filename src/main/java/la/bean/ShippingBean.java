package la.bean;

import java.io.Serializable;

public class ShippingBean implements Serializable {
    private int shipping_id;
    private String shipping_name;
    private String shipping_address;
    private String shipping_tel;
    private String shipping_email;

    public ShippingBean(int shipping_id, String shipping_name, String shipping_address, String shipping_tel,
            String shipping_email) {
        this.shipping_id = shipping_id;
        this.shipping_name = shipping_name;
        this.shipping_address = shipping_address;
        this.shipping_tel = shipping_tel;
        this.shipping_email = shipping_email;
    }

    public ShippingBean() {

    }

    public int getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(int shipping_id) {
        this.shipping_id = shipping_id;
    }

    public String getShipping_name() {
        return shipping_name;
    }

    public void setShipping_name(String shipping_name) {
        this.shipping_name = shipping_name;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getShipping_tel() {
        return shipping_tel;
    }

    public void setShipping_tel(String shipping_tel) {
        this.shipping_tel = shipping_tel;
    }

    public String getShipping_email() {
        return shipping_email;
    }

    public void setShipping_email(String shipping_email) {
        this.shipping_email = shipping_email;
    }
}