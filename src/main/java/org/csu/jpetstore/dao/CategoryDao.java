package org.csu.jpetstore.dao;

import org.apache.ibatis.annotations.*;
import org.csu.jpetstore.bean.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CategoryDao {


    @Select("select * from Category where id = #{ID}")
    Category findCategoryByID(@Param("ID") String id);

    @Select("select * from Category where name = #{name}")
    Category findCategoryByName(@Param("name") String name);

    /**
     * find all category
     * @return
     */
    @Select("SELECT * FROM Category")
    List<Category> findAllCategory();

    @Insert("INSERT into Category (id, name) VALUES(#{id}, #{name})")
    void insertCategory(Category category);

    /**
     * update Category a new name by id
     * @param newName
     * @param id
     */
    @Update("UPDATE Category SET name=#{newName} WHERE id=#{id}")
    void updateCategory(@Param("newName") String newName ,@Param("id") String id);

    /**
     * delete category by name
     * @param name
     */
    @Delete("DELETE FROM Category WHERE name=#{name}")
    void deleteCategory(@Param("name") String name);


}
