package org.csu.jpetstore.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.bean.Supplier;
import org.csu.jpetstore.exception.ApiRequestException;
import org.csu.jpetstore.service.AccountService;
import org.csu.jpetstore.service.SupplierService;
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
@RequestMapping(value = "/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private AccountService accountService;

    /**
     * Query all supplier
     * @return
     */
    @ApiOperation(value = "Get all suppliers")
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Supplier> selectAllItems(@RequestParam(value = "userId", required = false) String userId){
        if (userId != null){
            Account account = accountService.selectAccountByID(userId);
            if (account == null){
                throw new ApiRequestException("User not exist", HttpStatus.BAD_REQUEST);
            }
            return supplierService.selectSupplierByUserId(userId);
        }
        return supplierService.selectAllSupplier();
    }

    /**
     * Retrieve all the supplier that user owns
     * @param auth
     * @return
     */
    @ApiOperation(value = "Query all suppliers of current user", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/")
    @PreAuthorize("isAuthenticated() and hasRole('SELLER')")
    public List<Supplier> getSupplierListByUserId(@ApiIgnore Authentication auth) {
        return supplierService.selectSupplierByUserId(auth.getName());
    }

    /**
     * Query supplier's information
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Query supplier's information by supplierID")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Supplier getSupplierByID(@PathVariable String id) {
        Supplier supplier = supplierService.selectSupplierByID(id);
        if (supplier == null) {
            throw new ApiRequestException("Supplier not exist!", HttpStatus.BAD_REQUEST);
        }
        return supplier;
    }

    /**
     * New supplier
     *
     * @param auth
     * @param supplier
     * @return
     */
    @ApiOperation(value = "Create new supplier", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.POST, value = "/")
    @PreAuthorize("isAuthenticated() and !hasRole('ROLE_ADMIN')")
    public Supplier newSupplier(@ApiIgnore Authentication auth, @RequestBody Supplier supplier) {
        String userid = auth.getName();
        supplier.setUserid(Integer.parseInt(userid));
        supplierService.insertSupplier(supplier);
        accountService.updateAccountRole(userid, "SELLER");
        return supplier;
    }

    /***
     * Update supplier information
     * @param auth
     * @param supplier
     * @param supplierId
     * @return
     */
    @ApiOperation(value = "Update supplier information", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.PUT, value = "/{supplierId}")
    @PreAuthorize("isAuthenticated() and hasRole('SELLER')")
    public Map updateSupplier(@ApiIgnore Authentication auth, @RequestBody Supplier supplier, @PathVariable Integer supplierId) {
        String userid = auth.getName();
        Supplier supplier1 = supplierService.selectSupplierByID(supplierId.toString());
        if (supplier1 == null) {
            throw new ApiRequestException("Supplier not exist!", HttpStatus.BAD_REQUEST);
        } else if (!supplier1.getUserid().toString().equals(userid)) {
            throw new ApiRequestException("You don't have permission to operate.");
        }
        supplier.setId(supplierId);
        supplierService.updateSupplier(supplier);
        Map data = new HashMap();
        data.put("error", false);
        data.put("message", "Supplier updated success.");
        data.put("id", supplierId);
        data.put("data", supplier);
        return data;
    }

    /**
     * 删除店铺
     *
     * @param auth
     * @param supplierid
     * @return
     */
    @ApiOperation(value = "Delete supplier", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.DELETE, value = "/{supplierid}")
    @PreAuthorize("isAuthenticated() and hasRole('SELLER')")
    public Map deleteSupplier(@ApiIgnore Authentication auth, @PathVariable String supplierid) {
        String userid = auth.getName();
        if (supplierService.selectSupplierByID(supplierid) == null) {
            throw new ApiRequestException("Supplier not exist!", HttpStatus.BAD_REQUEST);
        }
        supplierService.deleteSupplier(supplierid);
        // if user has no supplier, change his role to user
        if (supplierService.selectSupplierByUserId(userid).size()== 0) {
            accountService.updateAccountRole(auth.getName(), "USER");
        }
        Map data = new HashMap();
        data.put("message", "Supplier has been deleted.");
        data.put("id", supplierid);
        data.put("error", false);
        return data;
    }

}
