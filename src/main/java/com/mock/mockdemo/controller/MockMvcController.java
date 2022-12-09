package com.mock.mockdemo.controller;

import com.mock.mockdemo.dto.Info;
import org.springframework.web.bind.annotation.*;

@RestController
public class MockMvcController {

    @GetMapping("/mockmvc")
    public String mockGet(@RequestParam String name, @RequestParam String id){
        return name + "의 MockMvc 테스트입니다. " + id;
    }

    @PostMapping("/mockmvc")
    public String MockPost(@RequestBody Info info){
        return info.getName()+"님의 테스트입니다. " + info.getId();
    }

}
