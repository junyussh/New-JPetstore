package org.csu.jpetstore.dao;

import org.apache.ibatis.annotations.*;
import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.bean.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ItemDao {


    @Insert("INSERT into Item (id, suppplier, product, quantity, unitprice, unitcost, attribute) " +
            "VALUES(#{id}, #{suppplier}, #{product}, #{quantity}, #{unitprice}, #{unitcost}, #{attribute})")
    void insertItem(Item item);

    @Update("UPDATE Item SET suppplier=#{suppplier}, product=#{product}, quantity=#{quantity}, unitprice=#{unitprice}, unitcost=#{unitcost}, attribute=#{attribute}")
    void updateItemInfo(Item item);

    @Select("select * from Item where product = #{productid}")
    List<Item> findItemByProductid(@Param("productid") String product);

    @Select("select * from Item where suppplier = #{suppplierid}")
    List<Item> findItemBySuppplierid(@Param("suppplierid") String suppplier);

    @Delete("DELETE FROM Item WHERE id=#{id}")
    void deleteItem(@Param("id") String id);




}

