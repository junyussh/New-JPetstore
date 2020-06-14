package org.csu.jpetstore.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.csu.jpetstore.bean.Supplier;
import org.csu.jpetstore.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(value = "/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * 获取当前用户的所有店铺
     *
     * @param auth
     * @return
     */
    @ApiOperation(value = "Query all suppliers of current user", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<Supplier> getSupplierListByUserId(@ApiIgnore Authentication auth) {
        return supplierService.selectSupplierByUserId(auth.getName());
    }


    /**
     * 删除店铺
     *
     * @param supplierid
     */
    @ApiOperation(value = "Delete supplier", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.DELETE, value = "/{supplierid}")
    public void deleteSupplier(@PathVariable String supplierid) {
        supplierService.deleteSupplier(supplierid);
    }
}
