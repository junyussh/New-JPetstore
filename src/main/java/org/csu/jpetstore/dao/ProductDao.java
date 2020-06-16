package org.csu.jpetstore.dao;

import org.apache.ibatis.annotations.*;
import org.csu.jpetstore.bean.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {

    @Select("select * from Product where id = #{ID}")
    Product findProductByID(@Param("ID") String id);

    @Select("select * from Product where name = #{name}")
    Product findProductByName(@Param("name") String name);

    @Select("select * from Product")
    List<Product> selectAllProducts();

    /**
     * get all product by categoryid
     * @return
     */
    @Select("SELECT * FROM Product where categoryId = #{id}")
    List<Product> findAllProductByCategoryId(@Param("id") String id);

    @Select("SELECT * FROM Product where categoryId = #{categoryId} and supplierId = #{supplierId}")
    List<Product> findAllProductsByCategoryAndSupplier(@Param("categoryId") String categoryId, @Param("supplierId") String supplierId);

    @Insert("INSERT into Product (id, supplierId, categoryId, name, description, image) VALUES(#{id}, #{supplierId}, ${categoryId}, #{name}, #{description}, #{image})")
    void insertProduct(Product product);

    @Delete("DELETE FROM Product WHERE id=#{id}")
    void deleteProduct(@Param("id") String id);

    /**
     * update info
     * @param product
     */
    @Update("UPDATE Product SET supplierId=#{supplierId}, categoryId=#{categoryId}, name=#{name}, description=#{description}, image=#{image} WHERE id=#{id}")
    void updateProductInfo(Product product);

    @Select("select * from Product where supplierId=#{id}")
    List<Product> getProductListBySupplierId(@Param("id") String id);

}
