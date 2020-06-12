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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/api/user")
public class AccountController {
    @Autowired
    private AccountService accountService;

    private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @ApiOperation(value = "Query user info" , authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public Account getUser(@ApiIgnore Authentication auth) {
        Account account = accountService.selectAccountByID(auth.getName());
        return account;
    }

    @ApiOperation(value = "Query user info" , authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Account getUserByID(@PathVariable String id) {
        Account account = accountService.selectAccountByID(id);
        if (account == null) {
            throw new ApiRequestException("User id not exist", HttpStatus.NOT_FOUND);
        }
        return account;
    }
}
