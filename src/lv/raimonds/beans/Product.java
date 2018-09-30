package lv.raimonds.beans;


public class Product {
    private String productName;
    private double oneProductPrice;
    private int qty;
    public Product(){

    }

    public Product(String productName,int qty, double oneProductPrice) {
        this.productName = productName;
        this.oneProductPrice = oneProductPrice;
        this.qty = qty;

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getOneProductPrice() {
        return oneProductPrice;
    }

    public void setOneProductPrice(double oneProductPrice) {
        this.oneProductPrice = oneProductPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }


}

