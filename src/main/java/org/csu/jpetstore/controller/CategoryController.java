package org.csu.jpetstore.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.bean.Category;
import org.csu.jpetstore.bean.Product;
import org.csu.jpetstore.bean.Supplier;
import org.csu.jpetstore.service.AccountService;
import org.csu.jpetstore.service.CategoryService;
import org.csu.jpetstore.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categorys")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AccountService accountService;

    /**Add new category*/
    @ApiOperation(value = "Add category" , authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addCategory(@ApiIgnore Authentication auth, @RequestBody Category category) {
        // 首先判断是用户是否为seller
//        if (accountService.selectAccountByID(auth.getName()).getRole().equals("SELLER")){
            categoryService.insertCategory(category);
//        }
    }

    /**Delete category*/
    @ApiOperation(value = "Delete category" , authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/{categoryid}")
    public void deleteSupplier(@PathVariable String categoryid){
            categoryService.deleteCategory(categoryid);
    }

}
