package com.ccm.purchaseorder_ccm;

import androidx.annotation.NonNull;

public class DB_PurchaseOrder {

    private String productId;
    private String description;
    private Integer amount;

    public DB_PurchaseOrder(String productId, String description, Integer amount) {
        this.productId = productId;
        this.description = description;
        this.amount = amount;
    }

    public DB_PurchaseOrder(){}

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @NonNull
    @Override
    public String toString() {
        return productId + " " + description + " " + amount;
    }
}
