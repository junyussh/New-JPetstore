package org.csu.jpetstore.service;

import org.csu.jpetstore.bean.Category;
import org.csu.jpetstore.dao.CategoryDao;
import org.csu.jpetstore.exception.ApiRequestException;
import org.csu.jpetstore.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private IDGenerator idGenerator;

    /*
     two way to get Category
     */
    public Category selectCategoryByID(String id) {
        return categoryDao.findCategoryByID(id);
    }

    public Category selectCategoryByName(String name) {
        return categoryDao.findCategoryByName(name);
    }

    public List<Category> selectAllCategory() {
        return categoryDao.findAllCategory();
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
        if (categoryDao.findCategoryByName(category.getName()) != null){
            throw new ApiRequestException("Supplier not exist!", HttpStatus.BAD_REQUEST);
        }
        category.setId(id);
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

    public void deleteCategoryByID(String id) {
        categoryDao.deleteCategoryByID(id);
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
