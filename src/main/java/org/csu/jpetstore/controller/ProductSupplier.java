package org.csu.jpetstore.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.csu.jpetstore.bean.Category;
import org.csu.jpetstore.bean.Product;
import org.csu.jpetstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api/product")
public class ProductSupplier {

    @Autowired
    private ProductService productService;


    /**
     * 获取supplier的所有product
     * @param supplierid
     * @return
     */
    @ApiOperation(value = "Query all product for a supplier" , authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/{supplierid}")
    public List<Product> getCategoryList(@PathVariable String supplierid){
        return productService.getProductListBySupplierId(supplierid);
    }

}
