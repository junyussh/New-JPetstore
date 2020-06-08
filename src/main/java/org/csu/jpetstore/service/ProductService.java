package org.csu.jpetstore.service;

import org.csu.jpetstore.bean.Category;
import org.csu.jpetstore.bean.Product;
import org.csu.jpetstore.dao.ProductDao;
import org.csu.jpetstore.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private IDGenerator idGenerator;

    /*
     two way to get Product
     */
    public Product selectProductByID(String id) {
        return productDao.findProductByID(id);
    }

    public Product selectProductByName(String name){
        return productDao.findProductByName(name);
    }

    /**
     * insert product
     * @param product
     */
    public void insertProduct(Product product) {
        Integer id;
        do {
            id = idGenerator.getID();
        } while (this.selectProductByID(id.toString()) != null);
        product.setId(id);
        product.setSupplierId(product.getSupplierId());
        product.setCategoryId(product.getCategoryId());
        product.setName(product.getName());
        productDao.insertProduct(product);
    }

    public void deleteProduct(String id){
        productDao.deleteProduct(id);
    }

    public void updateProduct(Product product){
        productDao.updateProductInfo(product);
    }
}
