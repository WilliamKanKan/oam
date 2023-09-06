package com.sztus.oam.employee.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.oam.employee.object.domain.Employee;
import com.sztus.oam.employee.service.SendCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/Employee")
public class SendCodeController {
    @Autowired
    private SendCodeService sendCodeService;

    @PostMapping(value = "/send-code")
    private JSONObject sendCode(@RequestBody Employee Employee){
        String telephone = Employee.getTelephone();
        String email = Employee.getEmail();
        return sendCodeService.codeSend(telephone, email);
    }

}
