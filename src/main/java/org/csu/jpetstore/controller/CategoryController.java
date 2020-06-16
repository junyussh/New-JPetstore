package org.csu.jpetstore.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.csu.jpetstore.bean.Category;
import org.csu.jpetstore.bean.Product;
import org.csu.jpetstore.exception.ApiRequestException;
import org.csu.jpetstore.service.CategoryService;
import org.csu.jpetstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    /**
     * Add new category
     * @param auth
     * @param category
     * @return
     */
    @ApiOperation(value = "Add category" , authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated() and hasRole('SELLER')")
    public Category addCategory(@ApiIgnore Authentication auth, @RequestBody Category category) {
        Category category1 = categoryService.selectCategoryByName(category.getName());
        // if category name exist
        if (category1 != null) {
            throw new ApiRequestException("Category is already exist!", HttpStatus.BAD_REQUEST);
        } else {
            categoryService.insertCategory(category);
            return category;
        }
    }

    /**
     * Get all categories
     * @return
     */
    @ApiOperation(value = "Get all categories")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return categoryService.selectAllCategory();
    }

    /**
     * Get category by ID
     * @param categoryId
     * @return
     */
    @ApiOperation(value = "Get category by ID")
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public Category getAllCategories(@PathVariable String categoryId) {
        Category category = categoryService.selectCategoryByID(categoryId);
        if (category == null) {
            throw new ApiRequestException("Category not found!", HttpStatus.NOT_FOUND);
        } else {
            return category;
        }
    }

    /**
     * Delete category by id
     * @param auth
     * @param categoryId
     * @return
     */
    @ApiOperation(value = "Delete category" , authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.DELETE, value = "/{categoryId}")
    @PreAuthorize("isAuthenticated() and !hasRole('USER')")
    public Map deleteSupplier(@ApiIgnore Authentication auth, @PathVariable String categoryId){
        if (categoryService.selectCategoryByID(categoryId) == null) {
            throw new ApiRequestException("Category not exist!", HttpStatus.BAD_REQUEST);
        }
        List<Product> products = productService.selectProductsByCategoryId(categoryId);
        if (products.size() > 0) {
            throw new ApiRequestException("There has one or more products are using the category, you can't delete it.", HttpStatus.BAD_REQUEST);
        } else {
            categoryService.deleteCategoryByID(categoryId);

            Map data = new HashMap();
            data.put("message", "Category has been deleted.");
            data.put("id", categoryId);
            data.put("error", false);
            return data;
        }
    }

}
