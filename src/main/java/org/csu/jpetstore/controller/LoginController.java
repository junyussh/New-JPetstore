package org.csu.jpetstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map login() {
        Map map = new HashMap();
        map.put("say", "hello");
        return map;
    }
}
