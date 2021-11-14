package com.ccm.purchaseorder_ccm;

import androidx.annotation.NonNull;

public class DB_PurchaseOrderList {
    private String orderId;
    private String client;

    public DB_PurchaseOrderList(String orderId, String client) {
        this.orderId = orderId;
        this.client = client;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @NonNull
    @Override
    public String toString() {
        return orderId + "    " + client;
    }
}
