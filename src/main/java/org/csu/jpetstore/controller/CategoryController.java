package org.csu.jpetstore.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.bean.Category;
import org.csu.jpetstore.bean.Product;
import org.csu.jpetstore.bean.Supplier;
import org.csu.jpetstore.exception.ApiRequestException;
import org.csu.jpetstore.service.AccountService;
import org.csu.jpetstore.service.CategoryService;
import org.csu.jpetstore.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AccountService accountService;

    /**Add new category*/
    @ApiOperation(value = "Add category" , authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public void addCategory(@ApiIgnore Authentication auth, @RequestBody Category category) {
        // 首先判断是用户是否为seller
//        if (accountService.selectAccountByID(auth.getName()).getRole().equals("SELLER")){
            categoryService.insertCategory(category);
//        }
    }

    /**Delete category*/
    @ApiOperation(value = "Delete category" , authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/{categoryid}")
    public Map deleteSupplier(@ApiIgnore Authentication auth, @PathVariable String categoryid){
        if (categoryService.selectCategoryByID(categoryid) == null) {
            throw new ApiRequestException("Category not exist!", HttpStatus.BAD_REQUEST);
        }
        categoryService.deleteCategory(categoryid);

        Map data = new HashMap();
        data.put("message", "Category has been deleted.");
        data.put("id", categoryid);
        data.put("error", false);
        return data;
    }

}
