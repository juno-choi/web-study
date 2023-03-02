package com.juno.spring.controller;

import com.juno.spring.annotation.V1;
import com.juno.spring.domain.dto.TempDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@V1
@RestController
public class HelloController {
    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("test!");
    }

    @GetMapping(value = "/json")
    public ResponseEntity<TempDto> json(){
        return ResponseEntity.ok(TempDto.builder()
                .id(1L)
                .name("테스터")
                .build());
    }
}
