package com.example.springsecurity.controller;


import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Log4j2
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping(value = "/loginProc", method=RequestMethod.POST)
    public ResponseEntity<Map<String, String>> loginProc() {
        Map<String, String> responseData = new HashMap<>();
        responseData.put("message", "Hello, World!");
        responseData.put("status", "success");

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    

}
