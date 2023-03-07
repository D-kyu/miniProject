package miniproj.VO;

public class CartVO {
    private String customer_id;
    private String product_id;
    private int quantity;
    private String product_name;
    private int price;
    private int ttl;

    public CartVO(String customer_id, String product_id, int quantity,
                  String product_name, int price, int ttl) {
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.product_name = product_name;
        this.price = price;
        this.ttl = ttl;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
}