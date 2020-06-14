package org.csu.jpetstore.dao;

import org.apache.ibatis.annotations.*;
import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.bean.Item;
import org.mapstruct.Mapper;
import org.omg.CORBA.INTERNAL;

import java.util.List;

@Mapper
public interface ItemDao {


    @Insert("INSERT into item (id, productId, supplierId, uniprice, unicost, quantity, attribute) " +
            "VALUES(#{id}, #{productid}, #{supplierid}, #{uniprice}, #{unicost}, #{quantity}, #{attribute})")
    void insertItem(Item item);

    @Update("UPDATE Item SET supplierId=#{supplier}, productId=#{product}, quantity=#{quantity}, uniprice=#{uniprice}, unicost=#{unicost}, attribute=#{attribute}")
    void updateItemInfo(Item item);

    @Select("select * from Item where productId = #{productid}")
    List<Item> findItemByProductid(@Param("productid") String productid);

    @Select("select * from Item where supplierId = #{supplierid}")
    List<Item> findItemBySuppplierid(@Param("supplierid")  int supplierid);

    @Delete("DELETE FROM Item WHERE id=#{id}")
    void deleteItem(@Param("id") String id);


    @Select("select * from Item where id = #{id}")
    List<Item> findItemById(@Param("id") String id);

}

