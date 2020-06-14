package org.csu.jpetstore.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.csu.jpetstore.bean.Category;
import org.csu.jpetstore.bean.Item;
import org.csu.jpetstore.bean.Product;
import org.csu.jpetstore.exception.ApiRequestException;
import org.csu.jpetstore.service.AccountService;
import org.csu.jpetstore.service.ItemService;
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
@RequestMapping(value = "/api/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SupplierService supplierService;

    /**Add Item*/
    @ApiOperation(value = "Add item" , authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public void addItem(@ApiIgnore Authentication auth, @RequestBody Item item){
         //首先判断是用户是否为seller
//        if (accountService.selectAccountByID(auth.getName()).getRole().equals("SELLER")){
        itemService.insertItem(item);
//        }
    }

    /**Update Item*/
    @ApiOperation(value = "Update item" , authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/updateItem", method = RequestMethod.PUT)
    public void updateItem(@ApiIgnore Authentication auth, @RequestBody Item item){
//        if (supplierService.selectSupplierByUserId(auth.getName()).contains())
        itemService.updateItemInfo(item);
    }

    /**findItemByProductid*/
    @ApiOperation(value = "find item by prodcutid", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/{productid}")
    public List<Item> findItemByProductid(@PathVariable String productid) {
        return itemService.findItemByProductid(productid);
    }

    /**findItemBySuppplierid*/
    @ApiOperation(value = "find item by supplierid", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/{suppplierid}")
    public List<Item> findItemBySuppplierid(@PathVariable String suppplierid) {
        return itemService.findItemBySuppplierid(suppplierid);
    }

    /**deleteItem*/
    @ApiOperation(value = "Delete item", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Map deleteItem(@PathVariable String id){
        if (itemService.findItemById(id) == null){
            throw new ApiRequestException("Item not exist!", HttpStatus.BAD_REQUEST);
        }
        itemService.deleteItem(id);
        Map data = new HashMap();
        data.put("message", "Item has been deleted.");
        data.put("id", id);
        data.put("error", false);
        return data;
    }



}
