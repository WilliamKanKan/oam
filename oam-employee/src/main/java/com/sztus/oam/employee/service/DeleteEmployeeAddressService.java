package com.sztus.oam.employee.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.oam.lib.core.type.AjaxResult;
import com.sztus.oam.employee.object.domain.EmployeeAddress;
import com.sztus.oam.employee.repository.writer.EmployeeAddressWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteEmployeeAddressService {
    @Autowired
    private EmployeeAddressWriter EmployeeAddressWriter;

    public JSONObject deleteEmployeeAddressById(Long id){
        LambdaQueryWrapper<EmployeeAddress> EmployeeAddressLambdaQueryWrapper = new LambdaQueryWrapper<>();
        EmployeeAddressLambdaQueryWrapper.eq(EmployeeAddress::getId,id);
        EmployeeAddress EmployeeAddress = EmployeeAddressWriter.getOne(EmployeeAddressLambdaQueryWrapper);
        if(EmployeeAddress!=null){
            boolean deleteResult = EmployeeAddressWriter.removeById(EmployeeAddress);
            if(deleteResult){
                return JSON.parseObject(AjaxResult.success());
            }else {
                return JSON.parseObject(AjaxResult.failure());
            }
        }else {
            return JSON.parseObject(AjaxResult.failure("Address not found"));
        }
    }
}
