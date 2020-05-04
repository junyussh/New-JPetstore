package org.csu.jpetstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class AccountController {
    @RequestMapping(method = RequestMethod.GET, value = "/v1/login")
    public Map hello() {
        Map map = new HashMap();
        map.put("say", "hello");
        return map;
    }
}
