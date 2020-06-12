package org.csu.jpetstore.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.csu.jpetstore.bean.Account;
import org.csu.jpetstore.exception.ApiRequestException;
import org.csu.jpetstore.service.AccountService;
import org.csu.jpetstore.service.MyUserDetailsService;
import org.csu.jpetstore.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class LoginController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

    /***
     * Login
     * @param Map
     * @return
     */
    @ApiOperation("Authenticate")
    @ApiResponses(value = {@ApiResponse(code=200,message = "Login Success"), @ApiResponse(code = 401, message = "Invalid username or password")})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map login(@RequestBody Map<String, String> params) {
        Map data = new HashMap();

        String username = params.get("username");
        String password = params.get("password");

        System.out.println(username);
        System.out.println(password);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        }
        catch (BadCredentialsException e) {
            throw new ApiRequestException("Invalid username or password!", HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        final String jwt = jwtUtil.generateToken(userDetails);
        data.put("token", jwt);
        data.put("id", userDetails.getUsername());
        data.put("username", username);
        data.put("message", "Login success");
        return data;
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Account register(@RequestBody Account param) {
        accountService.insertAccount(param);
        return param;
    }
}
