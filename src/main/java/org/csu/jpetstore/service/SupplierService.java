package org.csu.jpetstore.service;

import org.csu.jpetstore.bean.Supplier;
import org.csu.jpetstore.dao.SupplierDao;
import org.csu.jpetstore.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierDao supplierDao;
    @Autowired
    private IDGenerator idGenerator;

    public Supplier selectSupplierByID(String id) {
        return supplierDao.findSupplierByID(id);
    }
    public List<Supplier> selectSupplierByUserId(String userid) {
        return supplierDao.findSupplier(userid);
    }
    public void insertSupplier(Supplier supplier) {
        Integer id;
        do {
            id = idGenerator.getID();
        } while (this.selectSupplierByID(id.toString()) != null);

        supplier.setId(id);
        supplierDao.insertSupplier(supplier);
    }
    public void deleteSupplier(String id) {
        supplierDao.deleteSupplier(id);
    }

}
