package org.csu.jpetstore.bean;

import java.math.BigDecimal;

public class Item {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String supplier;
    private String product;
    private int quantity;
    private int uniprice;
    private int unicost;
    private String attribute;

    public Item(){
    }

    public Item(String id, String suppplier, String product, int quantity, int unitprice, int unitcost, String attribute){
        this.id = id;
        this.supplier = suppplier;
        this.product = product;
        this.quantity = quantity;
        this.uniprice = unitprice;
        this.unicost = unitcost;
        this.attribute = attribute;
    }

    public String getSuppplier() {
        return supplier;
    }

    public void setSuppplier(String suppplier) {
        this.supplier = suppplier;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitprice() {
        return uniprice;
    }

    public void setUnitprice(int unitprice) {
        this.uniprice = unitprice;
    }

    public int getUnitcost() {
        return unicost;
    }

    public void setUnitcost(int unitcost) {
        this.unicost = unitcost;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
