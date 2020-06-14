package org.csu.jpetstore.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.bean.Product;
import org.csu.jpetstore.bean.Supplier;
import org.csu.jpetstore.exception.ApiRequestException;
import org.csu.jpetstore.service.AccountService;
import org.csu.jpetstore.service.ProductService;
import org.csu.jpetstore.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SupplierService supplierService;


    /**
     * 获取当前用户的某一个supplier的所有product (查询当前商铺的所有商品)
     *
     * @param supplierid
     * @return
     */
    @ApiOperation(value = "Query all product of current [supplier]", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/all?supplier={supplierid}")
    public List<Product> getProductList(@RequestParam String supplierid) {
        return productService.getProductListBySupplierId(supplierid);
    }

    /**
     * 获取当前用户的所有product,包括所有商铺 (查询当前用户的所有商品)
     *
     * @param auth
     * @return
     */
    @ApiOperation(value = "Query all product of current [user]", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/userAll")
    public List<Product> getUserAllProduct(@ApiIgnore Authentication auth) {
        List<Supplier> suppliersList = supplierService.selectSupplierByUserId(auth.getName());
        List<Product> allProductList = new ArrayList<>();
        for (Supplier supplier : suppliersList) {
            allProductList.addAll(productService.getProductListBySupplierId(String.valueOf(supplier.getId())));
        }
        return allProductList;
    }

    /**
     * 获取当前平台的所有用户的所有product，包括所有所有用户的所有商铺 (查询平台的所有商品)
     *
     * @return
     */
    @ApiOperation(value = "Query all product of current [platform]", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Product> getPlatformAllProduct() {
        List<Product> allProductList = new ArrayList<>();
        for (Account account : accountService.selectAllAccount()) {
            for (Supplier supplier : supplierService.selectSupplierByUserId(String.valueOf(account.getId()))) {
                allProductList.addAll(productService.getProductListBySupplierId(String.valueOf(supplier.getId())));
            }
        }
        return allProductList;
    }

    /**
     * 添加新的 product
     *
     * @param auth
     * @param product
     */
    @ApiOperation(value = "Add new product", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated() and hasRole('SELLER')")
    public void addProduct(@ApiIgnore Authentication auth, @RequestBody Product product) {
        // if supplier belongs to current user
        String supplierId = product.getId().toString();
        Supplier supplier = supplierService.selectSupplierByID(supplierId);
        if (supplier == null) {
            throw new ApiRequestException("Supplier not exist", HttpStatus.BAD_REQUEST);
        } else if (!supplier.getUserid().toString().equals(auth.getName())) {
            throw new ApiRequestException("You don't have permission to operate.");
        }
        productService.insertProduct(product);
    }


    /**
     * 修改product相关信息
     *
     * @param auth
     * @param product
     */
    @ApiOperation(value = "Update product info", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/{productid}", method = RequestMethod.PUT)
    public Map updateProduct(@ApiIgnore Authentication auth, @RequestBody Product product, @RequestParam String productid) {
        System.out.println(product.getId());
        product.setId(Integer.valueOf(productid));
        // 判断这个商品是否为当前用户的售卖范围：获取他的所有商铺是否包含商品所在的店铺
        boolean flag = true;
        List<Supplier> supplierList = supplierService.selectSupplierByUserId(auth.getName());
        for (Supplier supplier : supplierList) {
            for (Product product1 : productService.getProductListBySupplierId(String.valueOf(supplier.getId()))) {
                if (product1.getId().equals(product.getId())) {
                    productService.updateProduct(product);
                    flag = false;
                }
            }
        }
//        if (supplierService.selectSupplierByUserId(auth.getName()).contains(supplierService.selectSupplierByID(String.valueOf(productService.selectProductByID(String.valueOf(product.getId())).getSupplierId())))){
//            System.out.println("is in");
//            productService.updateProduct(product);
//        }

        if (flag) {
            throw new ApiRequestException("Product update error", HttpStatus.BAD_REQUEST);
        }
        // exception handler
        Map data = new HashMap();
        data.put("error", false);
        data.put("message", "Supplier updated success.");
        data.put("id", productid);
        data.put("data", product);
        return data;
    }

    /**
     * 删除product
     *
     * @param auth
     * @param productid
     */
    @ApiOperation(value = "Delete product", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/{productid}", method = RequestMethod.DELETE)
    public Map deleteProduct(@ApiIgnore Authentication auth, @PathVariable String productid) {
        System.out.println(productid);
        List<Supplier> supplierList = supplierService.selectSupplierByUserId(auth.getName());
        for (Supplier supplier : supplierList) {
            for (Product product1 : productService.getProductListBySupplierId(String.valueOf(supplier.getId()))) {
                // String to integer
                if (product1.getId().equals(Integer.valueOf(productid))) {
//                    System.out.println("check");
                    productService.deleteProduct(productid);
                }
            }
        }

//        productService.deleteProduct(productid);
        // 判断这个商品是否为当前用户的售卖范围：获取他的所有商铺是否包含商品所在的店铺
//        if (supplierService.selectSupplierByUserId(auth.getName()).contains(supplierService.selectSupplierByID(String.valueOf(productService.selectProductByID(productid).getSupplierId())))){
//            productService.deleteProduct(productid);
//        }

        // exception handler
        Map data = new HashMap();
        data.put("message", "Product has been deleted.");
        data.put("id", productid);
        data.put("error", false);
        return data;
    }


}
