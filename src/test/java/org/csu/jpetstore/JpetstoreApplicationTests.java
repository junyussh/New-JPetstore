package org.csu.jpetstore;

import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.bean.Supplier;
import org.csu.jpetstore.service.AccountService;
import org.csu.jpetstore.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

//@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
//@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
//@EnableJdbcRepositories(basePackages = "org.csu.jpetstore.repository")
//@ComponentScan("org.csu.jpetstore")
@SpringBootTest
class JpetstoreApplicationTests {

    @Autowired
    private AccountService accountService;
    @Autowired
    private SupplierService supplierService;

    @Test
    void contextLoads() {
    }

    /**
     * Account Test
     */
    @Test
    void findUserByID() {
        Account account = accountService.selectAccountByID("30266518");
        System.out.println(account.getUsername());
    }
    @Test
    void insertAccount() {
        String id = String.format("%010d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 8);
        Integer _id = Integer.valueOf(id);
        Account newAccount = new Account();
        newAccount.setFirstName("Eric");
        newAccount.setLastName("Chen");
        newAccount.setAddress1("Taipei, Taiwan");
        newAccount.setCity("Taipei");
        newAccount.setCountry("Taiwan");
        newAccount.setId(_id);
        newAccount.setPassword("123456");
        newAccount.setUsername("test");
        newAccount.setPassword("admin");
        accountService.insertAccount(newAccount);
    }
    @Test
    void updateUserStatus() {
        Account account = accountService.selectAccountByUsername("test");
        accountService.updateAccountStatus(account.getId().toString(), true);
    }
    @Test
    void updateUserInfo() {
        Account account = accountService.selectAccountByUsername("test");
        account.setAddress1("Beitou");
        account.setAddress2("Taipei");
        accountService.updateAccountInfo(account);
    }
    @Test
    void updateUserRole() {
        Account account = accountService.selectAccountByUsername("test");
        accountService.updateAccountRole(account.getId().toString(), "ADMIN");
    }
    @Test
    void deleteUser() {
        Account account = accountService.selectAccountByUsername("test");
        accountService.deleteAccount(account.getId().toString());
    }

    /**
     * Supplier Test
     */
    @Test
    void insertSupplier() {
        Supplier supplier = new Supplier();
        Account testAccount = accountService.selectAccountByUsername("test");
        supplier.setUserid(testAccount.getId());
        supplier.setName("Test Shop");
        supplier.setAddress1("Shop Address 1");
        supplier.setAddress2("Shop Address 2");
        supplier.setCity("Taipei");
        supplier.setState("Taipei");
        supplier.setZip("112");
        supplier.setPhone("0987987487");
        supplierService.insertSupplier(supplier);
    }
    @Test
    void findSupplierByName() {
        List<Supplier> suppliers = supplierService.selectSupplierByName("test");
        Supplier supplier = suppliers.get(0);
        System.out.println(supplier.getName());
    }
    @Test
    void deleteSupplier() {
        Supplier supplier = supplierService.selectSupplierByName("Test").get(0);
        supplierService.deleteSupplier(supplier.getId().toString());
    }
}
