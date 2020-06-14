package org.csu.jpetstore.service;

import org.csu.jpetstore.bean.Item;
import org.csu.jpetstore.dao.AccountDao;
import org.csu.jpetstore.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemDao itemDao;

    public void insertItem(Item item) {
       itemDao.insertItem(item);
    }

    public void updateItemInfo(Item item){
        itemDao.updateItemInfo(item);
    }

    public List<Item> findItemByProductid(String product){
        return itemDao.findItemByProductid(product);
    }

    public List<Item> findItemBySuppplierid(String suppplier){
        return itemDao.findItemBySuppplierid(suppplier);
    }

    public void deleteItem(String id){
        itemDao.deleteItem(id);
    }

    public List<Item> findItemById(String id){return itemDao.findItemById(id);}
}
