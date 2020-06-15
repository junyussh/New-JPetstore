package org.csu.jpetstore.bean;

import java.sql.Timestamp;

public class Order {
    private Integer id;
    private Integer userId;
    private Integer itemId;
    private Integer productId;
    private Integer supplierId;
    private String itemAttribute;
    private String productName;
    private String supplierName;
    private Integer quantity;
    private Float total;
    private String status;
    private Timestamp created;
    private String shipAddr1;
    private String shipAddr2;
    private String shipCity;
    private String shipState;
    private String shipZip;
    private String shipCountry;
    private String courier;
    private String billToFirstName;
    private String billToLastName;
    private String creditCard;
    private String exprDate;
    private String cardType;

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShipAddr1() {
        return shipAddr1;
    }

    public void setShipAddr1(String shipAddr1) {
        this.shipAddr1 = shipAddr1;
    }

    public String getShipAddr2() {
        return shipAddr2;
    }

    public void setShipAddr2(String shipAddr2) {
        this.shipAddr2 = shipAddr2;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipState() {
        return shipState;
    }

    public void setShipState(String shipState) {
        this.shipState = shipState;
    }

    public String getShipZip() {
        return shipZip;
    }

    public void setShipZip(String shipZip) {
        this.shipZip = shipZip;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getBillToFirstName() {
        return billToFirstName;
    }

    public void setBillToFirstName(String billToFirstName) {
        this.billToFirstName = billToFirstName;
    }

    public String getBillToLastName() {
        return billToLastName;
    }

    public void setBillToLastName(String billToLastName) {
        this.billToLastName = billToLastName;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getExprDate() {
        return exprDate;
    }

    public void setExprDate(String exprDate) {
        this.exprDate = exprDate;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemAttribute() {
        return itemAttribute;
    }

    public void setItemAttribute(String itemAttribute) {
        this.itemAttribute = itemAttribute;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
