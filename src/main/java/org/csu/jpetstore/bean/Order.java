package org.csu.jpetstore.bean;

public class Order {
    private Integer orderid; //	int(11)	NO	主鍵，AUTO_INCREMENT
    private Integer userId; //	varchar(80)	NO	對應 Account 表的 id
    private Integer productId; //	int	No	購買的商品 id
    private Integer supplierId; //	int	No	由 service 自動填寫
    private Integer quantity; //	int	No	購買數量
    private String productName; //	varchar(80)	No	由 service 自動填寫
    private String created; //	date	NO	新增訂單的日期，系統自動填寫
    private String status; //	varchar(20)	No	目前制定了三種狀態，Cancel, Pending, Active，分別代表取消、待發貨、完成，預設值為 Pending，系統自動填寫
    private String shipaddr1; //	varchar(80)	NO
    private String shipaddr2; //	varchar(80)	YES
    private String shipcity; //	varchar(80)	NO
    private String shipstate; //	varchar(80)	NO
    private String shipzip; //	varchar(20)	NO
    private String shipcountry; //	varchar(20)	NO
    private String billaddr1; //	varchar(80)	NO
    private String billaddr2; //	varchar(80)	YES
    private String billcity; //	varchar(80)	NO
    private String billstate; //	varchar(80)	NO
    private String billzip; //	varchar(20)	NO
    private String billcountry; //	varchar(20)	NO
    private String courier; //	varchar(80)	NO	貨運公司
    private Integer total; //	decimal(10,2)	NO	訂單總價，系統填寫
    private String billtofirstname; //	varchar(80)	NO	收件人名字
    private String billtolastname; //	varchar(80)	NO
    private String shiptofirstname; //	varchar(80)	NO
    private String shiptolastname; //	varchar(80)	NO
    private String creditcard; //	varchar(80)	NO
    private String exprdate; //	varchar(7)	NO
    private String cardtype; //	varchar(80)	NO
    private String locale; //	varchar(80)

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShipaddr1() {
        return shipaddr1;
    }

    public void setShipaddr1(String shipaddr1) {
        this.shipaddr1 = shipaddr1;
    }

    public String getShipaddr2() {
        return shipaddr2;
    }

    public void setShipaddr2(String shipaddr2) {
        this.shipaddr2 = shipaddr2;
    }

    public String getShipcity() {
        return shipcity;
    }

    public void setShipcity(String shipcity) {
        this.shipcity = shipcity;
    }

    public String getShipstate() {
        return shipstate;
    }

    public void setShipstate(String shipstate) {
        this.shipstate = shipstate;
    }

    public String getShipzip() {
        return shipzip;
    }

    public void setShipzip(String shipzip) {
        this.shipzip = shipzip;
    }

    public String getShipcountry() {
        return shipcountry;
    }

    public void setShipcountry(String shipcountry) {
        this.shipcountry = shipcountry;
    }

    public String getBilladdr1() {
        return billaddr1;
    }

    public void setBilladdr1(String billaddr1) {
        this.billaddr1 = billaddr1;
    }

    public String getBilladdr2() {
        return billaddr2;
    }

    public void setBilladdr2(String billaddr2) {
        this.billaddr2 = billaddr2;
    }

    public String getBillcity() {
        return billcity;
    }

    public void setBillcity(String billcity) {
        this.billcity = billcity;
    }

    public String getBillstate() {
        return billstate;
    }

    public void setBillstate(String billstate) {
        this.billstate = billstate;
    }

    public String getBillzip() {
        return billzip;
    }

    public void setBillzip(String billzip) {
        this.billzip = billzip;
    }

    public String getBillcountry() {
        return billcountry;
    }

    public void setBillcountry(String billcountry) {
        this.billcountry = billcountry;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getBilltofirstname() {
        return billtofirstname;
    }

    public void setBilltofirstname(String billtofirstname) {
        this.billtofirstname = billtofirstname;
    }

    public String getBilltolastname() {
        return billtolastname;
    }

    public void setBilltolastname(String billtolastname) {
        this.billtolastname = billtolastname;
    }

    public String getShiptofirstname() {
        return shiptofirstname;
    }

    public void setShiptofirstname(String shiptofirstname) {
        this.shiptofirstname = shiptofirstname;
    }

    public String getShiptolastname() {
        return shiptolastname;
    }

    public void setShiptolastname(String shiptolastname) {
        this.shiptolastname = shiptolastname;
    }

    public String getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(String creditcard) {
        this.creditcard = creditcard;
    }

    public String getExprdate() {
        return exprdate;
    }

    public void setExprdate(String exprdate) {
        this.exprdate = exprdate;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
