package org.csu.jpetstore.service;

import org.csu.jpetstore.bean.Category;
import org.csu.jpetstore.dao.CategoryDao;
import org.csu.jpetstore.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public void insertCategory(Category category) {
        Integer id;
        do {
            id = idGenerator.getID();
        } while (this.selectCategoryByID(id.toString()) != null);
        category.setCategoryId(id);
        category.setName(category.getName());
        categoryDao.insertCategory(category);
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
     *
     * @param name
     * @param id
     */
    public void updateCategoryName(String newName, String id) {
        categoryDao.updateCategory(newName, id);
    }

}
