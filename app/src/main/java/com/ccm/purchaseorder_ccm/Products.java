package com.ccm.purchaseorder_ccm;

public class Products {
    private String productDescription;
    private String productId;

    public Products(String productDescription, String productId) {
        this.productDescription = productDescription;
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Products() {
    }
}
