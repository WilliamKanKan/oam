package com.sztus.oam.employee.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.oam.employee.object.domain.Employee;
import com.sztus.oam.employee.service.EmployeeLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/Employee")
public class EmployeeLoginController {
    @Autowired
    private EmployeeLoginService EmployeeLoginService;
    @PostMapping(value = "/login")
    public JSONObject login(@RequestBody Employee Employee){
        return EmployeeLoginService.loginByEmployeename(Employee);
    }
    @PostMapping(value = "/login-phone")
    public JSONObject loginByPhone(@RequestBody JSONObject object){
        String telephone = object.getString("telephone");
        String verifyCode = object.getString("verifyCode");
        return EmployeeLoginService.loginByTelephone(telephone, verifyCode);
    }
}
