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

    private String suppplier;
    private String product;
    private int quantity;
    private int unitprice;
    private int unitcost;
    private String attribute;

    public Item(){
    }

    public Item(String id, String suppplier, String product, int quantity, int unitprice, int unitcost, String attribute){
        this.id = id;
        this.suppplier = suppplier;
        this.product = product;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.unitcost = unitcost;
        this.attribute = attribute;
    }

    public String getSuppplier() {
        return suppplier;
    }

    public void setSuppplier(String suppplier) {
        this.suppplier = suppplier;
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
        return unitprice;
    }

    public void setUnitprice(int unitprice) {
        this.unitprice = unitprice;
    }

    public int getUnitcost() {
        return unitcost;
    }

    public void setUnitcost(int unitcost) {
        this.unitcost = unitcost;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
