package org.csu.jpetstore.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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
     *
     * @param supplierId
     * @return
     */
    @ApiOperation(value = "Query all product of current [supplier]")
    @RequestMapping(method = RequestMethod.GET, value = "/all?supplier={supplierId}")
    public List<Product> getProductList(@RequestParam String supplierId) {
        return productService.getProductListBySupplierId(supplierId);
    }

    /**
     * Query product by ID
     * @param productId
     * @return
     */
    @ApiOperation(value = "Query product by ID")
    @RequestMapping(method = RequestMethod.GET, value = "/{productId}")
    public Product getProductByID(@PathVariable String productId) {
        return productService.selectProductByID(productId);
    }
    /**
     * 获取当前用户的所有product,包括所有商铺 (查询当前用户的所有商品)
     *
     * @param auth
     * @return
     */
    @ApiOperation(value = "Query all product of current [user]", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/")
    @PreAuthorize("isAuthenticated() and hasRole('SELLER')")
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
    @ApiOperation(value = "Query all product in database")
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Product> getAllProducts() {
        return productService.selectAllProducts();
    }

    /**
     * 添加新的 product
     *
     * @param auth
     * @param product
     */
    @ApiOperation(value = "Add a new product", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated() and hasRole('SELLER')")
    public Product addProduct(@ApiIgnore Authentication auth, @RequestBody Product product) {
        // if supplier belongs to current user
        String supplierId = product.getSupplierId().toString();
        Supplier supplier = supplierService.selectSupplierByID(supplierId);
        if (supplier == null) {
            throw new ApiRequestException("Supplier not exist", HttpStatus.BAD_REQUEST);
        } else if (!supplier.getUserid().toString().equals(auth.getName())) {
            throw new ApiRequestException("You don't have permission to operate.");
        }
        productService.insertProduct(product);
        return product;
    }


    /**
     * 修改product相关信息
     *
     * @param auth
     * @param product
     */
    @ApiOperation(value = "Update product info", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated() and hasRole('SELLER')")
    public Map updateProduct(@ApiIgnore Authentication auth, @RequestBody Product product, @RequestParam String productId) {
        product.setId(Integer.valueOf(productId));
        // target supplierId
        String supplierId = product.getSupplierId().toString();
        Product product1 = productService.selectProductByID(productId);
        if (product1 == null) {
            throw new ApiRequestException("Product not exist!", HttpStatus.BAD_REQUEST);
        } else {
            Supplier old_supplier = supplierService.selectSupplierByID(product1.getSupplierId().toString());
            if (!old_supplier.getUserid().toString().equals(auth.getName())) {
                throw new ApiRequestException("You don't own this product", HttpStatus.FORBIDDEN);
            }
            Supplier supplier = supplierService.selectSupplierByID(supplierId);
            if (supplier == null) {
                throw new ApiRequestException("Supplier not exist!", HttpStatus.BAD_REQUEST);
            }
            else if (!supplier.getUserid().toString().equals(auth.getName())) {
                throw new ApiRequestException("You can't change the ownership of the product to other person's supplier!", HttpStatus.FORBIDDEN);
            }
        }
        productService.updateProduct(product);
        Map data = new HashMap();
        data.put("error", false);
        data.put("message", "Supplier updated success.");
        data.put("id", productId);
        data.put("data", product);
        return data;
    }

    /**
     * 删除product
     *
     * @param auth
     * @param productId
     */
    @ApiOperation(value = "Delete product", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    @PreAuthorize("isAuthenticated() and hasRole('SELLER')")
    public Map deleteProduct(@ApiIgnore Authentication auth, @PathVariable String productId) {
        Product product = productService.selectProductByID(productId);
        // product id not exist
        if (product == null) {
            throw new ApiRequestException("Product not exist!", HttpStatus.BAD_REQUEST);
        } else {
            String supplierId = product.getSupplierId().toString();
            // the user don't own this product
            if (!supplierService.selectSupplierByID(supplierId).getUserid().toString().equals(auth.getName())) {
                throw new ApiRequestException("You don't have permission to delete this product", HttpStatus.FORBIDDEN);
            }
        }
        productService.deleteProduct(productId);

        Map data = new HashMap();
        data.put("message", "Product "+product.getName()+" has been deleted.");
        data.put("id", productId);
        data.put("error", false);
        return data;
    }


}
