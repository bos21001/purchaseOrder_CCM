package com.ccm.purchaseorder_ccm;

import androidx.annotation.NonNull;

public class DB_ProductOrder {

    private String productId;
    private String serialNumber;
    private Integer location;

    public DB_ProductOrder(String productId, String serialNumber, Integer location) {
        this.productId = productId;
        this.serialNumber = serialNumber;
        this.location = location;
    }

    public DB_ProductOrder() {}

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    @NonNull
    @Override
    public String toString() {
        return productId + "     " + serialNumber + "     " + location ;
    }
}
