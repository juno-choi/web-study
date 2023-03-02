package com.juno.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello2Controller {
    @GetMapping("test2")
    public ResponseEntity<?> test2(){
        return ResponseEntity.ok("test2");
    }
}
