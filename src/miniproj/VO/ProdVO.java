package miniproj.VO;

public class ProdVO {
    private String product_id;
    private String product_name;
    private String color;
    private String pSize;
    private int price;
    private int stock_quantity;

    public ProdVO(String product_id, String product_name, String color, String pSize, int price, int stock_quantity) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.color = color;
        this.pSize = pSize;
        this.price = price;
        this.stock_quantity = stock_quantity;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getpSize() {
        return pSize;
    }

    public void setpSize(String pSize) {
        this.pSize = pSize;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }
}

