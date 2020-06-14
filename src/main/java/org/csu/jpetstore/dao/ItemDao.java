package org.csu.jpetstore.dao;

import org.apache.ibatis.annotations.*;
import org.csu.jpetstore.bean.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ItemDao {


    @Insert("INSERT into Item (id, productId, supplierId, unitprice, unitcost, quantity, attribute) " +
            "VALUES(#{id}, #{productId}, #{supplierId}, #{unitprice}, #{unitcost}, #{quantity}, #{attribute})")
    void insertItem(Item item);

    @Update("UPDATE Item SET quantity=#{quantity}, unitprice=#{unitprice}, unitcost=#{unitcost}, attribute=#{attribute}")
    void updateItem(Item item);

    @Select("select * from Item where productId = #{productid}")
    List<Item> findItemByProductId(@Param("productid") String productid);

    @Select("select * from Item where supplierId = #{supplierid}")
    List<Item> findItemBySuppplierid(@Param("supplierid")  int supplierid);

    @Select("select * from Item")
    List<Item> selectAllItems();

    @Delete("DELETE FROM Item WHERE id=#{id}")
    void deleteItem(@Param("id") String id);


    @Select("select * from Item where id = #{id}")
    Item findItemById(@Param("id") String id);

}

