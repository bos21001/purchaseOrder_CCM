package com.ccm.purchaseorder_ccm;

public class OrderProducts {
    private String productId;
    private String amount;

    public OrderProducts(String productId, String amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public OrderProducts() {

    }
}
