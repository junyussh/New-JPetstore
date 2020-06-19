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

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/users")
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
    public List<Account> getAllUsers(@RequestParam(value = "role", required = false) String role) {
        if (role != null) {
            return accountService.selectAccountByRole(role);
        }
        return accountService.selectAllAccount();
    }

    /**
     * 管理员 更新用户信息
     *
     * @param account
     * @param accountId
     */
    @ApiOperation(value = "Update account info(Admin)", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.PUT, value = "/{accountId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Map updateAccountByID(@RequestBody Account account, @PathVariable String accountId) {
        account.setId(Integer.valueOf(accountId));
        Account account1 = accountService.selectAccountByID(accountId);
        if(account1 == null) {
            throw new ApiRequestException("User id not exist!", HttpStatus.BAD_REQUEST);
        } else {
            account.setId(Integer.parseInt(accountId));
            accountService.updateAccountInfo(account);
            // exception
            Map data = new HashMap();
            data.put("message", "Account updated success");
            data.put("id", accountId);
            data.put("error", false);
            return data;
        }
    }

    @ApiOperation(value = "Update account info", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/me", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    public Account updateAccount(@ApiIgnore Authentication auth, @RequestBody Account account) {
        String userId = auth.getName();
        account.setId(Integer.parseInt(userId));
        Set<String> roles = auth.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());
        Iterator role = roles.iterator();
        account.setRole((String) role.next());
        accountService.updateAccountInfo(account);
        return account;
    }
}
