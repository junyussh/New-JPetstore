package org.csu.jpetstore.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.bean.Category;
import org.csu.jpetstore.bean.Product;
import org.csu.jpetstore.bean.Supplier;
import org.csu.jpetstore.service.AccountService;
import org.csu.jpetstore.service.ProductService;
import org.csu.jpetstore.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SupplierService supplierService;


    /**
     * 获取当前用户的某一个supplier的所有product (查询当前商铺的所有商品)
     * @param supplierid
     * @return
     */
    @ApiOperation(value = "Query all product for a supplier", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/{supplierid}")
    public List<Product> getProductList(@PathVariable String supplierid) {
        return productService.getProductListBySupplierId(supplierid);
    }

    /**
     * 获取当前用户的所有product,包括所有商铺 (查询当前用户的所有商品)
     * @param auth
     * @return
     */
    public List<Product> getUserAllProduct(@ApiIgnore Authentication auth){
        List<Supplier> suppliersList = supplierService.selectSupplierByUserId(auth.getName());
        List<Product> allProductList = new ArrayList<>();
        for (Supplier supplier : suppliersList){
            for (Product product : productService.getProductListBySupplierId(String.valueOf(supplier.getId()))){
                allProductList.add(product);
            }
        }
        return allProductList;
    }

    /**
     * 获取当前平台的所有用户的所有product，包括所有所有用户的所有商铺 (查询平台的所有商品)
     * @return
     */
    public List<Product> getPlatformAllProduct(){
        List<Product> allProductList = new ArrayList<>();
        for (Account account : accountService.selectAllAccount()){
            for (Supplier supplier : supplierService.selectSupplierByUserId(String.valueOf(account.getId()))){
                for (Product product : productService.getProductListBySupplierId(String.valueOf(supplier.getId()))){
                    allProductList.add(product);
                }
            }
        }
        return allProductList;
    }

    /**
     * 添加新的 product
     * @param auth
     * @param product
     */
    @ApiOperation(value = "Add new product", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public void addProduct(@ApiIgnore Authentication auth, @RequestBody Product product) {
        // 首先判断是用户是否为seller
        if (accountService.selectAccountByID(auth.getName()).getRole().equals("SELLER")){
            productService.insertProduct(product);
        }
    }

//    public void updateProduct()

}
