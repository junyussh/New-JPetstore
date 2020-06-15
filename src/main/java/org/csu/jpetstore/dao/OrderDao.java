package org.csu.jpetstore.dao;

import org.apache.ibatis.annotations.*;
import org.csu.jpetstore.bean.Order;

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

    @Insert("INSERT into Orders (id, userId, itemId, productId, supplierId, itemAttribute, productName, supplierName, quantity, total, status, created, shipAddr1, shipAddr2, shipCity, shipState, shipZip, shipCountry, courier, billToFirstName, billToLastName, creditCard, exprDate, cardType) VALUES" +
            "(#{id}, #{userId}, #{itemId}, #{productId}, #{supplierId}, #{itemAttribute}, #{productName}, #{supplierName}, #{quantity}, #{total}, #{status}, #{created}, #{shipAddr1}, #{shipAddr2}, #{shipCity}, #{shipState}, #{shipZip}, #{shipCountry}, #{courier}, #{billToFirstName}, #{billToLastName}, #{creditCard}, #{exprDate}, #{cardType})")
    void insertOrder(Order order);

    @Update("UPDATE Orders SET userId=#{userId}, productId=#{productId}, productName=#{productName}, supplierId=#{supplierId}, quantity=#{quantity}, total=#{total}, status=#{status}, created=#{created}, shipAddr1=#{shipAddr1}, shipAddr2=#{shipAddr2}, shipCity=#{shipCity}, shipState=#{shipState}, shipZip=#{shipZip}, shipCountry=#{shipCountry}, courier=#{courier}, billToFirstName=#{billToFirstName}, billToLastName=#{billToLastName}, creditCard=#{creditCard}, exprDate=#{exprDate}, cardType=#{cardType} WHERE id=#{id}")
    void updateOrder(Order order);

    @Delete("DELETE FROM Orders WHERE id=#{id}")
    void deleteOrder(@Param("id") String id);

}
