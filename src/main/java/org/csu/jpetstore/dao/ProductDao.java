package org.csu.jpetstore.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.csu.jpetstore.bean.Category;
import org.csu.jpetstore.bean.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {

    /**
     * get all product by categoryid
     * @return
     */
    @Select("SELECT * FROM Product where categoryId = #{id}")
    List<Product> findAllProductByCategoryId(@Param("id") String id);

    @Insert("INSERT into Product (id, supplierId, categoryId, name) VALUES(#{id}, #{supplierId}, ${categoryId}, #{name}")
    void insertProduct(Product product);

    /**
     * update product supplier
     * @param product
     */
    @Update("UPDATE Product SET supplierId=#{supplierId} WHERE id=#{id}")
    void updateProductSupplier(Product product);

    /**
     * update product name
     * @param product
     */
    @Update("UPDATE Product SET name=#{name} WHERE id=#{id}")
    void updateProductname(Product product);

}
