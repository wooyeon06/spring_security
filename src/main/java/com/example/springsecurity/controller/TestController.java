package com.example.springsecurity.controller;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springsecurity.dto.CustomUserDetails;

import lombok.extern.log4j.Log4j2;



@Log4j2
@Controller
public class TestController {

    @GetMapping("/")
    public String index() {
        log.debug("hello......");
        return "index";
    }


    @RequestMapping(value = "/getUserInfo", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> requestMethodName() {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();
        
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("role", role);
        map.put("customUserDetails", customUserDetails);

        return ResponseEntity.ok().body(map);
    }
}
