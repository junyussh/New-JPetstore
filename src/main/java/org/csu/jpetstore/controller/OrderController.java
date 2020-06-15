package org.csu.jpetstore.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.csu.jpetstore.bean.Item;
import org.csu.jpetstore.bean.Order;
import org.csu.jpetstore.bean.Product;
import org.csu.jpetstore.bean.Supplier;
import org.csu.jpetstore.exception.ApiRequestException;
import org.csu.jpetstore.service.ItemService;
import org.csu.jpetstore.service.OrderService;
import org.csu.jpetstore.service.ProductService;
import org.csu.jpetstore.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Create new order", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated() and !hasRole('ADMIN')")
    public Order createOrder(@ApiIgnore Authentication auth, @RequestBody Order param) {
        param.setUserId(Integer.valueOf(auth.getName()));
        Item item = itemService.selectItemByID(param.getItemId().toString());
        if (item == null) {
            throw new ApiRequestException("Item not exist!", HttpStatus.BAD_REQUEST);
        } else {
            if (param.getQuantity() > item.getQuantity()) {
                throw new ApiRequestException("Current stock quantity is "+item.getQuantity()+", you can't order more quantity than stock's", HttpStatus.BAD_REQUEST);
            }
            param.setProductId(item.getProductId());
            param.setSupplierId(item.getSupplierId());
            param.setItemAttribute(item.getAttribute());

            Product product = productService.selectProductByID(item.getProductId().toString());
            param.setProductName(product.getName());

            Supplier supplier = supplierService.selectSupplierByID(item.getSupplierId().toString());
            param.setSupplierName(supplier.getName());

            Timestamp now = new Timestamp(System.currentTimeMillis());
            param.setCreated(now);

            param.setStatus("Pending");
            param.setTotal(item.getUnitprice() * param.getQuantity());

            orderService.insertOrder(param);

            // update item stock quantity
            item.setQuantity(item.getQuantity()-param.getQuantity());
            itemService.updateItem(item);
        }
        return param;
    }
}
