package org.csu.jpetstore.service;

import org.csu.jpetstore.bean.Account;
import org.springframework.beans.factory.annotation.Autowired;
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
        Account account = accountService.selectAccountByUsername(username);
        UserDetails userDetails = User.builder()
                .username(account.getId().toString())
                .password(account.getPassword())
                .roles(account.getRole()).build();
        return userDetails;
    }
}
