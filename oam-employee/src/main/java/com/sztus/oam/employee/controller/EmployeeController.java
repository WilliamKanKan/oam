package com.sztus.oam.employee.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {

    @PostMapping("/test")
    public String testEmployee(){
        return "Employee test";
    }

}
