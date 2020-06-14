package org.csu.jpetstore.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.csu.jpetstore.bean.Category;
import org.csu.jpetstore.bean.Item;
import org.csu.jpetstore.bean.Product;
import org.csu.jpetstore.service.AccountService;
import org.csu.jpetstore.service.ItemService;
import org.csu.jpetstore.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(value = "/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SupplierService supplierService;

    /**
     * Add Item
     */
    @ApiOperation(value = "Add item", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addItem(@ApiIgnore Authentication auth, @RequestBody Item item) {
        //首先判断是用户是否为seller
//        if (accountService.selectAccountByID(auth.getName()).getRole().equals("SELLER")){
        itemService.insertItem(item);
//        }
    }

    /**
     * Update Item
     */
    @ApiOperation(value = "Update item", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void updateItem(@ApiIgnore Authentication auth, @RequestBody Item item) {
//        if (supplierService.selectSupplierByUserId(auth.getName()).contains())
        itemService.updateItemInfo(item);
    }

    /**
     * findItemByProductid
     */
    @ApiOperation(value = "find item by prodcutid", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/{productid}")
    public List<Item> findItemByProductid(@PathVariable String productid) {
        return itemService.findItemByProductid(productid);
    }

    /**
     * findItemBySuppplierid
     */
    @ApiOperation(value = "find item by supplierid", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/{suppplierid}")
    public List<Item> findItemBySuppplierid(@PathVariable String suppplierid) {
        return itemService.findItemBySuppplierid(suppplierid);
    }

    /**
     * deleteItem
     */
    @ApiOperation(value = "Delete item", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
    }


}
