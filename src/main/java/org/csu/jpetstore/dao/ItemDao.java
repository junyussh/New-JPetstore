package org.csu.jpetstore.dao;

import org.apache.ibatis.annotations.*;
import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.bean.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ItemDao {


    @Insert("INSERT into Item (id, productid, supplierid, uniprice, unicost, quantity, attribute) " +
            "VALUES(#{id}, #{product}, #{supplier}, #{uniprice}, #{unicost}, #{quantity}, #{attribute})")
    void insertItem(Item item);

    @Update("UPDATE Item SET supplierid=#{supplier}, productid=#{product}, quantity=#{quantity}, uniprice=#{uniprice}, unicost=#{unicost}, attribute=#{attribute}")
    void updateItemInfo(Item item);

    @Select("select * from Item where productid = #{productid}")
    List<Item> findItemByProductid(@Param("productid") String product);

    @Select("select * from Item where supplierid = #{supplierid}")
    List<Item> findItemBySuppplierid(@Param("supplierid") String suppplier);

    @Delete("DELETE FROM Item WHERE id=#{id}")
    void deleteItem(@Param("id") String id);


    @Select("select * from Item where id = #{id}")
    List<Item> findItemById(@Param("id") String id);

}

