package com.ccm.purchaseorder_ccm;

public class Order {
    private String clientId;
    private String orderId;
    private String productId;

    public Order(String clientId, String orderId, String productId) {
        this.clientId = clientId;
        this.orderId = orderId;
        this.productId = productId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Order() {
    }


}
