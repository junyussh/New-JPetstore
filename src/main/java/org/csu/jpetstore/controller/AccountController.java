package org.csu.jpetstore.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.exception.ApiRequestException;
import org.csu.jpetstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user")
public class AccountController {
    @Autowired
    private AccountService accountService;

    private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @ApiOperation(value = "Query current user info", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/me")
    public Account getUser(@ApiIgnore Authentication auth) {
        Account account = accountService.selectAccountByID(auth.getName());
        return account;
    }


    /**
     * 管理员 利用ID获取Account
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Fetch account info by ID", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Account getAccountByID(@PathVariable String id) {
        Account account = accountService.selectAccountByID(id);
        if (account == null) {
            throw new ApiRequestException("User id not exist", HttpStatus.NOT_FOUND);
        }
        return account;
    }


    /**
     * 管理员 获取平台所有的用户
     *
     * @return
     */
    @ApiOperation(value = "Get all account", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Account> getAllUsers() {
        return accountService.selectAllAccount();
    }


    /**
     * 管理员 获取特定的role的所有账户
     *
     * @param role
     * @return
     */
    @ApiOperation(value = "Get account by specific role", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/getAccountByRole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Account> getAccountByRole(@RequestParam String role) {
        return accountService.selectAccountByRole(role);
    }


    /**
     * 管理员 更新用户信息
     *
     * @param account
     * @param accountid
     */
    @ApiOperation(value = "Update account info", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.PUT, value = "/{accountid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Map updateAccount(@RequestBody Account account, @PathVariable String accountid) {
        account.setId(Integer.valueOf(accountid));
        accountService.updateAccountInfo(account);
        // exception
        Map data = new HashMap();
        data.put("message", "Account updated success");
        data.put("id", accountid);
        data.put("error", false);
        return data;
    }


}
