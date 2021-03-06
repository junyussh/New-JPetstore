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

    @Update("UPDATE Item SET quantity=#{quantity}, unitprice=#{unitprice}, unitcost=#{unitcost}, attribute=#{attribute} WHERE id=#{id}")
    void updateItem(Item item);

    @Select("select * from Item where productId = #{productId}")
    List<Item> findItemByProductId(@Param("productId") String productId);

    @Select("select * from Item where supplierId = #{supplierId}")
    List<Item> findItemBySupplierId(@Param("supplierId") String supplierId);

    @Select("select * from Item")
    List<Item> selectAllItems();

    @Delete("DELETE FROM Item WHERE id=#{id}")
    void deleteItem(@Param("id") String id);


    @Select("select * from Item where id = #{id}")
    Item findItemById(@Param("id") String id);

}

