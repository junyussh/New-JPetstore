package org.csu.jpetstore.bean;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Item {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int supplierid;
    private String productid;
    private int quantity;
    private int uniprice;
    private int unicost;
    private String attribute;

    public Item(){
    }

    public Item(String id, int supplierid, String productid, int uniprice, int unicost, int quantity, String attribute){
        this.id = id;
        this.supplierid = supplierid;
        this.productid = productid;
        this.quantity = quantity;
        this.uniprice = uniprice;
        this.unicost = unicost;
        this.attribute = attribute;
    }

    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUniprice() {
        return uniprice;
    }

    public void setUniprice(int uniprice) {
        this.uniprice = uniprice;
    }

    public int getUnicost() {
        return unicost;
    }

    public void setUnicost(int unicost) {
        this.unicost = unicost;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
