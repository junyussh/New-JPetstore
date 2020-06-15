package org.csu.jpetstore.dao;

import org.apache.ibatis.annotations.*;
import org.csu.jpetstore.bean.Item;
import org.csu.jpetstore.bean.Supplier;

import java.util.List;

@Mapper
public interface SupplierDao {
    @Select("SELECT * FROM Supplier WHERE id=#{id}")
    Supplier findSupplierByID(@Param("id") String id);

    @Select("SELECT * FROM Supplier WHERE INSTR(userId, #{value})")
    List<Supplier> findSupplier(@Param("value") String value);

    @Insert("INSERT INTO Supplier(id, userid, name, address1, address2, city, state, zip, phone) " +
            "VALUES(#{id}, #{userid}, #{name}, #{address1}, #{address2}, #{city}, #{state}, #{zip}, #{phone})")
    void insertSupplier(Supplier supplier);

    @Update("UPDATE Supplier SET name=#{name}, address1=#{address1}, address2=#{address2}, city=#{city}, state=#{state}, zip=#{zip}, phone=#{phone} WHERE id=#{id}")
    void updateSupplier(Supplier supplier);

    @Delete("DELETE FROM Supplier WHERE id=#{id}")
    void deleteSupplier(@Param("id") String id);

    @Select("select * from Supplier")
    List<Supplier> selectAllSupplier();
}
