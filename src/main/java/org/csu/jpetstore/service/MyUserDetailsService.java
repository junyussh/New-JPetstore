package org.csu.jpetstore.service;

import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Account account = accountService.selectAccountByID(username);
        if (account == null) {
            account = accountService.selectAccountByUsername(username);
        }
        if (account == null) {
            throw new ApiRequestException("Invalid username or password!", HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = User.builder()
                .username(account.getId().toString())
                .password(account.getPassword())
                .roles(account.getRole()).build();
        return userDetails;
    }
}
