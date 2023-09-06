package com.sztus.oam.employee.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.oam.lib.core.enumerate.CodeEnum;
import com.sztus.oam.lib.core.type.AjaxResult;
import com.sztus.oam.employee.object.domain.Employee;
import com.sztus.oam.employee.service.EmployeeLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class AdminLoginController {
    @Autowired
    private EmployeeLoginService EmployeeLoginService;
    @PostMapping(value = "/admin")
    public JSONObject adminLogin(@RequestBody Employee Employee){
        if(Employee.getFirstName().equals("william")){
            return EmployeeLoginService.loginByEmployeename(Employee);
        } else {
            return JSONObject.parseObject(AjaxResult.failure(CodeEnum.FAILURE.getText()));
        }

    }
}
