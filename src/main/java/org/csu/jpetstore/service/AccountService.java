package org.csu.jpetstore.service;

import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.dao.AccountDao;
import org.csu.jpetstore.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private IDGenerator idGenerator;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Query user by id
     *
     * @param id
     * @return
     */
    public Account selectAccountByID(String id) {
        return accountDao.findAccountByID(id);
    }

    public Account selectAccountByUsername(String username) {
        return accountDao.findAccountByUsername(username);
    }

    /**
     * Get all users
     *
     * @return
     */
    public List<Account> selectAllAccount() {
        return accountDao.findAllAccount();
    }

    /**
     * get specific role userlist
     *
     * @param role
     * @return
     */
    public List<Account> selectAccountByRole(String role) {
        return accountDao.findAccountByRole(role);
    }

    /**
     * Insert new user
     *
     * @param account
     */
    public void insertAccount(Account account) {
        Integer id;
        do {
            id = idGenerator.getID();
        } while (this.selectAccountByID(id.toString()) != null);

        account.setId(id);
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setStatus(false);
        account.setRole("USER");
        accountDao.insertAccount(account);
    }

    public void updateAccountInfo(Account account, Account origin) {
        if (!account.getPassword().isEmpty() || account.getPassword() == null) {
            account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        } else {
            account.setPassword(origin.getPassword());
        }
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
