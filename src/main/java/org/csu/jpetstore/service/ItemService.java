package org.csu.jpetstore.service;

import org.csu.jpetstore.bean.Item;
import org.csu.jpetstore.dao.ItemDao;
import org.csu.jpetstore.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private IDGenerator idGenerator;

    public void insertItem(Item item) {
        Integer id;
        do {
            id = idGenerator.getID();
        } while (this.selectItemByID(id.toString()) != null);
        item.setId(id);
        itemDao.insertItem(item);
    }

    public void updateItem(Item item){
        itemDao.updateItem(item);
    }

    public List<Item> selectItemByProductId(String productid){
        return itemDao.findItemByProductId(productid);
    }

    public List<Item> findItemBySuppplierid(int supplierid){
        return itemDao.findItemBySuppplierid(supplierid);
    }

    public List<Item> selectAllItems() {
        return itemDao.selectAllItems();
    }

    public void deleteItem(String id){
        itemDao.deleteItem(id);
    }

    public Item selectItemByID(String id){return itemDao.findItemById(id);}
}
