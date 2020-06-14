package org.csu.jpetstore.dao;

import org.apache.ibatis.annotations.*;
import org.csu.jpetstore.bean.Order;
import org.csu.jpetstore.bean.Product;

import java.util.List;

@Mapper
public interface OrderDao {

    @Select("select * from Orders")
    List<Order> findAllOrders();

    @Select("select * from Orders where userId=#{userId}")
    List<Order> findOrdersByUserId(@Param("userId") String userId);

    @Select("select * from Orders where supplierId=#{supplierId}")
    List<Order> findOrdersBySupplierId(@Param("supplierId") String supplierId);

    @Select("select * from Orders where productId=#{productId}")
    List<Order> findOrdersByProductId(@Param("productId") String productId);

    @Select("select * from Orders where id=#{id}")
    Order findOrderByID(@Param("id") String id);

    @Insert("INSERT into Orders (id, userId, productId, supplierId, quantity, productName, created, status, shipaddr1, shipaddr2, shipcity, shipstate, shipzip, shipcountry, billaddr1, billaddr2, billcity, billstate, billzip, billcountry, courier, total, billtofirstname, billtolastname, shiptofirstname, shiptolastname, creditcard, exprdate, cardtype, locale) VALUES(#{id}, #{userId}, #{productId}, #{supplierId}, #{quantity}, #{productName}, #{created}, #{status}, #{shipaddr1}, #{shipaddr2}, #{shipcity}, #{shipstate}, #{shipzip}, #{shipcountry}, #{billaddr1}, #{billaddr2}, #{billcity}, #{billstate}, #{billzip}, #{billcountry}, #{courier}, #{total}, #{billtofirstname}, #{billtolastname}, #{shiptofirstname}, #{shiptolastname}, #{creditcard}, #{exprdate}, #{cardtype}, #{locale})")
    void insertOrder(Order order);

    @Update("UPDATE Orders SET userId=#{userId}, productId=#{productId}, supplierId=#{supplierId}, quantity=#{quantity}, productName=#{productName}, created=#{created}, status=#{status}, shipaddr1=#{shipaddr1}, shipaddr2=#{shipaddr2}, shipcity=#{shipcity}, shipstate=#{shipstate}, shipzip=#{shipzip}, shipcountry=#{shipcountry}, billaddr1=#{billaddr1}, billaddr2=#{billaddr2}, billcity=#{billcity}, billstate=#{billstate}, billzip=#{billzip}, billcountry=#{billcountry}, courier=#{courier}, total=#{total}, billtofirstname=#{billtofirstname}, billtolastname=#{billtolastname}, shiptofirstname=#{shiptofirstname}, shiptolastname=#{shiptolastname}, creditcard=#{creditcard}, exprdate=#{exprdate}, cardtype=#{cardtype}, locale=#{locale} WHERE id=#{id}")
    void updateOrder(Order order);

    @Delete("DELETE FROM Order WHERE id=#{id}")
    void deleteOrder(@Param("id") String id);

}
