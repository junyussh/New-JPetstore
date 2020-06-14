package org.csu.jpetstore.service;

import org.csu.jpetstore.bean.Category;
import org.csu.jpetstore.dao.CategoryDao;
import org.csu.jpetstore.exception.ApiRequestException;
import org.csu.jpetstore.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private IDGenerator idGenerator;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
     two way to get Category
     */
    public Category selectCategoryByID(String id) {
        return categoryDao.findCategoryByID(id);
    }

    public Category selectCategoryByName(String name) {
        return categoryDao.findCategoryByName(name);
    }

    /**
     * insert new Category
     * first step: find by id
     * second step: insert
     *
     * @param category
     */
    public Map insertCategory(Category category) {
        Integer id;
        do {
            id = idGenerator.getID();
        } while (this.selectCategoryByID(id.toString()) != null);
        if (categoryDao.findCategoryByName(category.getName()) != null){
            throw new ApiRequestException("Supplier not exist!", HttpStatus.BAD_REQUEST);
        }
        category.setCategoryId(id);
        categoryDao.insertCategory(category);
        Map data = new HashMap();
        data.put("error", false);
        data.put("message", "Insert category success.");
        data.put("id", category.getCategoryId());
        data.put("data", category);
        return data;
    }

    /**
     * delete by name but not id
     *
     * @param name
     */
    public void deleteCategory(String name) {
        categoryDao.deleteCategory(name);
    }

    /**
     * update name
     * @param newName
     * @param id
     */
    public void updateCategoryName(String newName, String id) {
        categoryDao.updateCategory(newName, id);
    }

}
