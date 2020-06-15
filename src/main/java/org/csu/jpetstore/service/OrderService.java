package org.csu.jpetstore.service;

import org.csu.jpetstore.bean.Order;
import org.csu.jpetstore.dao.OrderDao;
import org.csu.jpetstore.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private IDGenerator idGenerator;

    public Order selectOrderByID(String id) {
        return orderDao.findOrderByID(id);
    }

    public List<Order> selectAllOrders() {
        return orderDao.findAllOrders();
    }

    public List<Order> selectOrdersByUserId(String userId) {
        return orderDao.findOrdersBySupplierId(userId);
    }

    public List<Order> selectOrdersBySupplierId(String supplierId) {
        return orderDao.findOrdersBySupplierId(supplierId);
    }

    public List<Order> selectOrdersByProductId(String productId) {
        return orderDao.findOrdersBySupplierId(productId);
    }

    public Order getOrderById(String id) {
        return orderDao.findOrderByID(id);
    }

    public void insertOrder(Order order) {
        Integer id;
        do {
            id = idGenerator.getID();
        } while (this.selectOrderByID(id.toString()) != null);
        order.setId(id);
        orderDao.insertOrder(order);
    }

    public void updateOrder(Order order) {
        orderDao.updateOrder(order);
    }

    public void deleteOrder(String id) {
        orderDao.deleteOrder(id);
    }

}
