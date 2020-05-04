package org.csu.jpetstore.service;

import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Query user by id
     * @param id
     * @return
     */
    public Account selectAccountByID(String id) {
        return accountDao.findAccountByID(id);
    }

    /**
     * Get all users
     * @return
     */
    public List<Account> selectAllAccount() {
        return accountDao.findAllAccount();
    }

    /**
     * Insert new user
     * @param account
     */
    public void insertAccount(Account account) {
        String id;
        do {
            id = String.format("%010d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 8);
        } while (this.selectAccountByID(id) != null);
        Integer _id = Integer.valueOf(id);
        account.setId(_id);
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setRole("USER");
        accountDao.insertAccount(account);
    }
    public void updateAccountInfo(Account account) {
        accountDao.updateAccountInfo(account);
    }
    public void updateAccountStatus(String id, boolean status) {
        accountDao.updateAccountStatus(id, status);
    }
    public void updateAccountRole(String id, String role) {
        accountDao.updateAccountRole(id, role);
    }
    public void deleteAccount(String id) {
        accountDao.deleteAccount(id);
    }
}
