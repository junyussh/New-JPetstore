package org.csu.jpetstore;

import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.bean.Category;
import org.csu.jpetstore.bean.Product;
import org.csu.jpetstore.bean.Supplier;
import org.csu.jpetstore.service.AccountService;
import org.csu.jpetstore.service.CategoryService;
import org.csu.jpetstore.service.ProductService;
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
        account.setAddress2("Taipei1");
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


    /**
     * Test Category
     */
    @Autowired
    private CategoryService categoryService;

    @Test
    void insertCategory() {
        String id = String.format("%010d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 8);
        Integer _id = Integer.valueOf(id);
        Category newCategory = new Category();
        newCategory.setCategoryId(_id);
        newCategory.setName("DOG");
        categoryService.insertCategory(newCategory);
    }

    @Test
    void deleteCategory() {
        categoryService.deleteCategory("CAT");
    }

    @Test
    void updateCategoryName() {
        categoryService.updateCategoryName("DOG", "10105209");
    }

    /**
     * Test Product
     */
    @Autowired
    private ProductService productService;

    @Test
    void insertProdcut() {
        // get test categoryid = 10105209 supplierId = 12525421
        String id = String.format("%010d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 8);
        Integer _id = Integer.valueOf(id);
        Product newProduct = new Product();
        newProduct.setId(_id);
        newProduct.setSupplierId(12525421); // temp
        newProduct.setCategoryId(10105209); // temp
        newProduct.setName("BigDog");
        productService.insertProduct(newProduct);
    }

    @Test
    void deleteProduct() {
        productService.deleteProduct(String.valueOf(64174746)); // already deleted
    }

    @Test
    void updateProduct() {
        Product product = productService.selectProductByName("BigDog");
        product.setName("BigStrongDog");
        productService.updateProduct(product);
    }


    @Test
    void testGetProductListBySupplierId() {
        List<Product> productList = productService.getProductListBySupplierId("15509524");
        for (Product i : productList) {
            System.out.println(i.getId()+"  "+i.getSupplierId()+"  "+i.getCategoryId());
        }
    }

    @Test
    void testGetSupplierListByUserId(){
        List<Supplier> supplierList =  supplierService.selectSupplierByUserId("30266518");
        for (Supplier i : supplierList) {
            System.out.println(i.getId()+"  "+i.getUserid()+"  "+i.getName());
        }
    }
}
