package com.sztus.oam.employee.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.oam.employee.object.domain.Employee;
import com.sztus.oam.employee.object.domain.EmployeeAddress;
import com.sztus.oam.employee.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/Employee")
public class EmployeeInfoController {
    @Autowired
    private GetEmployeeInfoService getEmployeeInfoService;
    @Autowired
    private SearchEmployeeService searchEmployeeService;
    @Autowired
    private EditEmployeeService editEmployeeService;
    @Autowired
    private DeleteEmployeeService deleteEmployeeService;
    @Autowired
    private DeleteEmployeeAddressService deleteEmployeeAddressService;
    @Autowired
    private EditEmployeeAddressService editEmployeeAddressService;
    @GetMapping(value = "/get-account")
    public JSONObject getInfo(HttpServletRequest request){
        String token = request.getHeader("Access-Token");
        return getEmployeeInfoService.getAccount(token);
    }
    @GetMapping(value = "/get-authorization")
    public JSONObject getAuthorization(HttpServletRequest request){
        String token = request.getHeader("Access-Token");
        return getEmployeeInfoService.getAuthorization(token);
    }
    @GetMapping(value = "/search/page/{pageId}")
    public JSONObject searchAllEmployee(@PathVariable("pageId") Long pageId,@RequestParam Long pageSize,@RequestParam(required = false) String nickname){
        return searchEmployeeService.searchEmployeeForAll(pageId, pageSize, nickname);

    }
    @PostMapping(value = "/edit/{id}")
    public JSONObject editEmployeeById(@PathVariable("id") Long id,@RequestBody Employee Employee){
        return editEmployeeService.editEmployeeById(id,Employee);

    }
    @DeleteMapping(value = "/delete/{id}")
    public JSONObject deleteEmployeeById(@PathVariable("id") Long id){
        return deleteEmployeeService.deleteEmployeeById(id);
    }
    @DeleteMapping(value = "/delete-address/{id}")
    public JSONObject deleteEmployeeAddressById(@PathVariable("id") Long id){
        return deleteEmployeeAddressService.deleteEmployeeAddressById(id);
    }
    @PostMapping(value = "/edit-address/{id}")
    public JSONObject editEmployeeAddressById(@PathVariable("id") Long id, @RequestBody EmployeeAddress EmployeeAddress){
        return editEmployeeAddressService.editEmployeeAddressById(id,EmployeeAddress);
    }
}
