package com.sztus.oam.employee.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.oam.employee.object.domain.Employee;
import com.sztus.oam.employee.service.EmployeeRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/Employee")
public class EmployeeRegisterController {
    @Autowired
    private EmployeeRegisterService EmployeeRegisterService;
    @PostMapping("/register")
    public JSONObject EmployeeRegister(@RequestBody Employee Employee){
        return EmployeeRegisterService.register(Employee);
    }
}
