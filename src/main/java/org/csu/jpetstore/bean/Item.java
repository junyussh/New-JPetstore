package org.csu.jpetstore.bean;

public class Item {
    private Integer id;
    private Integer supplierId;
    private Integer productId;
    private Integer quantity;
    private Float unitprice;
    private Float unitcost;
    private String attribute;

    public Item() {
    }

    public Item(Integer id, Integer supplierId, Integer productId, Integer quantity, Float unitprice, Float unitcost, String attribute) {
        this.id = id;
        this.supplierId = supplierId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.unitcost = unitcost;
        this.attribute = attribute;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Float unitprice) {
        this.unitprice = unitprice;
    }

    public Float getUnitcost() {
        return unitcost;
    }

    public void setUnitcost(Float unitcost) {
        this.unitcost = unitcost;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
