package org.csu.jpetstore;

import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
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
    @Test
    void contextLoads() {
    }

    @Test
    void findUser() {
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
        newAccount.setUsername("admin2");
        newAccount.setPassword("admin");
        accountService.insertAccount(newAccount);
    }
    @Test
    void updateUserStatus() {
        accountService.updateAccountStatus("30266518", true);
    }
    @Test
    void updateUserInfo() {
        Account account = accountService.selectAccountByID("30266518");
        account.setAddress1("Beitou");
        account.setAddress2("Taipei");
        accountService.updateAccountInfo(account);
    }
    @Test
    void updateUserRole() {
        accountService.updateAccountRole("30266518", "ADMIN");
    }
    @Test
    void deleteUser() {
        accountService.deleteAccount("53221811");
    }
}
