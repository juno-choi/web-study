package com.juno.spring.controller;

import com.juno.spring.annotation.V1;
import com.juno.spring.domain.dto.TempDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@Slf4j
public class AsyncController {
    @GetMapping("/async/{number}")
    public ResponseEntity<Callable<TempDto>> async(@PathVariable(value = "number") Long number){
        log.info("number = {}", number);
        Callable<TempDto> result =  () -> {
            return TempDto.builder()
                    .id(number)
                    .name("test"+number)
                    .build();
        };
        return ResponseEntity.ok(result);
    }

    @GetMapping("/no-async/{number}")
    public ResponseEntity<TempDto> noAsync(@PathVariable(value = "number") Long number){
        log.info("number = {}", number);

        return ResponseEntity.ok(TempDto.builder()
                .id(number)
                .name("test"+number)
                .build());
    }
}
