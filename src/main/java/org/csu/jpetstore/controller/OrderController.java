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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * Create new order
     *
     * @param auth
     * @param param
     * @return
     */
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
                throw new ApiRequestException("Current stock quantity is " + item.getQuantity() + ", you can't order more quantity than stock's", HttpStatus.BAD_REQUEST);
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
            item.setQuantity(item.getQuantity() - param.getQuantity());
            itemService.updateItem(item);
        }
        return param;
    }

    /**
     * Fetch all orders that user order
     *
     * @param auth
     * @return
     */
    @ApiOperation(value = "Query all orders of current user", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() and !hasRole('ADMIN')")
    public List<Order> getOrdersByUser(@ApiIgnore Authentication auth) {
        return orderService.selectOrdersByUserId(auth.getName());
    }

    /**
     * Query all orders by supplierId or productId
     * Note: this method is only available for SELLER and ADMIN
     * @param auth
     * @param supplierId
     * @param productId
     * @return
     */
    @ApiOperation(value = "Query all orders(Admin, Seller)", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() and hasAnyRole('ADMIN', 'SELLER')")
    public List<Order> getAllOrders(@ApiIgnore Authentication auth, @RequestParam(value = "supplierId", required = false) String supplierId, @RequestParam(value = "productId", required = false) String productId) {
        if (supplierId != null && productId != null) {
            throw new ApiRequestException("SupplierId and ProductId cannot both in URL param");
        }
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            if (supplierId != null) {
                Supplier supplier = supplierService.selectSupplierByID(supplierId);
                if (supplier == null) {
                    throw new ApiRequestException("Supplier not exist!", HttpStatus.BAD_REQUEST);
                } else {
                    return orderService.selectOrdersBySupplierId(supplierId);
                }
            } else if (productId != null) {
                Product product = productService.selectProductByID(productId);
                if (product == null) {
                    throw new ApiRequestException("Product not exist!", HttpStatus.BAD_REQUEST);
                } else {
                    return orderService.selectOrdersByProductId(productId);
                }
            }
            return orderService.selectAllOrders();
        } else {
            if (supplierId != null) {
                Supplier supplier = supplierService.selectSupplierByID(supplierId);
                if (supplier == null) {
                    throw new ApiRequestException("Supplier not exist!", HttpStatus.BAD_REQUEST);
                } else if (supplier.getUserid().toString().equals(auth.getName())) {
                    return orderService.selectOrdersBySupplierId(supplierId);
                } else {
                    throw new ApiRequestException("You can only view your supplier's orders.", HttpStatus.FORBIDDEN);
                }
            } else if (productId != null) {
                Product product = productService.selectProductByID(productId);
                if (product == null) {
                    throw new ApiRequestException("Product not exist!", HttpStatus.BAD_REQUEST);
                } else {
                    Supplier supplier = supplierService.selectSupplierByID(product.getSupplierId().toString());
                    if (supplier.getUserid().toString().equals(auth.getName())) {
                        return orderService.selectOrdersByProductId(productId);
                    } else {
                        throw new ApiRequestException("You can only view your product's orders.", HttpStatus.FORBIDDEN);
                    }
                }
            } else {
                List<Supplier> suppliers = supplierService.selectSupplierByUserId(auth.getName());
                List<Order> allOrderList = new ArrayList<>();
                for (Supplier supplier : suppliers) {
                    allOrderList.addAll(orderService.selectOrdersBySupplierId(supplier.getId().toString()));
                }
                return allOrderList;
            }
        }
    }

    /**
     * Query order by id
     *
     * @param auth
     * @param orderId
     * @return
     */
    @ApiOperation(value = "Query order by ID", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/{orderId}")
    @PreAuthorize("isAuthenticated()")
    public Order getOrderById(@ApiIgnore Authentication auth, @PathVariable String orderId) {
        Order order = orderService.selectOrderByID(orderId);
        if (order == null) {
            throw new ApiRequestException("Order not exist!", HttpStatus.BAD_REQUEST);
        }
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            if (order.getUserId().equals(auth.getName())) {
                return order;
            } else {
                throw new ApiRequestException("You don't have permission to view this order", HttpStatus.FORBIDDEN);
            }
        }
        else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SELLER"))) {
            if (order.getUserId().equals(auth.getName())) {
                return order;
            }
            Supplier supplier = supplierService.selectSupplierByID(order.getSupplierId().toString());
            if (supplier.getUserid().toString().equals(auth.getName())) {
                return order;
            } else {
                throw new ApiRequestException("You don't have permission to view this order", HttpStatus.FORBIDDEN);
            }
        } else {
            return order;
        }
    }

    /**
     * Update order status
     * @param auth
     * @param orderId
     * @param param
     * @return
     */
    @ApiOperation(value = "Update order's status", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/{orderId}", method = RequestMethod.PATCH)
    @PreAuthorize("isAuthenticated()")
    public Map updateOrderStatus(@ApiIgnore Authentication auth, @PathVariable String orderId, @RequestBody Map<String, String> param) {
        Map response = new HashMap();
        Order order = orderService.selectOrderByID(orderId);
        if (order == null) {
            throw new ApiRequestException("Order not exist!", HttpStatus.BAD_REQUEST);
        } else {
            if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                if (order.getUserId().toString().equals(auth.getName())) {
                    String status = param.get("status");
                    if (!status.equals("Cancel")) {
                        throw new ApiRequestException("You can only change your order's status to \"Cancel\"", HttpStatus.FORBIDDEN);
                    } else {
                        if (!order.getStatus().equals("Pending")) {
                            throw new ApiRequestException("You can't change the order's status anymore.", HttpStatus.FORBIDDEN);
                        } else {
                            this.cancelOrder(orderId);
                            orderService.updateOrderStatus(status, orderId);
                            response.put("error", false);
                            response.put("message", "Order " + orderId + " status has been changed to " + status);
                            response.put("id", orderId);
                            return response;
                        }
                    }
                } else {
                    throw new ApiRequestException("You can't change other user's order status", HttpStatus.FORBIDDEN);
                }
            } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SELLER"))) {
                Supplier supplier = supplierService.selectSupplierByID(order.getSupplierId().toString());
                if (supplier.getUserid().toString().equals(auth.getName())) {
                    String status = param.get("status");
                    if (!(status.equals("Cancel") || status.equals("Active"))) {
                        throw new ApiRequestException("You can only change your order's status to \"Cancel\" or \"Active\"", HttpStatus.BAD_REQUEST);
                    } else {
                        if (order.getStatus().equals("Active")) {
                            throw new ApiRequestException("The order's status is Active, you can't change it anymore", HttpStatus.FORBIDDEN);
                        } else {
                            if (status.equals("Cancel")) {
                                this.cancelOrder(orderId);
                            }
                            orderService.updateOrderStatus(status, orderId);
                            response.put("error", false);
                            response.put("message", "Order " + orderId + " status has been changed to " + status);
                            response.put("id", orderId);
                            return response;
                        }
                    }
                } else {
                    throw new ApiRequestException("You can only change order's status in your supplier.", HttpStatus.FORBIDDEN);
                }
            } else {
                String status = param.get("status");
                if (!(status.equals("Cancel") || status.equals("Active") || status.equals("Pending"))) {
                    throw new ApiRequestException("You can only change your order's status to \"Cancel\" or \"Pending\" or \"Active\"", HttpStatus.BAD_REQUEST);
                } else {
                    if (order.getStatus().equals("Cancel") && !status.equals("Cancel")) {
                        if (!this.restoreOrder(orderId)) {
                            throw new ApiRequestException("Stock quantity is not enough to restore order.", HttpStatus.BAD_REQUEST);
                        }
                    } else if (!order.getStatus().equals("Cancel") && status.equals("Cancel")) {
                        this.cancelOrder(orderId);
                    }
                    orderService.updateOrderStatus(status, orderId);
                    response.put("error", false);
                    response.put("message", "Order " + orderId + " status has been changed to " + status);
                    response.put("id", orderId);
                    return response;
                }
            }
        }
    }

    /**
     * if order's status is Active or Pending, and to be changed into Cancel, the method will add order's quantity to item's quantity
     * @param orderId
     */
    private void cancelOrder(String orderId) {
        Order order = orderService.selectOrderByID(orderId);
        Item item = itemService.selectItemByID(order.getItemId().toString());
        item.setQuantity(item.getQuantity()+order.getQuantity());
        itemService.updateItem(item);
    }

    /**
     * if order's status is Cancel, and to be changed into Pending or Active, the method will subtract order's quantity from item's quantity
     * @param orderId
     * @return
     */
    private Boolean restoreOrder(String orderId) {
        Order order = orderService.selectOrderByID(orderId);
        Item item = itemService.selectItemByID(order.getItemId().toString());
        if (item.getQuantity() >= order.getQuantity()) {
            item.setQuantity(item.getQuantity()-order.getQuantity());
            itemService.updateItem(item);
            return true;
        } else {
            return false;
        }
    }
}
