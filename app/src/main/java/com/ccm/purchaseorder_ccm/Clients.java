package com.ccm.purchaseorder_ccm;



public class Clients {
    private String id;
    private String name;

    public Clients(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clients(){

    }


}
