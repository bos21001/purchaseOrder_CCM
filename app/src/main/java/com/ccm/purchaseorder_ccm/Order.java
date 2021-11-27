package com.ccm.purchaseorder_ccm;



public class Order  {
    private String ClientId;
    private String OrderId;

    public Order(String clientId, String orderId) {
        ClientId = clientId;
        OrderId = orderId;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public Order() {
    }


}
